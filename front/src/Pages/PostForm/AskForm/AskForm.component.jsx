import React, { Fragment, useState, useEffect, useRef } from "react"
import MarkdownEditor from "components/Layouts/MarkdownEditor/MarkdownEditor.component"
import { badWordsFilter } from "utils/censorBadWords"
import { useNavigate } from "react-router-dom"
import { Modal, Box } from "@mui/material"

import { WithContext as ReactTags } from "react-tag-input"
import { COUNTRIES } from "./countries"

import "./AskForm.styles.scss"

// 태그 핸들용
const suggestions = COUNTRIES.map((country) => {
  return {
    id: country,
    text: country,
  }
})

const KeyCodes = {
  comma: 188,
  enter: 13,
}
const delimiters = [KeyCodes.comma, KeyCodes.enter]
// 태그 핸들용

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
    tagsData: [],
  })

  // 모달 핸들러
  const [modalOpen, setmodalOpen] = useState(false) // 모달 상태(ex 열림 닫힘 상태)
  // 모달 여는 이벤트
  const handleOpen = () => {
    setmodalOpen(true)
  }
  // 모달 닫는 이벤트
  const handleClose = () => {
    setmodalOpen(false)
  }

  const navigate = useNavigate()
  const goback = () => {
    navigate(-1)
  }
  // 모달 핸들러

  const [formErrors, setFormErrors] = useState({})

  useEffect(() => {
    setFormErrors({})
  }, [formData])

  const markdownEditorRef = useRef(null)

  const { title, body, tagsData } = formData

  const onChange = (e) => setFormData({ ...formData, [e.target.name]: e.target.value })

  // 태그 핸들
  // 렌더용 태그 변수
  const [tags, setTags] = React.useState([])
  // 태그 삭제
  const handleDelete = (i) => {
    setTags(tags.filter((tag, index) => index !== i))
    setFormData({ ...formData, tagsData: tagsData.filter((tag, index) => index !== i) })
  }
  // 태그 추가
  const handleAddition = (tag) => {
    if (tags.length < 5) {
      setTags([...tags, tag])
      setFormData({ ...formData, tagsData: [...tagsData, tag] })
    }
  }

  const handleDrag = (tag, currPos, newPos) => {
    const newTags = tags.slice()

    newTags.splice(currPos, 1)
    newTags.splice(newPos, 0, tag)

    setTags(newTags)
    setFormData({ ...formData, tagsData: newTags })
  }

  // 태그 핸들

  // 오류 검사
  const validateFormData = () => {
    const errors = []

    errors.reverse().forEach((err) => setFormErrors((prev) => ({ ...prev, ...err })))

    return errors
  }
  // 오류 검사

  const onSubmit = async (e) => {
    console.log(formData)
    e.preventDefault()

    const errors = validateFormData()

    // if there are errors, don't submit
    if (errors.length > 0) return
    addPost({ title, body, tagsData })

    setFormData({
      title: "",
      body: "",
      tagsData: [],
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
              <label className='form-label s-label'>
                {/* Title */}
                제목
                <p className='title-desc fw-normal fs-caption'>
                  {/* Be specific and imagine you’re asking a question to another person */}
                  구체적으로 다른 사람에게 질문을 하고 있다고 상상해 보세요.
                </p>
              </label>
              <input
                className='title-input s-input'
                type='text'
                name='title'
                value={title}
                onChange={(e) => onChange(e)}
                id='title'
                // placeholder='e.g. Is there an R function for finding the index of an element in a vector?'
                placeholder='예시) 벡터에서 요소의 인덱스를 찾는 R 함수가 있습니까?'
                required
              />
            </div>
            <div className='body-grid'>
              <label className='form-label s-label fc-black-800'>
                {/* Body */}
                내용
                <p className='body-desc fw-normal fs-caption fc-black-800'>
                  {/* Include all the information someone would need to answer your question */}
                  누군가가 귀하의 질문에 대답하는 데 필요한 모든 정보를 포함하십시오.
                </p>
              </label>
              <div className='s-textarea rich-text-editor-container'>
                <MarkdownEditor ref={markdownEditorRef} onChange={updateConvertedContent} />
              </div>
            </div>
            <div className='tag-grid'>
              <label className='form-label s-label'>
                {/* Tag Name */}
                태그 이름
                <p className='tag-desc fw-normal fs-caption'>
                  {/* Add up to 5 tags to describe what your question is about */}
                  질문의 내용을 설명하는 최대 5개의 태그를 추가하세요.
                </p>
              </label>
              <ReactTags
                tags={tags}
                suggestions={suggestions}
                delimiters={delimiters}
                handleDelete={handleDelete}
                handleAddition={handleAddition}
                handleDrag={handleDrag}
                inputFieldPosition='bottom'
                autocomplete
              />
              <p className='fc-error fw-bold ml8 mt4'>{formErrors.name}</p>
            </div>
          </div>
        </div>
        <div className='post-button mt32'>
          <button className='s-btn s-btn__primary' id='submit-button' name='submit-button'>
            {/* Post your question */}
            질문 개시
          </button>
          <s>`\t`</s>
        </div>
      </form>
      <button className='s-btn s-btn__danger' onClick={handleOpen}>
        {/* Post your question */}
        뒤로 가기
      </button>
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

export default AskForm
