import React, { Fragment, useState } from "react"
import { connect, useSelector } from "react-redux"
// import PropTypes from "prop-types"
// import { Link } from "react-router-dom"

import TagBadge from "../../../../components/Components/TagBadge/TagBadge.component"
import UserCard from "../../../../components/Components/UserCard/UserCard.component"
import { Link, useLocation } from "react-router-dom"
import "./PostCell.styles.scss"
import censorBadWords from "../../../../utils/censorBadWords"
import { selectUser, selectToken } from "redux/slice/AuthSlice"

// const PostCell = ({
//   deletePost,
//   auth,
//   post: {
//     post: { id, post_body, tags, gravatar, user_id, username, created_at },
//   },
// }) => {
const PostCell = (data) => {
  console.log("data :",data);
  const [curUser] = useState(useSelector(selectUser))
  const content = data.content.questionContent
  const user = data.content.user
  const questionSeq = data.content.questionSeq
  const tags = data.content.tagList
  console.log("tags :",tags);
  return (
    <Fragment>
      <div className='post-cell'>
        <div className='post-text fc-black-800' dangerouslySetInnerHTML={{ __html: censorBadWords(content) }}></div>
        {/* <div className='post-tags fc-black-800'> */}
        <div className='post-tags'>
          {tags!=null ?(
            tags.map((tag, index) => (
                <TagBadge key={index} tag_name={tag} size={"s-tag"} float={"left"} />
                  // <TagBadge key={index} tag_name={tag} size={"s-tag"} />
              // <div className='profile-tags'>

              // </div>
            ))
          ):(
            <div></div>
          )
          }
        </div>
        <div className='post-actions fc-black-800'>
          <div className='post-actions-extended'>
            <div className='post-btns'>
              <div className='post-menu'>
                <Link className='post-links' title='short permalink to this question' to='/'>
                  share
                </Link>
                <Link className='post-links' title='Follow this question to receive notifications' to='/'>
                  follow
                </Link>
                {/* {curUser.seq === user.seq && (
                  <Link
                    className='s-link s-link__danger'
                    style={{ paddingLeft: "4px" }}
                    title='Delete the post'
                    onClick={(e) => deletePost(questionSeq)}
                    to='/questions'
                  >
                    delete
                  </Link>
                )} */}
              </div>
            </div>
            <UserCard created_at={user.createdTime} user_id={user.name} gravatar={user.profile} username={user.nickname} />
          </div>
        </div>
      </div>
    </Fragment>
  )
}

export default PostCell
