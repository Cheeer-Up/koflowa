import React, { Fragment, useState } from "react"
import { useSelector, useDispatch } from "react-redux"
import { WithContext as ReactTags } from "react-tag-input"
import TagList from "../TagList.component"
// import { deleteWatchTag, postWatchTag } from "../../../../../api/tags" 주시태그 삭제, 추가 api
// import { } tag슬라이스에 주시태그를 얻는액션

function WatchedTagWidget() {
  // 태그 핸들 변수
  // const { watchTags } = useSelector(주시 태그를 얻는 액션)

  // dispatch용 useDispatch
  const dispatch = useDispatch()

  // COUNTRIES 대신에 watchTags
  // const suggestions = COUNTRIES.map((tag) => {
  //   return {
  //     id: tag,
  //     text: tag,
  //   }
  // })

  // 임시로 만든 suggestions 변수
  const tempList = ["java", "python", "c", "c++"]
  const suggestions = tempList.map((tag) => {
    return {
      id: tag,
      text: tag,
    }
  })
  // 임시로 만든 suggestions 변수

  const KeyCodes = {
    comma: 188,
    enter: 13,
  }
  const delimiters = [KeyCodes.comma, KeyCodes.enter]
  // 태그 핸들 변수

  // 태그 핸들 함수

  // 렌더용 태그 변수
  const [tags, setTags] = React.useState([])

  // 태그 삭제
  const handleDelete = (i) => {
    setTags(tags.filter((tag, index) => index !== i))
    // 주시 태그 삭제 api 호출
    // dispatch(주시 삭제 리듀서(필요한 값))
    // deleteWatchTag()
  }

  // 태그 추가
  const handleAddition = (tag) => {
    if (suggestions.includes(tag)) {
      setTags([...tags, tag])
      // 주시 태그 추가 api 호출
      // dispatch(api리듀서(필요한 값))
    }
  }
  // 태그 핸들 함수

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
          <span>주시 태그</span>
          {edit ? <a onClick={handleNotEdit}>종료</a> : <a onClick={handleEdit}>수정</a>}
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

export default WatchedTagWidget
