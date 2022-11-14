import React, { Fragment, useState, useEffect, useRef } from "react"
import PropTypes from "prop-types"
import MarkdownEditor from "components/Layouts/MarkdownEditor/MarkdownEditor.component"
import { badWordsFilter } from "utils/censorBadWords"
import { useNavigate } from "react-router-dom"
import { Modal, Box } from "@mui/material"

import "./AskForm.styles.scss"

const de = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 400,
  bgcolor: "#3c4146",
  boxShadow: 24,
  borderRadius: "15px",
  pt: 2,
  px: 4,
  pb: 3,
}

const AskForm = ({ addPost }) => {
  const [formData, setFormData] = useState({
    title: "",
    body: "",
    name: "",
  })

  const [modalOpen, setmodalOpen] = useState(false)
  const handleOpen = () => {
    setmodalOpen(true)
  }
  const handleClose = () => {
    setmodalOpen(false)
  }

  const navigate = useNavigate()

  const goback = () => {
    navigate(-1)
  }

  const [formErrors, setFormErrors] = useState({})

  useEffect(() => {
    setFormErrors({})
  }, [formData])

  const markdownEditorRef = useRef(null)

  const { title, body, name } = formData

  const onChange = (e) => setFormData({ ...formData, [e.target.name]: e.target.value })

  const validateFormData = () => {
    const errors = []

    const tags = formData.name
      .split(",")
      .filter(Boolean)
      .map((tag) => tag.trim())

    tags.forEach((tag) => {
      if (tag.length > 25) {
        errors.push({
          name: `태그이름은 25글자 보다 길어질수 없습니다.`,
          // name: `A tag name can't be longer than 25 characters.`,
        })
      } else if (/[^a-zA-Z]/.test(tag)) {
        errors.push({
          // name: `${tag} tag must contain English alphabets only (no spaces).`,
          name: `${tag} 태그는 항상 알파벳으로만 이루어져 있고 공백이 있어선 안됩니다.`,
        })
      }
    })

    if (badWordsFilter.isProfane(formData.name)) {
      // errors.push({ name: "Inappropriate words are not allowed." })
      errors.push({ name: "부적절한 단어는 허용되지 않습니다." })
    }

    errors.reverse().forEach((err) => setFormErrors((prev) => ({ ...prev, ...err })))

    return errors
  }

  const onSubmit = async (e) => {
    e.preventDefault()

    const errors = validateFormData()

    // if there are errors, don't submit
    if (errors.length > 0) return

    addPost({ title, body, name })

    setFormData({
      title: "",
      body: "",
      name: "",
    })
    markdownEditorRef.current.cleanEditorState()
  }

  const updateConvertedContent = (htmlConvertedContent) => {
    setFormData({ ...formData, body: htmlConvertedContent })
  }

  return (
    <Fragment>
      <form onSubmit={(e) => onSubmit(e)}>
        <div className='question-form p16 s-card'>
          <div className='question-layout'>
            <div className='title-grid'>
              <label className='form-label s-label'>이름</label>
              <input
                className='title-input s-input'
                type='text'
                name='title'
                value={title}
                onChange={(e) => onChange(e)}
                id='title'
                placeholder='예시) Springboot'
                required
              />
            </div>
            <div className='body-grid'>
              <label className='form-label s-label fc-black-800'>내용</label>
              <div className='s-textarea rich-text-editor-container'>
                <MarkdownEditor ref={markdownEditorRef} onChange={updateConvertedContent} />
              </div>
            </div>
          </div>
        </div>
        <div className='tagform-bottom'>
          <div className='post-button mt32'>
            <button className='s-btn s-btn__primary' id='submit-button' name='submit-button'>
              태그 생성
            </button>
          </div>
          <button className='s-btn s-btn__danger' onClick={handleOpen}>
            뒤로 가기
          </button>
        </div>
      </form>
      <Modal
        open={modalOpen}
        onClose={handleClose}
        aria-labelledby='parent-modal-title'
        aria-describedby='parent-modal-description'
      >
        <Box sx={{ ...de, width: 400 }}>
          <h2 id='parent-modal-title'>질문 삭제</h2>
          <p>이 질문을 삭제하시겠습니까? 질문이 삭제되면 취소 할 수 없습니다.</p>
          <button className='s-btn s-btn__danger' onClick={goback}>
            뒤로가기
          </button>
          <button className='s-btn' onClick={handleClose}>
            취소
          </button>
        </Box>
      </Modal>
    </Fragment>
  )
}

AskForm.propTypes = {
  // addPost: PropTypes.func.isRequired,
}

export default AskForm
