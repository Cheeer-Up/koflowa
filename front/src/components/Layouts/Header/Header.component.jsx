import React, { Fragment, useState } from "react"
import { Link, useNavigate } from "react-router-dom"
import { connect } from "react-redux"
import PropTypes from "prop-types"
import { logout } from "../../../redux/auth/auth.actions"

import { ReactComponent as Search } from "../../../assets/Search.svg"
// import { ReactComponent as Logo } from "../../../assets/LogoMd.svg";
import { ReactComponent as Logo } from "../../../assets/KoflowaHeaderMdDark.svg"
// import { ReactComponent as SmallLogo } from "../../../assets/LogoGlyphMd.svg"
import { ReactComponent as SmallLogo } from "../../../assets/KoflowaHeaderTextDark.svg"
import Spinner from "../../Components/Spinner/Spinner.component"
import LinkButton from "../../Components/LinkButton/LinkButton.component"
import MobileSideBar from "../MobileSideBar/MobileSideBar.component"

import "./Header.styles.scss"

const Header = ({ auth: { isAuthenticated, loading, user }, logout }) => {
  let history = useNavigate()
  const [searchState, setSearchState] = useState(false)

  const authLinks = (
    <div className='btns'>
      {loading || user === null ? (
        <Spinner width='50px' height='50px' />
      ) : (
        <Link to={`/users/${user.id}`} title={user.username}>
          <img alt='user-logo' className='logo' src={user.gravatar} />
        </Link>
      )}
      <LinkButton text={"로그 아웃"} link={"/login"} type={"s-btn__filled"} handleClick={logout} />
    </div>
  )

  const guestLinks = (
    <div className='btns'>
      <LinkButton text={"로그인"} link={"/login"} type={"s-btn__primary"} />
      <LinkButton text={"회원 가입"} link={"/register"} type={"s-btn__filled"} />
    </div>
  )

  const SearchBar = () => {
    return (
      <form
        onSubmit={() => history.push("/questions")}
        className='small-search-form'
        autoComplete='off'
      >
        <input
          className='small-search'
          autoComplete='off'
          type='text'
          name='search'
          maxLength='35'
          placeholder='검색...'
        />
        <Search className='small-search-icon' />
      </form>
    )
  }

  return loading ? (
    ""
  ) : (
    <Fragment>
      <nav className='navbar fixed-top navbar-expand-lg navbar-light bs-md'>
        <div className='hamburger'>
          <MobileSideBar hasOverlay />
        </div>
        <div className='header-brand-div'>
          <Link className='navbar-brand' to='/'>
            <Logo className='full-logo' />
            <SmallLogo className='glyph-logo' />
            {/* 로고 부분 보여주고 싶은 로고 고르면됨 */}
          </Link>
        </div>

        <form
          id='search'
          onSubmit={() => history.push("/questions")}
          className={`grid--cell fl-grow1 searchbar px12 js-searchbar`}
          autoComplete='off'
        >
          <div className='ps-relative search-frame'>
            <input
              className='s-input s-input__search h100 search-box'
              autoComplete='off'
              type='text'
              name='search'
              maxLength='35'
              placeholder='검색...'
            />
            <Search />
          </div>
        </form>
        <div className='header-search-div'>
          <Search className='search-icon' onClick={() => setSearchState(!searchState)} />
          {!loading && (
            // <Fragment>{isAuthenticated ? guestLinks : authLinks}</Fragment>
            <Fragment>{isAuthenticated ? authLinks : guestLinks}</Fragment>
          )}
        </div>
      </nav>
      {searchState && <SearchBar />}
    </Fragment>
  )
}

Header.propTypes = {
  logout: PropTypes.func.isRequired,
  auth: PropTypes.object.isRequired,
}

const mapStateToProps = (state) => ({
  auth: state.auth,
})

export default connect(mapStateToProps, { logout })(Header)
