import React, { Fragment, useState } from "react"
import moment from "moment"
import { Link, useNavigate } from "react-router-dom"
import { create_Chk_With_Room } from "api/talk"
import { useSelector } from "react-redux"
import { selectUser, selectToken } from "redux/slice/AuthSlice"

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
  let navigate = useNavigate()
  const [acToken] = useState(useSelector(selectToken))
  const [user] = useState(useSelector(selectUser))
  const [ style, setStyle ] = useState({display: 'none'})
  const create_ChatRoom = (oppSeq) =>{
    create_Chk_With_Room(acToken,oppSeq).then(()=>{
      navigate('/talk')
    })
  }
  
  return (
    <Fragment>
      <div className='owner' style={{ float: float, backgroundColor: backgroundColor }}>
        <div className='user-block fc-black-500'>
          <div className='action-time'>{moment(created_at).fromNow(false)}</div>
          <div className='user-logo'>
            <Link className='user-link' to={`/users/${user_id}`}>
              <div className='logo-wrapper'>
                <img alt='user_logo' src={gravatar} />
              </div>
            </Link>
          </div>
          <div className='user-profile'>
            {/* <Link className='user-profile-link fc-blue-600' to={`/users/${user_id}`}>
              {username}
            </Link> */}
            
            <div>
              <div className='userIconGnb'
                onMouseEnter={e => {
                              setStyle({display: 'block'})
                    }}
                onMouseLeave={e => {
                              setStyle({display: 'none'})
                }}>
                <Link className='user-profile-link fc-blue-600' to={`/users/${user_id}`}>
                  {username}
                </Link>
                <ul style={style} className="custom">
                  <li><Link className='user-profile-link fc-blue-600' to={`/users/${user_id}`}>
                      회원정보
                    </Link>
                  </li>
                  {user_id!==user.seq ? (
                    <li className='user-profile-link fc-blue-600' onClick={()=>create_ChatRoom(user_id)}>
                    코톡 보내기
                    </li>
                      ):(
                        <div></div>
                  )}
                  
                </ul> 
              </div>
            </div>

          </div>
          
          
        </div>
      </div>
    </Fragment>
  )
}

export default UserCard
