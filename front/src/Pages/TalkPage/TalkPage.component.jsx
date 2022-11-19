import "./TalkPage.styles.scss"

import "react-chat-elements/dist/main.css"
import { MessageList, MessageBox, Input, Button, ChatList } from "react-chat-elements"
import { Grid } from "@mui/material"
import React, { Fragment, useEffect, useState, useMemo, useRef } from "react"
import { v4 as uuidv4 } from "uuid"
import {
  chkEachRoomTalk,
  create_Chk_With_Room,
  deleteRoom,
  deleteTalk,
  getAllMyRoom,
  getAllRoomTalkCnt,
  readEachRoomTalk,
  sendTalk,
} from "api/talk"
import { useSelector, useDispatch } from "react-redux"
import { selectUser, selectToken } from "redux/slice/AuthSlice"
import { useNavigate } from "react-router-dom"
import SockJS from "sockjs-client"

import usestateref from "react-usestateref"
import { Box, Container } from "@mui/system"

const inputReferance = React.createRef()

//일정 시간마다 함수 호출 가능
const useInterval = (callback, delay) => {
  const savedCallback = useRef()

  useEffect(() => {
    savedCallback.current = callback
  })

  useEffect(() => {
    const tick = () => {
      savedCallback.current()
    }

    const timerId = setInterval(tick, delay)
    return () => clearInterval(timerId)
  }, [delay])
}

var StompJs = require("@stomp/stompjs")

var client = new StompJs.Client({
  //websocket 주소만 입력 가능 * ws://, wss:// 로 시작
  // brokerURL: "https://i7d106.p.ssafy.io:8080/ws",
  connectHeaders: {},
  // debug: function (str) {
  //   console.log(str);
  // },
  reconnectDelay: 5000,
  heartbeatIncoming: 4000,
  heartbeatOutgoing: 4000,
})
//  StompJS로 연결
client.webSocketFactory = () => {
  return new SockJS(process.env.REACT_APP_API_URL + "/ws/chat")
}

client.onWebSocketClose = () => {
  console.log("소켓 연결 종료")
}

//에러 발생시 실행되는 코드
client.onStompError = function (frame) {
  console.log("Broker reported error: " + frame.headers["message"])
  console.log("Additional details: " + frame.body)
}

