import React, { useEffect, Fragment, useState } from "react"
import { Link, useLocation, useNavigate } from "react-router-dom"
import { getUserProfile, putMyProfile, postMyImage } from "api/mypages"

import UserSection from "./UserSection/UserSection.component"
import Spinner from "components/Components/Spinner/Spinner.component"
import UserAnswerActivity from "./UserActivity/UserAnswerActivity.component"
import UserTagActivity from "./UserActivity/UserTagActivity.component"
import UserQuestionActivity from "./UserActivity/UserQuestionActivity.component"

import "./ProfilePage.styles.scss"
import { useDispatch, useSelector } from "react-redux"
import {
  selectUser,
  selectEdit,
  setIsEdit,
  selectFile,
  selectNewInfo,
  setNewInfo,
  setUser,
  setFile,
  setChangeInfo,
  setChangeImage,
  selectToken,
} from "redux/slice/AuthSlice.js"
import { create_Chk_With_Room } from "api/talk"

const ProfilePage = () => {
  const [curUser, setCurUser] = useState([])
  const [edit, setEdit] = useState(false) // 변경시 css 관리를 위한 변수
  const [loading, setLoading] = useState(true)

  //정보수정 시 nickname과 about의 상태 관리
  const [nickname, setNickname] = useState(curUser.nickname)
  const [about, setAbout] = useState(curUser.about)

  const userSeq = useLocation().pathname.split("/")[2]
  const accessToken = localStorage.getItem("accessToken")

  // redux
  const dispatch = useDispatch()
  let navigate = useNavigate()
  // 현재 로그인하고 있는 유저
  let loginUser = useSelector(selectUser)
  // 하위 컴포넌트 수정 사항을 위해 전체적으로 관리
  let globalEdit = useSelector(selectEdit)
  // 이미지 파일 저장
  let file = useSelector(selectFile)
  // 수정 정보 저장
  let newInfo = useSelector(selectNewInfo)
  const [acToken] = useState(useSelector(selectToken))
  useEffect(() => {
    getUserProfile(accessToken, userSeq).then((data) => {
      const payload = data.data.result.data
      setEdit(false) // 처음 초기화
      setIsEdit(false)
      setCurUser(payload)
      setNickname(payload.nickname == null ? "" : payload.nickname)
      setAbout(payload.about == null ? "" : payload.about)
      if (loginUser != null && Number(loginUser.seq) === Number(userSeq)) dispatch(setUser(payload))
      setLoading(false)
    })
    console.log("reload")
  }, [])

  /**
   * 사용자가 정보를 수정할 때 사용
   */
  const putUserInfo = () => {
    setEdit(true)
    dispatch(setIsEdit(true))
    console.log("정보수정")
  }

  /**
   * 정보 수정 후 저장할 때 사용
   */
  const saveUserInfo = () => {
    setEdit(false)
    dispatch(setIsEdit(false))
    const formData = new FormData()
    formData.append("data", file)
    if (file != null) {
      //내 이미지를 서버에 전송
      postMyImage(accessToken, formData).then((data) => {
        const payload = data.data.result.data
        console.log(payload)
        console.log(loginUser)
        dispatch(setFile(null))
        dispatch(setChangeImage(payload))
      })
    }
    if (newInfo != null) {
      //내 프로필을 서버에 전송
      putMyProfile(accessToken, newInfo).then((data) => {
        const payload = data.data.result.data
        dispatch(setChangeInfo(payload))
        dispatch(setNewInfo(null))
      })
    }
    // setEdit(false)
    dispatch(setIsEdit())
    setEdit(false)
  }

  /**
   *
   * @param {*} oppSeq
   */
  const create_ChatRoom = (oppSeq) => {
    create_Chk_With_Room(acToken, oppSeq).then(() => {
      navigate("/talk")
    })
  }
  return loading || curUser === null ? (
    <Spinner type='page' width='75px' height='200px' />
  ) : (
    <Fragment>
      <div id='mainbar' className='user-main-bar pl24 pt24'>
        <div className='user-card'>
          <div className='grid--cell s-navigation mb16'>
            <Link id='info' to='#' className={"s-navigation--item " + (edit ? "" : "is-selected")} data-shortcut='P'>
              내 정보
            </Link>
            {loginUser !== null && Number(loginUser.seq) === Number(userSeq) ? (
              !globalEdit ? (
                <button
                  id='edit'
                  className={"s-navigation--item " + (edit ? "is-selected display" : "")}
                  onClick={putUserInfo}
                >
                  정보수정
                </button>
              ) : (
                <button
                  id='edit'
                  className={"s-navigation--item " + (edit ? "is-selected" : "")}
                  onClick={saveUserInfo}
                >
                  확인
                </button>
              )
            ) : (
              <div></div>
            )}
          </div>
          <UserSection
            nickname={nickname}
            setNickname={setNickname}
            setAbout={setAbout}
            about={about}
            user={curUser}
            setCurUser={setCurUser}
          />
        </div>
        {Number(loginUser.seq) !== Number(userSeq) ? (
          <div>
            <button className='kotalk-btn' onClick={() => create_ChatRoom(userSeq)}>
              코톡 보내기
            </button>
          </div>
        ) : (
          <div></div>
        )}

        <div className='row-grid'>
          <UserQuestionActivity userSeq={userSeq} />
        </div>
        <div className='row-grid'>
          <UserAnswerActivity userSeq={userSeq} />
        </div>
        <div className='row-grid'>
          <UserTagActivity userSeq={userSeq} />
        </div>
      </div>
    </Fragment>
  )
}

export default ProfilePage
