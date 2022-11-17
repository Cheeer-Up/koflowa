import React, { Fragment } from "react"

import VoteCell from "./VoteCell/VoteCell.component"
import PostCell from "./PostCell/PostCell.component"
// import CommentCell from "./CommentCell/CommentCell.component"

import "./QuestionSection.styles.scss"

const QuestionSection = (question) => {
  return (
    <Fragment>
      <div className='question'>
        <div className='post-layout'>
          <VoteCell
            question={question.question}
          />
          <PostCell content={question.question}/>
          {/* <CommentCell /> */}
        </div>
      </div>
    </Fragment>
  )
}

export default QuestionSection
