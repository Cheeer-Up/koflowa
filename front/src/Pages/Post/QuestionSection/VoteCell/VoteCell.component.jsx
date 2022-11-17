import React, { Fragment, useState } from "react"
import { selectUser, selectToken } from "redux/slice/AuthSlice"
import { useSelector } from "react-redux"

import "./VoteCell.styles.scss"
import "../../AnswerSection/AnswerItem/AnswerItem.styles.scss"
import { ReactComponent as UpVote } from "../../../../assets/ArrowUpLg.svg"
import { ReactComponent as DownVote } from "../../../../assets/ArrowDownLg.svg"
import { postQuestionUpdown, getQuestionUpdown } from "api/question"

const VoteCell = ({ question }) => {
  const questionSeq = question.questionSeq
  const [acToken] = useState(useSelector(selectToken))
  const [user] = useState(useSelector(selectUser))
  const [vote, setVote] = useState(question.up-question.down)
  const questionUpDown = (questionUpdownType) => {
    getQuestionUpdown(acToken, question.questionSeq).then((res)=>{
      console.log("this.res : ", res);
      const updownData = res.data.result.data
      console.log("updownData :",updownData);
      if(updownData==null){
        if (questionUpdownType=="UP"){
          setVote(vote+1)
        }else{
          setVote(vote-1)
        }
      }else{
        if(updownData.questionUpdownType=='UP'){
          if (questionUpdownType=="UP"){
            setVote(vote-1)
          }else{
            setVote(vote-2)
          }
        }else{
          if (questionUpdownType=="UP"){
            setVote(vote+2)
          }else{
            setVote(vote+1)
          }
        }
      }
    })
    postQuestionUpdown(acToken, {questionUpdownType, questionSeq})

  }

  return (
    <Fragment>
      <div className='vote-cell fc-black-800'>
        <div className='stats'>
          </div>
        <div/>
        <div className='answer-layout'>
          <div className='vote-cell'>
            <div className='vote-container'>
              <button className='vote-up' title='추천' onClick={() => questionUpDown("UP")}>
                <UpVote className='icon'/>
              </button>
                <div className='vote-count fc-black-500'>{vote}</div>
                <button className='vote-down' title='비추천'  onClick={() => questionUpDown("DOWN")}>
                  <DownVote className='icon'/>
                </button>
              </div>
            </div>
          </div>
        </div>
    </Fragment>
  )
}

export default VoteCell
