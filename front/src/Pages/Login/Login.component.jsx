import React, { Fragment } from "react"
import { Navigate } from "react-router-dom"
import PropTypes from "prop-types"

import AuthForm from "components/Layouts/AuthForm/AuthForm.component"
import Footer from "components/Layouts/Footer/Footer.component"
import googleLogo from "../../assets/google-logo.png"

import { useSelector } from "react-redux"
import { GOOGLE_AUTH_URL } from "../../api/urls"

const Login = () => {
  return (
    <Fragment>
      <div className='auth-page'>
        <div className='register-content'>
          <div className='register-grid'>
            {/* 클릭 시 인증 페이지 이동 */}
            <a className='btn btn-block social-btn google' href={GOOGLE_AUTH_URL}>
              <img src={googleLogo} alt='Google' /> Sign in with Google
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
