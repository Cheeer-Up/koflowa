import React, { useEffect, useRef, useState } from "react"

import "./AvatarCard.styles.scss"
import { useDispatch, useSelector } from "react-redux"
import { selectUser, selectEdit, selectFile, setFile } from "redux/slice/AuthSlice.js"
import { useLocation } from "react-router-dom"

const AvatarCard = ({ id, profile }) => {
  const fileInput = useRef(null)
  const [image, setImage] = useState(profile)
  const userSeq = useLocation().pathname.split("/")[2]

  const dispatch = useDispatch()
  let loginUser = useSelector(selectUser)
  let file = useSelector(selectFile)
  let globalEdit = useSelector(selectEdit) // 하위 컴포넌트 수정 사항을 위해 전체 적으로 관리

  useEffect(() => {
    Number(userSeq) === Number(loginUser.seq) ? setImage(loginUser.profile) : setImage(profile)
  }, [])

  const onChange = (e) => {
    if (e.target.files[0]) {
      dispatch(setFile(e.target.files[0]))
    } else {
      return
    }

    const reader = new FileReader()
    reader.onload = () => {
      if (reader.readyState === 2) {
        setImage(reader.result)
      }
    }
    reader.readAsDataURL(e.target.files[0])
  }

  return (
    <div className='img-card'>
      <div className='avatar-card'>
        <div className='avatar'>
          <div className='avatar-link'>
            <div className='logo-wrapper'>
              <img src={image} alt='user-logo' onClick={() => (globalEdit ? fileInput.current.click() : "")} />
              <input
                className='display'
                type='file'
                accept='image/jpg,impge/png,image/jpeg'
                onChange={onChange}
                ref={fileInput}
              />
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

export default AvatarCard
