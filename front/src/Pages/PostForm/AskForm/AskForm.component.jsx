import React, { Fragment, useState, useEffect, useRef } from "react"
import { useSelector, useDispatch } from "react-redux"
import MarkdownEditor from "components/Layouts/MarkdownEditor/MarkdownEditor.component"
import { useNavigate } from "react-router-dom"
import { Modal, Box } from "@mui/material"

import { WithContext as ReactTags } from "react-tag-input"
import { postQuestion } from "api/question"
import { getAllTagsStringList } from "api/tags"
import { selectTags, setTags } from "redux/slice/TagSlice"
import { selectToken } from "redux/slice/AuthSlice"

import "./AskForm.styles.scss"

const de = {
  position: "absolute",
  top: "40%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 400,
  bgcolor: "#ffffff",
  boxShadow: 24,
  borderRadius: "15px",
  pt: 2,
  px: 4,
  pb: 3,
}

const AskForm = () => {
  const dispatch = useDispatch()
  const [acToken] = useState(useSelector(selectToken))
  const [suggestionList] = useState(useSelector(selectTags))

  // 태그 핸들용
  const suggestions = suggestionList.map((tag) => {
    return {
      id: tag,
      text: tag,
    }
  })

  const KeyCodes = {
    comma: 188,
    enter: 13,
  }
  const delimiters = [KeyCodes.comma, KeyCodes.enter]
  // 태그 핸들용

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
    // setFormErrors({})
    getAllTagsStringList().then((result) => {
      dispatch(setTags(result.data.result.data))
    })
  }, [])

  const markdownEditorRef = useRef(null)

  const { title, body, tagsData } = formData

  const onChange = (e) => setFormData({ ...formData, [e.target.name]: e.target.value })

  // 태그 핸들
  // 렌더용 태그 변수
  const [tagsList, setTagsList] = React.useState([])
  // 태그 삭제
  const handleDelete = (i) => {
    setTagsList(tagsList.filter((tag, index) => index !== i))
    setFormData({ ...formData, tagsData: tagsData.filter((tag, index) => index !== i) })
  }
  // 태그 추가
  const handleAddition = (tag) => {
    if (tagsList.length < 5 && !tagsList.includes(tag) && suggestionList.includes(tag.text)) {
      setTagsList([...tagsList, tag])
      console.log(tag)
      setFormData({ ...formData, tagsData: [...tagsData, tag.text] })
    }
  }

  const handleDrag = (tag, currPos, newPos) => {
    const newTags = tagsList.slice()

    newTags.splice(currPos, 1)
    newTags.splice(newPos, 0, tag)

    setTagsList(newTags)
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
    e.preventDefault()

    const errors = validateFormData()

    // if there are errors, don't submit
    if (errors.length > 0) return

    console.log(formData)
    postQuestion(acToken, {
      questionTitle: title,
      questionContent: body,
      tagList: tagsData,
    }).then((result) => {
      navigate("/questions/" + result.data.result.data.questionSeq)
    })

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
  const postThread = () => {}
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
                  다른사람들이 질문을 이해하기 쉽게 가능한한 많은 정보를 넣어주세요.
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
                <p className='tag-desc fw-normal fs-caption'>질문의 내용을 설명하는 최대 5개의 태그를 추가해 보세요.</p>
              </label>
              <ReactTags
                tags={tagsList}
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
