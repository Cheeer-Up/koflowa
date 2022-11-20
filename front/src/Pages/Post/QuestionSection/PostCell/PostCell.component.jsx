import React, { Fragment, useState } from "react"
import { connect, useSelector } from "react-redux"

import TagBadge from "../../../../components/Components/TagBadge/TagBadge.component"
import UserCard from "../../../../components/Components/UserCard/UserCard.component"
import "./PostCell.styles.scss"
import censorBadWords from "../../../../utils/censorBadWords"
import { selectUser, selectToken } from "redux/slice/AuthSlice"

const PostCell = (data) => {
  const [curUser] = useState(useSelector(selectUser))
  const content = data.content.questionContent
  const user = data.content.user
  const questionSeq = data.content.questionSeq
  const tags = data.content.tagList
  return (
    <Fragment>
      <div className='post-cell'>
        <div className='post-text fc-black-800' dangerouslySetInnerHTML={{ __html: censorBadWords(content) }}></div>
        <div className='post-tags'>
          {tags != null ? (
            tags.map((tag, index) => <TagBadge key={index} tag_name={tag} size={"s-tag"} float={"left"} />)
          ) : (
            <div></div>
          )}
        </div>
        <div className='post-actions fc-black-800'>
          <div className='post-actions-extended'>
            <div className='post-btns'>
              <div className='post-menu'></div>
            </div>
            <UserCard
              created_at={data.content.createdTime}
              user_id={user.name}
              gravatar={user.profile}
              username={user.nickname}
            />
          </div>
        </div>
      </div>
    </Fragment>
  )
}

export default PostCell
