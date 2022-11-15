import React, { useEffect, Fragment, useState } from "react"
import { Link, useLocation } from "react-router-dom"
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
} from "redux/slice/AuthSlice.js"

const ProfilePage = () => {
  const [curUser, setCurUser] = useState({})
  const [edit, setEdit] = useState(false) // 변경시 css 관리를 위한 변수
  const [loading, setLoading] = useState(true)
  const userSeq = useLocation().pathname.split("/")[2]
  const accessToken = localStorage.getItem("accessToken")

  const dispatch = useDispatch()
  let loginUser = useSelector(selectUser)
  let isEdit = useSelector(selectEdit) // 하위 컴포넌트 수정 사항을 위해 전체 적으로 관리
  let file = useSelector(selectFile)
  let newInfo = useSelector(selectNewInfo)

  useEffect(() => {
    getUserProfile(accessToken, userSeq).then((data) => {
      const payload = data.data.result.data
      setCurUser(payload)
      dispatch(setUser(payload))
      setLoading(false)
    })
  }, [isEdit])

  const putUserInfo = () => {
    // 현재 정보 수정을 하는 상태이다.
    setEdit(true)
    dispatch(setIsEdit())
    console.log("정보수정")
  }

  const saveUserInfo = () => {
    // 정보 수정 완료 상태
    // display 숨긴거 다시 원상 복구 해주고
    // 지금까지 수정한 값 서버로 보내주기
    setEdit(false)
    dispatch(setIsEdit())
    const formData = new FormData()
    formData.append("data", file)
    if (file != null) {
      postMyImage(accessToken, formData).then((data) => {
        const payload = data.data.result.data
        console.log(payload)
        console.log(loginUser)
        dispatch(setFile(null))
        dispatch(setChangeImage(payload))
      })
    }
    if (newInfo != null) {
      putMyProfile(accessToken, newInfo).then((data) => {
        const payload = data.data.result.data
        dispatch(setChangeInfo(payload))
        dispatch(setNewInfo(null))
      })
    }
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
              isEdit === false ? (
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
          <UserSection user={curUser} />
        </div>
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
