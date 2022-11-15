import React, { useEffect, useRef } from "react"
import "moment/locale/ko"
import moment from "moment"
import { useLocation } from "react-router-dom"

import "./ContentCard.styles.scss"
import { useDispatch, useSelector } from "react-redux"
import { selectUser, selectEdit, setIsEdit, selectNewInfo, setNewInfo } from "redux/slice/AuthSlice.js"
import { useState } from "react"

const ContentCard = ({ nickname, setNickname, about, setAbout, created_at }) => {
  const dispatch = useDispatch()
  let isEdit = useSelector(selectEdit) // 하위 컴포넌트 수정 사항을 위해 전체 적으로 관리
  let newInfo = useSelector(selectNewInfo)
  let loginUser = useSelector(selectUser)

  useEffect(() => {
    // loginUser ===
  }, [])

  const saveNickname = (e) => {
    setNickname(e.target.value)
    dispatch(
      setNewInfo({
        nickname: e.target.value,
        about,
      })
    )
  }

  const saveAbout = (e) => {
    setAbout(e.target.value)
    dispatch(
      setNewInfo({
        nickname,
        about: e.target.value,
      })
    )
  }

  return (
    <div className='content-card'>
      <div className='content-grid'>
        <div className='info-cell'>
          <div className='info'>
            <div className='details'>
              <h2>
                {isEdit ? "" : nickname}
                <input
                  className={isEdit ? "" : "display"}
                  name='nickname'
                  placeholder={nickname}
                  onChange={saveNickname}
                  value={nickname}
                />
                <div className='date'>
                  <p>{moment(created_at).startOf("hour").fromNow()}</p>
                </div>
              </h2>
            </div>
            <div className={isEdit ? "display" : "about"}> {isEdit ? "" : about}</div>
            <input
              type='text'
              className={isEdit ? "" : "display"}
              name='about'
              placeholder={about}
              onChange={saveAbout}
              value={about}
            />
          </div>
        </div>
      </div>
    </div>
  )
}

export default ContentCard
