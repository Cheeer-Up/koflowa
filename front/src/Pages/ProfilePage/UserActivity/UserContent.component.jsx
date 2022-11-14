import React from "react"

import TagBadge from "components/Components/TagBadge/TagBadge.component"
import "moment/locale/ko"
import moment from "moment"
import "./UserActivity.styles.scss"
import { Link } from "react-router-dom"

const UserContent = ({ type, content }) => {
  return type === "question" ? (
    <div className='top-tags-cells'>
      <div className='top-cell'>
        <div className='tag-cell bg-black-025'>
          <Link className='title' to={`/questions/${content.questionSeq}`}>
            {content.questionTitle}
          </Link>
          <div className='score'>
            <div className='score-txt'>
              <div className='score-tab'>
                {/* <span className='txt fc-light'>Posts</span> */}
                <span className='number fc-black-800'>{moment(content.createdTime).startOf("hour").fromNow()}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  ) : (
    <div className='top-tags-cells'>
      <div className='top-cell'>
        <div className='tag-cell bg-black-025'>
          <Link className='title' to={`/questions/${content.questionSeq}`}>
            {content.content}
          </Link>
          <div className='score'>
            <div className='score-txt'>
              <div className='score-tab'>
                <span className='number fc-black-800'>{moment(content.createdTime).startOf("hour").fromNow()}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

export default UserContent
