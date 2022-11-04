import React, { Fragment, useEffect, useState } from "react"
import { connect } from "react-redux"
import PropTypes from "prop-types"
import { getUsers } from "../../redux/users/users.actions"
import handleSorting from "../../utils/handleSorting"

import UserPanel from "./UserPanel/UserPanel.component"
import Spinner from "../../components/Components/Spinner/Spinner.component"
import SearchBox from "../../components/Components/SearchBox/SearchBox.component"
import ButtonGroup from "../../components/Components/ButtonGroup/ButtonGroup.component"
import Pagination from "../../components/Layouts/Pagination/Pagination.component"

import "./AllUsersPage.styles.scss"

const itemsPerPage = 18

const AllUsersPage = ({ getUsers, user: { users, loading } }) => {
  useEffect(() => {
    getUsers()
  }, [getUsers])

  const [page, setPage] = useState(1)
  const [fetchSearch, setSearch] = useState("")
  const [sortType, setSortType] = useState("Popular")

  const handlePaginationChange = (e, value) => setPage(value)

  const handleChange = (e) => {
    e.preventDefault()
    setSearch(e.target.value)
    setPage(1)
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
          <ButtonGroup
            buttons={["인기순", "이름순", "활동순", "신규 사용자"]}
            // buttons={["Popular", "Name", "Active", "New Users"]}
            selected={sortType}
            setSelected={setSortType}
          />
        </div>
        <div className='user-browser'>
          <div className='grid-layout'>
            {users
              .filter((user) => user.username.toLowerCase().includes(fetchSearch.toLowerCase()))
              ?.sort(handleSorting(sortType, "사용자"))
              .slice((page - 1) * itemsPerPage, (page - 1) * itemsPerPage + itemsPerPage)
              .map((user, index) => (
                <UserPanel key={index} user={user} />
              ))}
          </div>
        </div>
        <Pagination
          page={page}
          itemList={users.filter((user) =>
            user.username.toLowerCase().includes(fetchSearch.toLowerCase())
          )}
          itemsPerPage={itemsPerPage}
          handlePaginationChange={handlePaginationChange}
        />
      </div>
    </Fragment>
  )
}

AllUsersPage.propTypes = {
  getUsers: PropTypes.func.isRequired,
  user: PropTypes.object.isRequired,
}

const mapStateToProps = (state) => ({
  user: state.user,
})

export default connect(mapStateToProps, { getUsers })(AllUsersPage)
