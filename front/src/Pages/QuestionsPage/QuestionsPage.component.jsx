import React, { Fragment, useEffect, useState } from "react"
import { useLocation } from "react-router-dom"

import LinkButton from "components/Components/LinkButton/LinkButton.component"
import PostItem from "components/Components/PostItem/PostItem.component"
import Spinner from "components/Components/Spinner/Spinner.component"
import SearchBox from "components/Components/SearchBox/SearchBox.component"
import Pagination from "components/Layouts/Pagination/Pagination.component"
import { getQuestionsData } from "api/question"

import "./QuestionsPage.styles.scss"

const itemsPerPage = 10

const QuestionsPage = () => {
  const [page, setPage] = useState(1)
  const [totalPage, setTotalPage] = useState(1)
  const [questions, setQuestions] = useState(null)
  console.log(questions);

  let searchQuery = new URLSearchParams(useLocation().search).get("search")

  useEffect(() => {
    console.log("searchQuery: ", searchQuery)
    getQuestionsData(localStorage.getItem("accessToken"), {
      page: page - 1,
      size: itemsPerPage,
      sort: ["createdTime.desc"],
    }).then((res) => {
      console.log("getQuestionsData: ", res)
      setTotalPage(res.data.result.data.totalPages)
      // setQuestions(res.data.result.data.content)
      setQuestions(res.data.result.data)
    })
  }, [page])

  const handlePaginationChange = (e, value) => {
    if (value !== page) {
      setPage(value)
    }
  }

  return questions === null ? (
    <Spinner type='page' width='75px' height='200px' />
  ) : (
    <Fragment>
      <div id='mainbar' className='questions-page fc-black-800'>
        <div className='questions-grid'>
          <h3 className='questions-headline'>{searchQuery ? "검색 결과" : "모든 질문"}</h3>
          <div className='questions-btn'>
            <LinkButton text={"질문 하기"} link={"/add/question"} type={"s-btn__primary"} />
          </div>
        </div>
        {searchQuery ? (
          <div className='search-questions'>
            <span style={{ color: "#acb2b8", fontSize: "12px" }}>Results for {searchQuery}</span>
            <SearchBox placeholder={"검색..."} name={"search"} pt={"mt8"} />
          </div>
        ) : (
          ""
        )}
        <div className='questions-tabs'>
          <span>{new Intl.NumberFormat("en-IN").format(questions.content.length)} 질문글</span>
        </div>
        <div className='questions'>
          {questions.content.map((question, index) => (
            <PostItem key={index} question={question} />
          ))}
        </div>
        <Pagination page={page} count={totalPage} handlePaginationChange={handlePaginationChange} />
      </div>
    </Fragment>
  )
}

export default QuestionsPage
