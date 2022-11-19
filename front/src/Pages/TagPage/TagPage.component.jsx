import React, { useEffect, Fragment, useState } from "react"
import { useParams } from "react-router-dom"

import PostItem from "components/Components/PostItem/PostItem.component"
import LinkButton from "components/Components/LinkButton/LinkButton.component"
import Spinner from "components/Components/Spinner/Spinner.component"
import "./TagPage.styles.scss"
import { getSingleTagData } from "api/tags"
import { getQuestionDatabyTagName } from "api/question"

const TagPage = () => {
  const [tag, setTag] = useState({})
  const { tagName } = useParams()
  const [questionList, setQuestionList] = useState([])

  useEffect(() => {
    getSingleTagData(tagName).then((result) => {
      setTag(result.data.result.data)
    })
    getQuestionDatabyTagName(tagName).then((result) => {
      setQuestionList(result.data.result.data.content)
    })
  }, [tagName])

  return tag === null ? (
    <Spinner type='page' width='75px' height='200px' />
  ) : (
    <Fragment>
      <div id='mainbar' className='questions-page fc-black-800'>
        <div className='questions-grid'>
          <h3 className='questions-headline'>{tag.name} 가 태그된 질문들</h3>
          <div className='questions-btn'>
            <LinkButton text={"Ask Question"} link={"/add/question"} type={"s-btn__primary"} />
          </div>
        </div>
        <p className='fs-body' dangerouslySetInnerHTML={{ __html: tag.description }} />
        <div className='questions-tabs'>
          <span>
            {new Intl.NumberFormat("en-IN").format(tag.questionCount)}{" "}
            {tag.questionCount === 1 ? "question" : "questions"}
          </span>
        </div>
        <div className='questions'>
          {tag.questionCount === 0 ? (
            <h4 style={{ margin: "30px 30px" }}>질문이 존재하지 않습니다.</h4>
          ) : (
            questionList?.map((question, index) => <PostItem key={index} question={question} />)
          )}
        </div>
      </div>
    </Fragment>
  )
}

export default TagPage
