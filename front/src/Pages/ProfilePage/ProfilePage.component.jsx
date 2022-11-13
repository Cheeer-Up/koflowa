import React, { useEffect, Fragment, useState } from "react"
import { Link, useLocation, useParams, redirect } from "react-router-dom"
import { getUserProfile, getUserTags, getAuthUserProfile, getUserquestion, getUserAnswer } from "../../api/mypages"

import UserSection from "./UserSection/UserSection.component"
import Spinner from "components/Components/Spinner/Spinner.component"
import UserActivity from "./UserActivity/UserActivity.component"
import UserTagActivity from "./UserActivity/UserTagActivity.component"

import { selectUser } from "../../redux/slice/AuthSlice"
import { useSelector } from "react-redux"
import "./ProfilePage.styles.scss"
import axios from "axios"

const ProfilePage = () => {
  const [user, setUser] = useState(useSelector(selectUser))
  const [loading, setLoading] = useState(true)
  const [tags, setTags] = useState([])
  const [questions, setQuestions] = useState([])
  const [answers, setAnswers] = useState([])
  const userSeq = useLocation().pathname.split("/")[2]
  const [page, setPage] = useState(1)
  const size = 10
  const sort = "createTime,desc"
  const [accessToken] = useState(localStorage.getItem("accessToken"))
  useEffect(() => {
    if (user === null) {
      alert("로그인 페이지로 이동합니다.")
      window.location.href = "/login"
    }
    console.log("user: ", user)
    console.log("accessToken: ", accessToken)

    getAuthUserProfile(user.seq, accessToken)
      .then((res) => {
        console.log("getAuthUserProfile: ", res)
        let payload = res.data.result.data
        setUser(payload)
        setLoading(false)
      })
      .catch((err) => {
        console.log(err)
      })
    getUserTags(userSeq).then((data) => {
      console.log("getUserTags: ", data)

      const payload = data.data.result.data
      setTags(payload)
    })
    getUserquestion(page, size, sort, userSeq).then((data) => {
      console.log("getUserquestion: ", data)

      const payload = data.data.result.data
      console.log("question", payload)
    })
    getUserAnswer(page, size, sort, userSeq).then((data) => {
      console.log("getUserAnswer: ", data)

      const payload = data.data.result.data
      console.log("answer", payload)
    })
  }, [])

  return loading || user === null ? (
    <Spinner type='page' width='75px' height='200px' />
  ) : (
    <Fragment>
      <div id='mainbar' className='user-main-bar pl24 pt24'>
        <div className='user-card'>
          <div className='grid--cell s-navigation mb16'>
            <Link to='#' className='s-navigation--item is-selected' data-shortcut='P'>
              Profile
            </Link>
            <Link to='#' className='s-navigation--item' data-shortcut='A'>
              Activity
            </Link>
          </div>
          <UserSection user={user} />
        </div>
        <div className='row-grid'>
          <UserTagActivity tags={tags} />
        </div>
        <div className='row-grid'>
          <UserActivity title='Question' userSeq={userSeq} />
        </div>
        <div className='row-grid'>
          <UserActivity title='Answer' userSeq={userSeq} />
        </div>
      </div>
    </Fragment>
  )
}

export default ProfilePage
