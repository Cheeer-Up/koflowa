import React from "react"
import { Link } from "react-router-dom"
import { useState } from "react"

import UserCard from "../UserCard/UserCard.component"
import TagBadge from "../TagBadge/TagBadge.component"

import "./PostItem.styles.scss"

function PostItem(question) {
  return (
    <div className='posts'>
      <div className='stats-container fc-black-500'>
        <div className='stats'>
          <div className='vote'>
            <span className='vote-count'>{question.question.answerCount}</span>
            <div className='count-text'>답변수</div>
          </div>
          {/* <div className='vote'>
            <span className='vote-count'>{comment_count}</span>
            <div className='count-text'>코멘트</div>
          </div> */}
          {/* {answer_count > 0 ? answerVoteUp : answerVoteDown} */}
          <div className='vote'>
            <span className='vote-count'>{question.question.tagList && question.question.tagList.length}</span>
            <div className='count-text'>{question.question.tagList && "태그"}</div>
          </div>
          <div className='vote'></div>
        </div>
      </div>
      <div className='summary'>
        <h3>
          <Link to={`/questions/${question.question.questionSeq}`}>{question.question.questionTitle}</Link>
        </h3>
        <div className='profile-tags'>
          {question.question.tagList &&
            question.question.tagList.map((tag, index) => <TagBadge key={index} tag_name={tag} size={"s-tag"} />)}
        </div>
        <UserCard
          created_at={question.question.createdTime}
          user_id={question.question.user.seq}
          gravatar={question.question.user.profile}
          username={question.question.user.nickname}
          float={"right"}
          backgroundColor={"transparent"}
        />
      </div>
    </div>
  )
}

export default PostItem
