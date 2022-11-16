import React, { Fragment, useState, useEffect, useRef } from "react"
import MarkdownEditor from "components/Layouts/MarkdownEditor/MarkdownEditor.component"
import { badWordsFilter } from "utils/censorBadWords"
import { useNavigate } from "react-router-dom"
import { Modal, Box } from "@mui/material"
import { postRegistTag } from "api/tags"
import { useSelector } from "react-redux"
import { selectToken } from "redux/slice/AuthSlice"

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

const AskForm = () => {
  const token = useSelector(selectToken)
  const [formData, setFormData] = useState({
    title: "",
    body: "",
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

  const { title, body } = formData

  const onChange = (e) => setFormData({ ...formData, [e.target.name]: e.target.value })

  const validateFormData = () => {
    const errors = []

    const tags = formData.title
      .split(",")
      .filter(Boolean)
      .map((tag) => tag.trim())

    tags.forEach((tag) => {
      if (tag.length > 25) {
        errors.push({
          title: `태그이름은 25글자 보다 길어질수 없습니다.`,
        })
      } else if (/[^a-zA-Z]/.test(tag)) {
        errors.push({
          title: `${tag} 태그는 항상 알파벳으로만 이루어져 있고 공백이 있어선 안됩니다.`,
        })
      }
    })

    if (badWordsFilter.isProfane(formData.title)) {
      errors.push({ title: "부적절한 단어는 허용되지 않습니다." })
    }

    errors.reverse().forEach((err) => setFormErrors((prev) => ({ ...prev, ...err })))

    return errors
  }

  const onSubmit = async (e) => {
    e.preventDefault()

    const errors = validateFormData()

    // if there are errors, don't submit
    if (errors.length > 0) return

    postRegistTag(token, {
      name: title,
      description: body,
    }).then(() => {
      navigate("/tags/" + title)
    })

    setFormData({
      title: "",
      body: "",
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
          <h2 id='parent-modal-title'>등록 취소</h2>
          <p>페이지를 나가면 작성중인 내용은 저장되지 않습니다.</p>
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

export default AskForm
