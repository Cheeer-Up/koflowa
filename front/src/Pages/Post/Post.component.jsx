import React, { useEffect, Fragment, useState } from "react"
import moment from "moment"
import { useParams } from "react-router-dom"

import LinkButton from "../../components/Components/LinkButton/LinkButton.component"
import Spinner from "../../components/Components/Spinner/Spinner.component"
import AnswerSection from "./AnswerSection/AnswerSection.component"
import QuestionSection from "./QuestionSection/QuestionSection.component"
import { getQuestionData } from "api/question"

import "./Post.styles.scss"

const Post = () => {
  const { questionSeq } = useParams()
  const [question, setQuestion] = useState(null)

  useEffect(() => {
    getQuestionData(questionSeq).then((result) => {
      console.log(result.data.result.data)
      setQuestion(result.data.result.data)
    })
  }, [])

  return question === null ? (
    <Spinner type='page' width='75px' height='200px' />
  ) : (
    <Fragment>
      <div id='mainbar' className='post'>
        <div className='question-header fc-black-800 pl24'>
          <h1>{question.questionTitle}</h1>
          <div>
            <LinkButton text={"질문 작성"} link={"/add/question"} type={"s-btn__primary"} />
          </div>
        </div>
        <div className='question-date fc-black-800 pl24'>
          <div className='grid-cell'>
            <span>{moment(question.createdTime).fromNow(false)}</span>
          </div>
        </div>
        <div className='question-main pl24 pt16'>
          <p>{question.questionContent}</p>
          <QuestionSection question={question} />
          <AnswerSection questionSeq={questionSeq} page='0' size='10' />
        </div>
      </div>
    </Fragment>
  )
}

export default Post
