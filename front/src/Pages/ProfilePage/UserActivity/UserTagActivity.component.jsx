import React, { useEffect, useState } from "react"

import UserTag from "Pages/ProfilePage/UserActivity/UserTag.component"
import "./UserActivity.styles.scss"
import { getUserTags } from "api/mypages"

const UserTagActivity = ({ tags }) => {
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
