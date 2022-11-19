import api from "api/api"
import { TALKROOM, TALKMSG, talkList, talkRead, talkChk } from "api/urls"

/**나의 모든 채팅방을 조회 */
export const getAllMyRoom = (acTkn) => api(acTkn).get(TALKROOM)

/**상대방과의 채팅방 생성/조회 */
export const create_Chk_With_Room = (acTkn, receiverSeq) => api(acTkn).post(TALKROOM, { receiver_seq: receiverSeq })

/**채팅방 삭제 */
export const deleteRoom = (acTkn, roomSeq) =>
  api(acTkn).delete(TALKROOM, {
    roomSeq: roomSeq,
  })

/** 톡 전송*/
export const sendTalk = (acTkn, data) =>
  api(acTkn).post(TALKMSG, {
    ...data,
  })

/** 각각의 톡 기록 삭제*/
export const deleteTalk = (acTkn, msgSeq) =>
  api(acTkn).delete(TALKMSG, {
    params: {
      messageSeq: msgSeq,
    },
  })
/**특정 채팅방 톡 조회 */
export const chkEachRoomTalk = (acTkn, roomSeq) => api(acTkn).get(talkList(roomSeq), {})

/** 채팅방 메시지 전체(단일) 읽음 표시 */
export const readEachRoomTalk = (acTkn, roomSeq) =>
  api(acTkn).get(talkRead(), {
    params: {
      roomSeq: roomSeq,
    },
  })

/** 유저 미확인 톡 전체(단일) 개수 표시*/
export const getAllRoomTalkCnt = (acTkn) => api(acTkn).get(talkChk())
