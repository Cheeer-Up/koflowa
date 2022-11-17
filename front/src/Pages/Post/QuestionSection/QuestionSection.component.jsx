import React, { Fragment } from "react"

import VoteCell from "./VoteCell/VoteCell.component"
import PostCell from "./PostCell/PostCell.component"
// import CommentCell from "./CommentCell/CommentCell.component"

import "./QuestionSection.styles.scss"

const QuestionSection = (question) => {
  console.log("this question : ",question);
  return (
    <Fragment>
      <div className='question'>
        <div className='post-layout'>
          <VoteCell
            answerCount={question.question.answerCount}
            commentCount={question.question.comment_count}
            tagCount={question.question.tagList ? question.question.tagList.length : 0}
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
