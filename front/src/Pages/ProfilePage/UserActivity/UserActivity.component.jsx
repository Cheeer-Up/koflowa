import React from "react"

import TagBadge from "components/Components/TagBadge/TagBadge.component"

import "./UserActivity.styles.scss"

const UserActivity = ({ title }) => (
  <div className='grid-cell2'>
    <div className='top-tags'>
      <h3 className='fw-bold fc-dark bc-black-3'>{title}</h3>
      <div className='top-tags-sec'></div>
    </div>
  </div>
)

export default UserActivity