const TalkPage = () => {
  const navigate = useNavigate()
  const [myInfo, setMyInfo] = useState(useSelector(selectUser))
  const [acTkn, setAcTkn] = useState(useSelector(selectToken))
  const [allRoomList, setAllRoomList] = useState([])
  const [chatList, setChatList, chatListRef] = usestateref([])
  /**createdTime, roomSeq, user(상대방) waitingMessageNumber*/
  const [crntConRoom, setCrntConRoom] = useState()
  const [callbackSetup, setCallbackSetup] = useState(false)
  const messagesEndRef = useRef()

  // useEffect(() => {
  //   scrollToBottom()
  // })

  useInterval(() => {
    getAllMyRoom(acTkn).then((res) => {
      setAllRoomList(res.data.result.data)
    })
  }, 3000)

  const scrollToBottom = () => {
    messagesEndRef.current?.scrollIntoView({ behavior: "smooth" })
  }
  // 내 정보가 변경될 때마다 실행
  useEffect(() => {
    //채팅 컴포넌트는 로그인 필요한 서비스
    if (acTkn == null) {
      alert("로그인이 필요합니다.")
      navigate("/login")
    }
  }, [acTkn, myInfo, navigate])

  // 내가 가지고있는 모든 방의 정보를 호출
  useEffect(() => {
    getAllMyRoom(acTkn).then((res) => {
      console.log("모든 방 정보: ", res.data.result.data)
      setAllRoomList(res.data.result.data)
    })
    return () => {
      client.deactivate()
    }
  }, [])

  const onMessageReceived = (payload) => {
    let parsedData = ""
    try {
      parsedData = JSON.parse(payload.body)
      console.log("parsedData: ", parsedData)
      //전송자가 나가 아닌 경우 전송받은 메세지를 입력
      if (parsedData.sender !== myInfo.seq) {
        console.log("chatListRef.current: ", chatListRef.current)
        setChatList([
          ...chatListRef.current,
          {
            content: parsedData.chatContent,
            user: { nickname: parsedData.senderNickname },
          },
        ])
      }
    } catch (error) {
      console.log("NOT JSON data: " + payload)
      return
    }
    scrollToBottom()
  }
  const requestCreateRoom = (usrSeq) => {
    if (usrSeq === myInfo.seq) {
      alert("자기 자신에게 채팅창 생성 불가")
      return
    }
    create_Chk_With_Room(acTkn, usrSeq).then(({ data }) => {
      console.log("채팅창 생성/조회 결과: ", data)
    })
  }

  /**모든 채팅방 출력 */
  const PrintChatRoomAll = () => {
    return allRoomList.map((data) => {
      return (
        <ChatList
          key={uuidv4()}
          onClick={() => spreadChat_conSock(data, setChatList)}
          className='chat-list'
          dataSource={[
            {
              avatar: data.user.profile,
              alt: "no avatar",
              title: data.user.nickname,
              // subtitle: "최신 메시지",
              date: data.createdTime,
              unread: data.waitingMessageNumber,
            },
          ]}
        />
      )
    })
  }
  /**채팅 및 소켓 연결 */
  const spreadChat_conSock = (data, setChatList) => {
    // 채팅 내역 호출
    chkEachRoomTalk(acTkn, data.roomSeq).then(({ data }) => {
      console.log("각 방 채팅 내역", data.result.data)
      setChatList(data.result.data)
    })

    // 해당 방과 소켓 연결
    client.onConnect = (frame) => {
      console.log("frame: ", frame)
      console.log("socket onConnect userNum to " + data.user.seq)
      // spring 구조 => 상대방 seq로 바로 보내는 상황
      // 따라서 자신과 상대방 seq에 대한 구독
      // 전송 시 상대방 번호로
      // 수신 시 자신의 번호로
      // 메시지가 도착
      client.subscribe("/sub/chat/room/" + data.roomSeq, onMessageReceived)
    }
    client.activate()
    setCrntConRoom(data)
  }
  const clickSendTalk = () => {
    console.log("myInfo: ", myInfo)
    let value = inputReferance.current.value.trim()
    console.log("value:", value)
    if (value === "" || value == null || crntConRoom === null) return
    inputReferance.current.value = ""
    let data = {
      roomSeq: crntConRoom.roomSeq,
      content: value,
      sessionCode: 0,
    }

    //내가 보낸 정보를 채팅 리스트에 추가
    setChatList([
      ...chatListRef.current,
      {
        content: value,
        user: { nickname: myInfo.nickname },
      },
    ])

    sendTalk(acTkn, data)

    client.publish({
      destination: "/pub/chat/message",
      headers: {
        Authorization: "Bearer " + acTkn,
        "content-type": "application/json",
      },
      body: JSON.stringify({
        userSeq: crntConRoom.roomSeq,
        sender: myInfo.seq,
        senderNickname: myInfo.nickname,
        chatContent: value,
      }),
      skipContentLengthHeader: true,
    })
    scrollToBottom()
  }

  const PrintChatAll = () => {
    return chatListRef.current.map((chat, idx) => {
      return (
        <MessageBox
          key={uuidv4()}
          position={chat.user.nickname === myInfo.nickname ? "right" : "left"}
          title={chat.user.nickname}
          type='text'
          text={chat.content}
          date={chat.createdTime}
        />
      )
    })
  }
  const makeRoom = (receiver) => {
    create_Chk_With_Room(acTkn, receiver).then((res) => {
      console.log(res)
    })
  }
  return (
    <Box sx={{ height: 700 }}>
      <br />
      <button
        onClick={() => {
          makeRoom(5)
        }}
      >
        채팅방 생성 디버그
      </button>
      <Grid container spacing={2}>
        <Grid item xs={3}>
          <PrintChatRoomAll />
        </Grid>
        <Grid item xs={7}>
          <Grid item>
            <div className='talkBox'>
              <div className='boxInner'>
                <PrintChatAll />
                <div ref={messagesEndRef} />
              </div>
            </div>
          </Grid>
          <Grid item>
            <Input
              referance={inputReferance}
              // multiline={true}
              rightButtons={<Button color='white' backgroundColor='black' text='Send' onClick={clickSendTalk} />}
              onKeyUp={() => {
                if (window.event.keyCode === 13) clickSendTalk()
              }}
            />
          </Grid>
        </Grid>
        <Grid item xs={2}></Grid>
      </Grid>
    </Box>
  )
}

export default TalkPage
