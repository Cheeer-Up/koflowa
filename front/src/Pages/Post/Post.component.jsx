import React, { useEffect, Fragment, useState } from "react"
import moment from "moment"
import { useParams } from "react-router-dom"

import LinkButton from "../../components/Components/LinkButton/LinkButton.component"
import Spinner from "../../components/Components/Spinner/Spinner.component"
import AnswerSection from "./AnswerSection/AnswerSection.component"
import QuestionSection from "./QuestionSection/QuestionSection.component"
import { getQuestionData } from "api/question"

import "./Post.styles.scss"

// const Post = ({ getPost, post: { post, loading } }) => {
//   const { id } = useParams()

//   useEffect(() => {
//     getPost(id)
//     // eslint-disable-next-line
//   }, [getPost])

//   return loading || post === null ? (
//     <Spinner type='page' width='75px' height='200px' />
//   ) : (
//     <Fragment>
//       <div id='mainbar' className='post'>
//         <div className='question-header fc-black-800 pl24'>
//           <h1>{censorBadWords(post.title)}</h1>
//           <div>
//             <LinkButton text={"Ask Question"} link={"/add/question"} type={"s-btn__primary"} />
//           </div>
//         </div>
//         <div className='question-date fc-black-800 pl24'>
//           <div className='grid-cell'>
//             <span className='fc-light'>Asked</span>
//             <time dateTime={moment(post.created_at).fromNow(true)}>
//               {moment(post.created_at).fromNow(true)} ago
//             </time>
//           </div>
//         </div>
//         <div className='question-main pl24 pt16'>
//           <QuestionSection />
//           <AnswerSection />
//         </div>
//       </div>
//     </Fragment>
//   )
// }
const Post = () => {
  // const { id } = useParams()
  const questionSeq = useParams()
  const [loading, setLoading] = useState(true)
  const [question, setQuestion] = useState(null)

  // useEffect(() => {\
  //   getQuestionData(questionSeq).then((res) => {
  //     console.log(res)
  //     setQuestion()
  //     setLoading(false)
  //   }, [])
  useEffect(() => {
    getQuestionData(questionSeq.postSeq).then((res) => {
      setQuestion(res.data.result.data)
      setLoading(false)
    })
  }, [])

  // eslint-disable-next-line

  return loading || question === null ? (
    <Spinner type='page' width='75px' height='200px' />
  ) : (
    <Fragment>
      <div id='mainbar' className='post'>
        <div className='question-header fc-black-800 pl24'>
          {/* <h1>{censorBadWords(question.title)}</h1> */}
          <h1>{question.questionTitle}</h1>
          <div>
            <LinkButton text={"질문 작성"} link={"/add/question"} type={"s-btn__primary"} />
          </div>
        </div>
        <div className='question-date fc-black-800 pl24'>
          <div className='grid-cell'>
            <span>
              {moment(question.createdTime).fromNow(false)} {/* 실제로 들어갈 코드*/}
            </span>
          </div>
        </div>
        <div className='question-main pl24 pt16'>
          <p>{question.questionContent}</p>
          <QuestionSection />
          <AnswerSection questionSeq={questionSeq.postSeq} page="0" size="10"/>
        </div>
      </div>
    </Fragment>
  )
}

export default Post
