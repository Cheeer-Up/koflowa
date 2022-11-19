import "./Login.styles.scss"

import React, { Fragment, useEffect } from "react"

import Footer from "components/Layouts/Footer/Footer.component"
import googleLogo from "../../assets/google-logo.png"
import { GOOGLE_AUTH_URL } from "../../api/urls"
import Button from "@mui/material/Button"

const Login = () => {
  return (
    <Fragment>
      <div className='auth-page'>
        <div className='register-content'>
          <div className='register-grid'>
            {/* 클릭 시 인증 페이지 이동 */}
            <a className='btn btn-block social-btn google' href={GOOGLE_AUTH_URL}>
              <Button variant='outlined'>
                <img src={googleLogo} alt='Google' />
                구글을 통해
                {window.location.pathname === "/register" ? " 회원가입하기" : " 로그인하기"}
              </Button>
            </a>
            {/* <AuthForm action={"Log in"} /> */}
          </div>
        </div>
      </div>
      <Footer />
    </Fragment>
  )
}

Login.propTypes = {
  // isAuthenticated: PropTypes.bool,
}

export default Login
