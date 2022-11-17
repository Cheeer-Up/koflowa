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
            answerCount={question.question.answerCount}
            commentCount={question.question.comment_count}
            tagCount={question.question.tagList ? question.tagList.length : 0}
          />
          <PostCell content={question.question.questionContent} user={question.question.user} />
          {/* <CommentCell /> */}
        </div>
      </div>
    </Fragment>
  )
}

export default QuestionSection
