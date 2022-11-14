import React, { useRef } from "react"

import "./AvatarCard.styles.scss"

const AvatarCard = ({ id, profile }) => {
  const fileInput = useRef(null)
  const onChange = (e) => {
    if (e.target.files[0]) {
      console.log(e.target.files[0])
    } else {
      return
    }
  }
  return (
    <div className='img-card'>
      <div className='avatar-card'>
        <div className='avatar'>
          <div className='avatar-link'>
            <div className='logo-wrapper'>
              <img src={profile} alt='user-logo' onClick={() => fileInput.current.click()} />
              <input type='file' accept='image/jpg,impge/png,image/jpeg' onChange={onChange} ref={fileInput} />
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

export default AvatarCard
