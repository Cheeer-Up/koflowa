import React, { Fragment } from "react"
import moment from "moment"
import { Link } from "react-router-dom"

import "./UserCard.styles.scss"

const UserCard = ({
  created_at,
  user_id,
  gravatar,
  username,
  dateType,
  float,
  backgroundColor,
}) => {
  return (
    <Fragment>
      <div className='owner' style={{ float: float, backgroundColor: backgroundColor }}>
        <div className='user-block fc-black-500'>
          <div className='action-time'>
            {/* {dateType ? dateType : ""} {moment(created_at.slice(0, 4), "YYYYMMDDHH").fromNow(false)} */}
            {/* 복구용*/}
            {moment(created_at).fromNow(false)} {/* 실제로 들어갈 코드*/}
          </div>
          <div className='user-logo'>
            <Link className='user-link' to={`/users/${user_id}`}>
              <div className='logo-wrapper'>
                <img alt='user_logo' src={gravatar} />
              </div>
            </Link>
          </div>
          <div className='user-profile'>
            <Link className='user-profile-link fc-blue-600' to={`/users/${user_id}`}>
              {username}
            </Link>
          </div>
        </div>
      </div>
    </Fragment>
  )
}

export default UserCard
