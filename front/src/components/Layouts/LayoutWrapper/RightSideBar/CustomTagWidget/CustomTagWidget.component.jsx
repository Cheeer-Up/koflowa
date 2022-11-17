import React, { Fragment, useState, useEffect } from "react"
import { useSelector } from "react-redux"

import { WithContext as ReactTags } from "react-tag-input"
import TagList from "../TagList.component"
import { selectTags } from "redux/slice/TagSlice"

function CustomTagWidget({ name, token, getApi, postApi, deleteApi }) {
  const [tags, setTags] = useState([]) // 렌더용 태그 변수
  const [suggestionList] = useState(useSelector(selectTags))

  useEffect(() => {
    // 로그인 되어있는 경우에만 customTag 리스트 가져와야함
    if (token) {
      getApi(token).then((result) => {
        setTags(
          result.data.result.data.map((tag) => {
            return {
              id: tag,
              text: tag,
            }
          })
        )
      })
    }
  }, [])

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

  // 태그 삭제
  const handleDelete = (i) => {
    const tag = tags.filter((tag, index) => index === i)[0].text
    setTags(tags.filter((tag, index) => index !== i))

    // 태그 삭제 api 호출
    deleteApi(token, tag).then((result) => {
      console.log(result)
    })
  }

  // 태그 추가
  const handleAddition = (tag) => {
    if (suggestions.includes(tag)) {
      setTags([...tags, tag])

      // 태그 추가 api 호출
      postApi(token, tag.text).then((result) => {
        console.log("---", tag, result)
      })
    }
  }

  // 태그 수정용 변수
  const [edit, setEdit] = useState(false)

  // 태그 수정용 핸들
  const handleEdit = () => {
    setEdit(true)
  }
  // 태그 수정종료용 핸들
  const handleNotEdit = () => {
    setEdit(false)
  }

  return (
    <Fragment>
      <div className='widget'>
        <div className='s-sidebarwidget--header'>
          <span>{name}</span>
          {edit ? <a onClick={handleNotEdit}> 종료</a> : <a onClick={handleEdit}> 수정</a>}
        </div>
        <div className='widget-content fc-black-800'>
          <ol className='step-section'>
            <li
              style={{
                borderBottomRightRadius: "3px",
                borderBottomLeftRadius: "3px",
              }}
              className='step except-step'
            >
              <div className='inst'>
                {edit ? (
                  <ReactTags
                    tags={tags}
                    suggestions={suggestions}
                    delimiters={delimiters}
                    handleDelete={handleDelete}
                    handleAddition={handleAddition}
                    inputFieldPosition='bottom'
                    autocomplete
                  />
                ) : (
                  <TagList tags={tags} />
                )}
              </div>
            </li>
          </ol>
        </div>
      </div>
    </Fragment>
  )
}

export default CustomTagWidget
