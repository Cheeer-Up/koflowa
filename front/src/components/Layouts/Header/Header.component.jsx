import React, { Fragment, useEffect, useState } from "react"
import { Link, useNavigate, location } from "react-router-dom"

import { ReactComponent as Search } from "assets/Search.svg"
import { ReactComponent as Logo } from "assets/KoflowaHeaderMd.svg"
import { ReactComponent as SmallLogo } from "assets/KoflowaHeaderText.svg"
import Spinner from "components/Components/Spinner/Spinner.component"
import LinkButton from "components/Components/LinkButton/LinkButton.component"
import MobileSideBar from "components/Layouts/MobileSideBar/MobileSideBar.component"
import { selectToken, selectUser, setToken, setUser } from "../../../redux/slice/AuthSlice"
import { useSelector, useDispatch } from "react-redux"

import "./Header.styles.scss"

const emptyUser = {
  authProvider: "",
  email: "",
  name: "",
  nickname: "",
  profile: "",
  role: "",
  seq: "",
}

const Header = () => {
  let navigate = useNavigate()
  const dispatch = useDispatch()
  const [userState, setUserState] = useState(useSelector(selectUser))
  const [acTkn, setAcTkn] = useState(useSelector(selectToken))
  const [searchState, setSearchState] = useState(false)

  useEffect(() => {
    if (userState === null || acTkn === null) {
      dispatch(setUser(emptyUser))
      dispatch(setToken(""))
      setUserState(emptyUser)
      setAcTkn("")
    }
  }, [userState, acTkn, dispatch])

  const AuthLinks = () => {
    /** localStorage의 Token들과 redux의 user를 삭제하여 로그아웃을 합니다. */
    const removeTokens = () => {
      dispatch(setUser(emptyUser))
      dispatch(setToken(""))
      setUserState(emptyUser)
      setAcTkn("")
      localStorage.removeItem("accessToken")
      localStorage.removeItem("refreshToken")
      navigate("/")
    }

    return (
      <div className='btns'>
        <Link to={`/users/${userState.seq}`}>
          <img alt='user-logo' className='logo' src={userState.profile} />
        </Link>
        <LinkButton text='로그아웃' link='/' className='l-btn' handleClick={removeTokens} />
      </div>
    )
  }

  const GuestLinks = () => {
    return (
      <div className='btns'>
        <LinkButton text={"로그인"} link={"/login"} className='l-btn' />
        <LinkButton text={"회원가입"} link={"/register"} />
      </div>
    )
  }
  const IsAuth = () => {
    if (acTkn !== "") return <AuthLinks />
    else return <GuestLinks />
  }

  console.log(window.innerWidth)
  const SearchBar = () => {
    return (
      <div>
        <form onSubmit={() => navigate.push("/questions")} className='small-search-form' autoComplete='off'>
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
      </div>
    )
  }

  if (window.location.pathname === "/meeting") return null

  return (
    <Fragment>
      {/* 모바일 사이즈 시 햄버거 노출 */}
      <nav className='navbar fixed-top navbar-expand-lg navbar-light bs-md'>
        <div className='hamburger'>
          <MobileSideBar hasOverlay />
        </div>
        {/* 로고 이미지 */}
        <div className='header-brand-div'>
          <Link className='navbar-brand' to='/questions'>
            {/* <Logo className='full-logo' /> */}
            <img src='KoflowaHeader2.png' alt='' className='full-logo' />
            <SmallLogo className='glyph-logo' />
          </Link>
        </div>
        {/* 검색 창 */}
        <form
          id='search'
          onSubmit={() => navigate.push("/questions")}
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
          {/* 로그인 시 AuthLinks 아닐 시 guest */}
          <IsAuth />
          {/* 돋보기 이미지 */}
          <Search className='search-icon' onClick={() => setSearchState(!searchState)} />
          {/* {!user && <Fragment>{isAuthenticated ? <AuthLinks /> : <GuestLinks />}</Fragment>} */}
        </div>
      </nav>
      {searchState && <SearchBar />}
    </Fragment>
  )
}

export default Header
