// import React, { useEffect, Fragment, useState } from "react"
// import moment from "moment"
// import { connect, useSelector } from "react-redux"
// import PropTypes from "prop-types"
// import { Link } from "react-router-dom"
// import { selectUser, selectToken } from "redux/slice/AuthSlice"
// // import { getComments, deleteComment, addComment } from "../../../../redux/comments/comments.actions"
// import { postAnswerComment, putAnswerComment, deleteAnswerComment, getAnswerComment } from "api/answer"

// import Spinner from "../../../../components/Components/Spinner/Spinner.component"
// import TagBadge from "../../../../components/Components/TagBadge/TagBadge.component"
// import LinkButton from "../../../../components/Components/LinkButton/LinkButton.component"

// import "./CommentCell.styles.scss"
// import censorBadWords from "../../../../utils/censorBadWords"

// const CommentCell = ({ answer }) => {
//   const [acToken] = useState(useSelector(selectToken))
//   const [user] = useState(useSelector(selectUser))
//   const [comments, setComments] = useState(getAnswerComment(answer.seq))
//   const temp = getAnswerComment(answer.seq)
//   console.log("temp : ", temp);
//   useEffect(() => {
//     setComments(temp)
//     // eslint-disable-next-line
//   }, [getAnswerComment])

//   const [formData, setFormData] = useState({
//     type: "ANSWER",
//     boardSeq: answer.seq,
//   })

//   const { content } = formData
//   // const { boardSeq } = formData

//   const handleChange = (e) => setFormData({ ...formData, [e.target.name]: e.target.value })

//   const handleSubmit = async (e) => {
//     e.preventDefault()
//     postAnswerComment(formData)//객체 만들기
//     setFormData({
//       type: "ANSWER",
//       boardSeq: answer.seq,
//     })
//   }

//   return (
//     <Fragment>
//       <div className='comments-cell'>
//         <div className='comments'>
//           <ul className='comments-list'>
//             {comments.map((comment, index) => (
//                 <li key={index} className='comments-item'>
//                   <div className='comment-text fc-black-800'>
//                     <div className='comment-body'>
//                       <span className='body'>{censorBadWords(comment.body)}</span>
//                       &nbsp;&ndash;&nbsp;
//                       <TagBadge
//                         tag_name={comment.username}
//                         size={"s-tag"}
//                         link={`/users/${comment.user_id}`}
//                         display={"inline"}
//                       />
//                       <span
//                         title={moment(comment.created_at).fromNow(true)}
//                         style={{ color: "#959ca3 !important" }}
//                         className='date fs-body1'
//                       >
//                         {moment(comment.created_at).fromNow(true)} ago
//                       </span>
//                     </div>
//                     {comment.user_id === user.userSeq && (
//                       <Link
//                         className='s-tag s-tag__moderator'
//                         style={{ marginTop: "4px" }}
//                         title='Delete the comment'
//                         onClick={(e) => deleteAnswerComment(comment.seq)}
//                         // to={`/questions/${question.questionSeq}`}
//                       >
//                         delete
//                       </Link>
//                     )}
//                   </div>
//                 </li>
//               ))
//             }
//           </ul>
//         </div>
//         <div className='add-comment'>
//           {acToken ? (
//             <Fragment>
//               <form className='comment-form' onSubmit={(e) => handleSubmit(e)}>
//                 <div>
//                   <input
//                     className='title-input s-input'
//                     type='text'
//                     name='content'
//                     value={content}
//                     onChange={(e) => handleChange(e)}
//                     id='title'
//                     placeholder='Leave a comment'
//                   />
//                 </div>
//               </form>
//             </Fragment>
//           ) : (
//             <Fragment>
//               <LinkButton text={"You need to login to add a comment"} link={"/login"} />
//             </Fragment>
//           )}
//         </div>
//       </div>
//     </Fragment>
//   )
// }

// CommentCell.propTypes = {
//   // auth: PropTypes.object.isRequired,
//   // post: PropTypes.object.isRequired,
//   // addComment: PropTypes.func.isRequired,
//   // deleteComment: PropTypes.func.isRequired,
//   // getComments: PropTypes.func.isRequired,
//   // comment: PropTypes.object.isRequired,
// }

// const mapStateToProps = (state) => ({
//   auth: state.auth,
//   post: state.post,
//   comment: state.comment,
// })

// export default connect(mapStateToProps, {
//   // deleteComment,
//   // getComments,
//   // addComment,
// })(CommentCell)
