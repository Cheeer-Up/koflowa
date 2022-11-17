import React, { Fragment, useState, useRef } from "react"
import { connect, useSelector } from "react-redux"
import PropTypes from "prop-types"
// import { addAnswer } from "../../../../redux/answers/answers.actions"
import { postAnswer } from "api/answer"
import { selectToken } from "redux/slice/AuthSlice"

import LinkButton from "../../../../components/Components/LinkButton/LinkButton.component"
import MarkdownEditor from "../../../../components/Layouts/MarkdownEditor/MarkdownEditor.component"

import "./AnswerForm.styles.scss"

const AnswerForm = ({ auth, questionSeq }) => {
  const [formData, setFormData] = useState({
    content: "",
  })
  // console.log(formData);
  const [acToken] = useState(useSelector(selectToken))
  // console.log(acToken);
  const markdownEditorRef = useRef(null)

  const { content } = formData

  const handleSubmit = async (e) => {
    e.preventDefault()
    postAnswer(acToken, questionSeq, {content})
    // console.log("content : ", {content});
    setFormData({
      content: "",
    })
    markdownEditorRef.current.cleanEditorState()
  }

  const updateConvertedContent = (htmlConvertedContent) => {
    setFormData({ ...formData, content : htmlConvertedContent })
  }

  return (
    <Fragment>
      {acToken ? (
        <Fragment>
          <form className='answer-form' onSubmit={(e) => handleSubmit(e)}>
            <div className='answer-grid'>
              <label className=' fc-black-800'>답변 작성하기</label>
              <div className='s-textarea rich-text-editor-container'>
                <MarkdownEditor ref={markdownEditorRef} onChange={updateConvertedContent} />
              </div>
              <button className='s-btn s-btn__primary'>작성하기</button>
            </div>
          </form>
        </Fragment>
      ) : (
        <Fragment>
          <LinkButton
            text={"작성하려면 로그인이 필요합니다"}
            link={"/login"}
            type={"s-btn__outlined"}
            marginTop={"12px"}
          />
        </Fragment>
      )}
    </Fragment>
  )
}

AnswerForm.propTypes = {
  auth: PropTypes.object.isRequired,
  // addAnswer: PropTypes.func.isRequired,
  // post: PropTypes.object.isRequired,
}

const mapStateToProps = (state) => ({
  auth: state.auth,
  post: state.post,
})

export default connect(mapStateToProps, { postAnswer })(AnswerForm)
