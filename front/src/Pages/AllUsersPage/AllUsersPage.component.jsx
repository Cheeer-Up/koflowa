import React, { Fragment, useEffect, useState } from "react"

import UserPanel from "Pages/AllUsersPage/UserPanel/UserPanel.component"
import Spinner from "components/Components/Spinner/Spinner.component"
import SearchBox from "components/Components/SearchBox/SearchBox.component"
import Pagination from "components/Layouts/Pagination/Pagination.component"

import { getAllProfile } from "api/mypages"

import "./AllUsersPage.styles.scss"

const AllUsersPage = () => {
  const [loading, setLoading] = useState(true)
  const [users, setUsers] = useState([])
  const [page, setPage] = useState(1)
  const [totalPage, setTotalPage] = useState(1)
  const size = 10
  const sort = "reputationScore,desc"

  useEffect(() => {
    getAllProfile(page - 1, size, sort).then((data) => {
      const payload = data.data.result.data
      setUsers(payload.content)
      setTotalPage(payload.totalPages)
      setLoading(false)
    })
  }, [page])
  const handlePaginationChange = (e, value) => {
    setPage(value)
  }

  const handleChange = (e) => {
    e.preventDefault()
  }

  return loading || users === null ? (
    <Spinner type='page' width='75px' height='200px' />
  ) : (
    <Fragment>
      <div id='mainbar' className='users-page fc-black-800'>
        <h1 className='headline'>사용자</h1>
        <div className='headline-count'>
          <span>{new Intl.NumberFormat("en-IN").format(users.length)} 사용자</span>
        </div>
        <div className='users-box pl16 pr16 pb16'>
          <SearchBox placeholder={"사용자 검색"} handleChange={handleChange} width={"200px"} />
        </div>
        <div className='user-browser'>
          <div className='grid-layout'>
            {users.map((user, index) => (
              <UserPanel key={index} user={user} />
            ))}
          </div>
        </div>
        <Pagination page={page} count={totalPage} handlePaginationChange={handlePaginationChange} />
      </div>
    </Fragment>
  )
}

export default AllUsersPage
