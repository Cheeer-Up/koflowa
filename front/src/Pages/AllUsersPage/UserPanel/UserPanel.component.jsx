import React, { Fragment } from "react"
import { Link } from "react-router-dom"

import "./UserPanel.styles.scss"

const UserPanel = ({ user: { seq, email, nickname, profile, createdTime, reputationScore } }) => {
  return (
    <Fragment>
      <div className='user-panel-info s-card bs-sm h:bs-md fc-black-500'>
        <div className='user-gravatar'>
          <Link to={{ pathname: `/users/${seq}` }}>
            <div className='logo-wrapper'>
              <img alt='user-logo' src={profile} />
            </div>
          </Link>
        </div>
        <div className='user-details'>
          <Link className='fc-blue-600' to={`/users/${seq}`}>
            {nickname}
          </Link>
          <span className='item'>
            <span className='count-info'>{email}</span>
          </span>
          <span className='item'>
            <span className='count'>
              {reputationScore} <span className='count-info'> 명성 </span>
            </span>
          </span>
          {/* <span className='item'>
            <span className='count-info'>
              {createdTime[0]}.{createdTime[1]}.{createdTime[2]}{" "}
            </span>
          </span> */}
        </div>
      </div>
    </Fragment>
  )
}

// UserPanel.propTypes = {
//   user: PropTypes.object.isRequired,
// }

export default UserPanel
