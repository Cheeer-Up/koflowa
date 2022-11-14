import React, { useEffect, useState } from "react"
import { getUserProfile, getUserTags, getAuthUserProfile, getUserquestion, getUserAnswer } from "api/mypages"

import UserTag from "Pages/ProfilePage/UserActivity/UserTag.component"
import "./UserActivity.styles.scss"

const UserTagActivity = ({ userSeq }) => {
  const [tags, setTags] = useState([])
  const accessToken = localStorage.getItem("accessToken")

  useEffect(() => {
    getUserTags(accessToken, userSeq).then((data) => {
      const payload = data.data.result.data
      setTags(payload)
    })
  }, [])
  return (
    <div className='grid-cell2'>
      <div className='top-tags'>
        <h3 className='fw-bold fc-dark bc-black-3'>Top Tags</h3>
        <div className='top-tags-sec'>
          {tags.map((tag, index) => (
            <UserTag tag={tag} key={index} />
          ))}
        </div>
      </div>
    </div>
  )
}

export default UserTagActivity
