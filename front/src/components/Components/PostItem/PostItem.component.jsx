import React from "react"
import { Link } from "react-router-dom"

import UserCard from "../UserCard/UserCard.component"
import TagBadge from "../TagBadge/TagBadge.component"

import "./PostItem.styles.scss"

function PostItem(question) {
  // 여기서 부터
  const tags = ["python", "java"]
  const comment_count = 10
  const username = "test"
  const gravatar = "https://cdn-icons-png.flaticon.com/512/5435/5435719.png"
  const answer = 3
  // 여기까지는 테스트용 데이터 입니다
  console.log(question)
  return (
    <div className='posts'>
      <div className='stats-container fc-black-500'>
        <div className='stats'>
          <div className='vote'>
            <span className='vote-count'>{answer}</span>
            <div className='count-text'>답변수</div>
          </div>
          <div className='vote'>
            <span className='vote-count'>{comment_count}</span>
            <div className='count-text'>코멘트</div>
          </div>
          {/* {answer_count > 0 ? answerVoteUp : answerVoteDown} */}
          <div className='vote'>
            <span className='vote-count'>{tags.length}</span>
            <div className='count-text'>태그</div>
          </div>
          <div className='vote'></div>
        </div>
      </div>
      <div className='summary'>
        <h3>
          <Link to={`/questions/${question.question.questionSeq}`}>
            {question.question.questionTitle}
          </Link>
        </h3>
        {/* 컬럼값 추가가 되면 수정될 예정
        <div className='profile-tags'>
          {tags.map((tag, index) => (
            <TagBadge key={index} tag_name={tag.tagname} size={"s-tag"} />
          ))}
        </div> */}
        <UserCard
          created_at={question.question.createdTime}
          user_id={question.question.userSeq}
          gravatar={gravatar}
          username={username}
          float={"right"}
          backgroundColor={"transparent"}
        />
      </div>
    </div>
  )
}

export default PostItem
