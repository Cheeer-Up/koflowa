import React, { useEffect, Fragment, useState } from "react"
import { Link, useLocation, useParams } from "react-router-dom"
import { getUserProfile } from "api/mypages"

import UserSection from "./UserSection/UserSection.component"
import Spinner from "components/Components/Spinner/Spinner.component"
import ExternalUserDetails from "./ExternalUserDetails/ExternalUserDetails.component"
import UserActivity from "./UserActivity/UserActivity.component"

import "./ProfilePage.styles.scss"

const ProfilePage = () => {
  const [user, setUser] = useState({})
  const [loading, setLoading] = useState(true)
  const userSeq = useLocation().pathname.split("/")[2]

  useEffect(() => {
    getUserProfile(userSeq).then((data) => {
      const payload = data.data.result.data
      setUser(payload)
      setLoading(false)
    })
  }, [])

  return loading || user === null ? (
    <Spinner type='page' width='75px' height='200px' />
  ) : (
    <Fragment>
      <div id='mainbar' className='user-main-bar pl24 pt24'>
        <div className='user-card'>
          <div className='grid--cell s-navigation mb16'>
            <Link to='#' className='s-navigation--item is-selected' data-shortcut='P'>
              Profile
            </Link>
            <Link to='#' className='s-navigation--item' data-shortcut='A'>
              Activity
            </Link>
          </div>
          <UserSection user={user} />
        </div>
        <div className='row-grid'>
          {/* <ExternalUserDetails /> */}
          <UserActivity />
        </div>
      </div>
    </Fragment>
  )
}

export default ProfilePage
