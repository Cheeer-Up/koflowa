import React from "react"
import { Link } from "react-router-dom"

import "./AvatarCard.styles.scss"

const AvatarCard = ({ id, profile }) => (
  <div className='img-card'>
    <div className='avatar-card'>
      <div className='avatar'>
        <Link className='avatar-link' to={`/users/${id}`}>
          <div className='logo-wrapper'>
            <img src={profile} alt='user-logo' />
          </div>
        </Link>
      </div>
    </div>
  </div>
)

export default AvatarCard
