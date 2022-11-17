import React, { Fragment } from "react"
import { useState } from "react"

import "./VoteCell.styles.scss"
import "../../AnswerSection/AnswerItem/AnswerItem.styles.scss"
import { ReactComponent as UpVote } from "../../../../assets/ArrowUpLg.svg"
import { ReactComponent as DownVote } from "../../../../assets/ArrowDownLg.svg"

const VoteCell = ({ answerCount, commentCount, tagCount }) => {
  const [vote, setVote] = useState(0)
  // console.log(answerCount, commentCount, tagCount)
  return (
    <Fragment>
      <div className='vote-cell fc-black-800'>
        <div className='stats'>
          {/* <div className='vote'>
            <span className='vote-count'>{answerCount}</span>
            <div className='count-text'>답변</div>
          </div> */}
          {/* <div className='vote'>
            <span className='vote-count'>{commentCount}0</span>
            <div className='count-text'>코멘트</div>
          </div> */}
          {/* <div className='vote'>
            <span className='vote-count'>{tagCount}</span>
            <div className='count-text'>태그</div>
          </div> */}

        <div className='answer-layout'>
          <div className='vote-cell'>
            <div className='vote-container'>
              <button className='vote-up' title='추천' >
                <UpVote className='icon'/>
              </button>
              <div className='vote-count fc-black-500'>{vote}</div>
              <button className='vote-down' title='비추천' >
                <DownVote className='icon'/>
              </button>
            </div>
          </div>
        </div>

        </div>

        
      </div>
    </Fragment>
  )
}

export default VoteCell
