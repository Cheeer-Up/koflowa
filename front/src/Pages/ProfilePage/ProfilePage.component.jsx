import React, { useEffect, Fragment, useState } from "react"
import { Link, useLocation } from "react-router-dom"
import { getUserProfile } from "api/mypages"

import UserSection from "./UserSection/UserSection.component"
import Spinner from "components/Components/Spinner/Spinner.component"
import UserAnswerActivity from "./UserActivity/UserAnswerActivity.component"
import UserTagActivity from "./UserActivity/UserTagActivity.component"
import UserQuestionActivity from "./UserActivity/UserQuestionActivity.component"

import "./ProfilePage.styles.scss"
import { useDispatch } from "react-redux"
import { useSelector } from "react-redux"
import { selectUser, selectEdit, setIsEdit } from "redux/slice/AuthSlice.js"

const ProfilePage = () => {
  const [user, setUser] = useState()
  const [edit, setEdit] = useState(false)
  const [loading, setLoading] = useState(true)
  const userSeq = useLocation().pathname.split("/")[2]
  const accessToken = localStorage.getItem("accessToken")

  const dispatch = useDispatch()
  let loginUser = useSelector(selectUser)
  let isEdit = useSelector(selectEdit)

  useEffect(() => {
    getUserProfile(accessToken, userSeq).then((data) => {
      const payload = data.data.result.data
      setUser(payload)
      setLoading(false)
    })
    console.log(loginUser)
  }, [isEdit])

  const putUserInfo = () => {
    if (!isEdit) {
      setEdit(true)
      dispatch(setIsEdit(true))
    } else {
      setEdit(false)
      dispatch(setIsEdit(false))
    }
    console.log(loginUser)
  }

  return loading || user === null ? (
    <Spinner type='page' width='75px' height='200px' />
  ) : (
    <Fragment>
      <div id='mainbar' className='user-main-bar pl24 pt24'>
        <div className='user-card'>
          <div className='grid--cell s-navigation mb16'>
            <Link id='info' to='#' className={"s-navigation--item " + (edit ? "" : "is-selected")} data-shortcut='P'>
              내 정보
            </Link>
            {loginUser !== null && loginUser.seq == userSeq ? (
              isEdit === false ? (
                <button id='edit' className={"s-navigation--item " + (edit ? "is-selected" : "")} onClick={putUserInfo}>
                  정보수정
                </button>
              ) : (
                <button
                  id='edit-complete'
                  className={"s-navigation--item " + (edit ? "is-selected" : "")}
                  onClick={putUserInfo}
                >
                  확인
                </button>
              )
            ) : (
              <div></div>
            )}
          </div>
          <UserSection user={user} />
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
