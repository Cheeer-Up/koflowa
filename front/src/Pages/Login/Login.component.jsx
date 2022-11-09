import React, { Fragment } from "react"
import { Navigate } from "react-router-dom"
import PropTypes from "prop-types"

import AuthForm from "components/Layouts/AuthForm/AuthForm.component"
import Footer from "components/Layouts/Footer/Footer.component"

import { useSelector } from "react-redux"

const Login = () => {
  const isAuthenticated = true
  if (isAuthenticated) {
    return <Navigate to='/' />
  }

  return (
    <Fragment>
      <div className='auth-page'>
        <div className='register-content'>
          <div className='register-grid'>
            <AuthForm action={"Log in"} />
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
