import React, { Fragment, useState, useEffect } from "react"
import { useSelector, useDispatch } from "react-redux"

import TagsWidget from "./TagsWidget/TagsWidget.component"
import CustomTagWidget from "./CustomTagWidget/CustomTagWidget.component"
import { selectToken } from "redux/slice/AuthSlice"
import {
  getIgnoreTagList,
  postIgnoreTag,
  deleteIgnoreTag,
  getWatchTagList,
  postWatchTag,
  deleteWatchTag,
  getAllTagsStringList,
} from "api/tags"
import { setTags } from "redux/slice/TagSlice"

import "./RightSideBar.styles.scss"

const RightSideBar = () => {
  // redux로부터 accessToken을 받아옴
  const [acToken] = useState(useSelector(selectToken))
  const dispatch = useDispatch()

  useEffect(() => {
    // 모든 태그 리스트 받아옴
    getAllTagsStringList().then((result) => {
      dispatch(setTags(result.data.result.data))
    })
  }, [])

  return (
    <Fragment>
      <div id='sidebar' className='side-bar'>
        <CustomTagWidget
          name='주시 태그'
          token={acToken}
          getApi={getWatchTagList}
          postApi={postWatchTag}
          deleteApi={deleteWatchTag}
        />
        <CustomTagWidget
          name='숨김 태그'
          token={acToken}
          getApi={getIgnoreTagList}
          postApi={postIgnoreTag}
          deleteApi={deleteIgnoreTag}
        />
        <TagsWidget />
      </div>
    </Fragment>
  )
}

export default RightSideBar
