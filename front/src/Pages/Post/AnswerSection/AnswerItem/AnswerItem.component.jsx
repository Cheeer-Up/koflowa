import React, { Fragment, useState } from "react"
import { connect } from "react-redux"
import PropTypes from "prop-types"
import { Link } from "react-router-dom"
// import { deleteAnswer } from "../../../../redux/answers/answers.actions"
import { useSelector, useDispatch } from "react-redux"
import { deleteAnswer, answerUpdown, getAnswerUpDown, answerAccept } from "api/answer"

import { ReactComponent as UpVote } from "../../../../assets/ArrowUpLg.svg"
import { ReactComponent as DownVote } from "../../../../assets/ArrowDownLg.svg"
import UserCard from "../../../../components/Components/UserCard/UserCard.component"
import CommentCellComponent from "Pages/Post/QuestionSection/CommentCell/CommentCell.component"

import "./AnswerItem.styles.scss"
import censorBadWords from "../../../../utils/censorBadWords"
import { selectUser, selectToken } from "redux/slice/AuthSlice"
import { setIsEdit } from "redux/slice/AnswerSlice"

const AnswerItem = ({
  answer,
  question,
  // answer: { accept, content, up, down, createdTime, modifiedTime, seq },
  // post: { post },
  // auth,
}) => {
  const [acToken] = useState(useSelector(selectToken))
  const [user] = useState(useSelector(selectUser))
  const [vote, setVote] = useState(answer.up-answer.down)
  const dispatch = useDispatch()
  const answerUpDown = (type) => {
    getAnswerUpDown(acToken, answer.seq).then((res)=>{
      const updownData = res.data.result.data
      if(updownData==null){
        if (type=="UP"){
          setVote(vote+1)
        }else{
          setVote(vote-1)
        }
      }else{
        if(updownData.type=='UP'){
          if (type=="UP"){
            setVote(vote-1)
          }else{
            setVote(vote-2)
          }
        }else{
          if (type=="UP"){
            setVote(vote+2)
          }else{
            setVote(vote+1)
          }
        }
      }
      // const payload = res.data.result.data
      // setAnswer(payload)
      // setLoading(false)
    })
    answerUpdown(acToken, answer.seq, {type})
    // if (type=="UP"){
    //   setVote(vote+1)
    // }else{
    //   setVote(vote-1)
    // }
  }

  const acceptAnswer = (answerSeq)=>{
    answerAccept(acToken, answerSeq)
    dispatch(setIsEdit())
  }

  return (
    <Fragment>
      <div className='answer-layout'>
        <div className='vote-cell'>
          <div className='vote-container'>
            <button className='vote-up' title='추천' onClick={() =>answerUpDown("UP")}>
              <UpVote className='icon'/>
            </button>
            <div className='vote-count fc-black-500'>{vote}</div>
            <button className='vote-down' title='비추천' onClick={() => answerUpDown("DOWN")}>
              <DownVote className='icon'/>
            </button>
            {user!==null ? (
              question.user.seq===user.seq? (
                answer.accept===true ? (
                  <div title='이 답변을 채택하셨습니다'>
                    <svg aria-hidden="true" class="svg-icon iconCheckmarkLg fc-green-500" width="36" height="36" viewBox="0 0 36 36" color="#999"><path d="m6 14 8 8L30 6v8L14 30l-8-8v-8Z"></path></svg>
                  </div>
                ):(
                  <div title='이 답변을 채택하시겠습니까?' onClick={()=>acceptAnswer(answer.seq)}>
                    <svg aria-hidden="true" class="svg-icon iconCheckmarkLg fc-green-200" width="36" height="36" viewBox="0 0 36 36" color="#999"><path d="m6 14 8 8L30 6v8L14 30l-8-8v-8Z"></path></svg>
                  </div>
                )
                
              ):(
                answer.accept==true ? (
                  <div title='질문자가 이 답변을 채택하였습니다'>
                    <svg aria-hidden="true" class="svg-icon iconCheckmarkLg fc-green-500" width="36" height="36" viewBox="0 0 36 36" color="#999"><path d="m6 14 8 8L30 6v8L14 30l-8-8v-8Z"></path></svg>
                  </div>
                ):(
                  <div></div>
                )
              )
            ):(
              answer.accept==true ? (
                <div title='질문자가 이 답변을 채택하였습니다'>
                  <svg aria-hidden="true" class="svg-icon iconCheckmarkLg fc-green-500" width="36" height="36" viewBox="0 0 36 36" color="#999"><path d="m6 14 8 8L30 6v8L14 30l-8-8v-8Z"></path></svg>
                </div>
              ):(
                <div></div>
              )
            )}
            
            
          </div>
        </div>
        <div className='answer-item'>
          <div className='answer-content fc-black-800' dangerouslySetInnerHTML={ {__html: answer.content} }>
          </div>
          <div className='answer-actions'>
            <div className='action-btns'>
              <div className='answer-menu'>
                {/* <Link className='answer-links' title='short permalink to this question' to='/'>
                  share
                </Link>
                <Link
                  className='answer-links'
                  title='Follow this question to receive notifications'
                  to='/'
                >
                  follow
                </Link> */}
                {user!==null&&answer.userSeq === user.seq && (
                  <Link
                    className='s-link s-link__danger'
                    style={{ paddingLeft: "4px" }}
                    title='Delete the answer'
                    onClick={(e) => deleteAnswer(acToken,answer.seq)}
                    to={`/questions/${answer.questionSeq}`}
                  >
                    delete
                  </Link>
                )}
              </div>
            </div>
            <UserCard
              created_at={answer.createdTime}
              user_id={answer.userSeq}
              gravatar={answer.profile}
              username={answer.userNickname}
              dateType={"answered"}
              backgroundColor={"transparent"}
            />
          </div>

          {/* <CommentCellComponent answer={answer}/> */}

        </div>
      </div>
    </Fragment>
  )
}

AnswerItem.propTypes = {
//   // auth: PropTypes.object.isRequired,
//   // post: PropTypes.object.isRequired,
  answer: PropTypes.object.isRequired,
//   // deleteAnswer: PropTypes.func.isRequired,
}

// const mapStateToProps = (state) => ({
//   auth: state.auth,
//   post: state.post,
// })

// export default connect(mapStateToProps, { deleteAnswer })(AnswerItem)
export default AnswerItem
