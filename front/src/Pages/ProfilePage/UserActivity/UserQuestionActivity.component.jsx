import React, { useState } from "react"
import { getUserProfile, getUserTags, getAuthUserProfile, getUserquestion, getUserAnswer } from "api/mypages"

import TagBadge from "components/Components/TagBadge/TagBadge.component"

import "./UserActivity.styles.scss"
import { useEffect } from "react"
import UserActivity from "./UserAnswerActivity.component"
import UserContent from "./UserContent.component"
import Pagination from "components/Layouts/Pagination/Pagination.component"

const UserQuestionActivity = ({ userSeq }) => {
  const [questions, setQuestions] = useState([])
  const accessToken = localStorage.getItem("accessToken")
  const [page, setPage] = useState(1)
  const [totalPage, setTotalPage] = useState(1)
  const size = 5
  const sort = "createdTime,desc"

  useEffect(() => {
    getUserquestion(accessToken, page - 1, size, sort, userSeq).then((data) => {
      const payload = data.data.result.data
      // console.log(payload)
      setQuestions(payload.content)
      setTotalPage(payload.totalPages)
    })
  }, [page])
  const handlePaginationChange = (e, value) => {
    setPage(value)
  }

  const handleChange = (e) => {
    e.preventDefault()
  }
  return (
    <div className='grid-cell2'>
      <div className='top-tags'>
        <h3 className='fw-bold fc-dark bc-black-3'>Questions</h3>
        <div className='top-tags-sec'>
          {questions.map((question, index) => (
            <UserContent type='question' content={question} key={index} />
          ))}
        </div>
      </div>
      <Pagination page={page} count={totalPage} handlePaginationChange={handlePaginationChange} />
    </div>
  )
}

export default UserQuestionActivity
