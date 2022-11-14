import React, { useEffect } from "react"
import "moment/locale/ko"
import moment from "moment"

import "./ContentCard.styles.scss"

const ContentCard = ({ username, answers_count, posts_count, comments_count, tags_count, created_at, user_about }) => (
  <div className='content-card'>
    <div className='content-grid'>
      <div className='info-cell'>
        <div className='info'>
          <div className='details'>
            <h2>
              {username}
              <div className='date'>
                <p>{moment(created_at).startOf("hour").fromNow()}</p>
              </div>
            </h2>
          </div>
          <div className='about'> {user_about}</div>
        </div>
      </div>
      {/* <div className='stats-cell'>
        <div className='count-sec'>
          <div className='counts'>
            <div className='cells'>
              <div className='column-grid'>
                <div className='head fc-black-700'>{answers_count}</div>
                <div className='foot fc-black-500'>answers</div>
              </div>
            </div>
            <div className='cells'>
              <div className='column-grid'>
                <div className='head fc-black-700'>{posts_count}</div>
                <div className='foot fc-black-500'>questions</div>
              </div>
            </div>
            <div className='cells'>
              <div className='column-grid'>
                <div className='head fc-black-700'>{comments_count}</div>
                <div className='foot fc-black-500'>comments</div>
              </div>
            </div>
            <div className='cells'>
              <div className='column-grid'>
                <div className='head fc-black-700'>{tags_count}</div>
                <div className='foot fc-black-500'>tags</div>
              </div>
            </div>
          </div>
        </div>
      </div> */}
    </div>
  </div>
)

export default ContentCard
