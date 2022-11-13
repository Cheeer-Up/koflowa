import React, { Fragment } from "react"
import { connect } from "react-redux"
import { Navigate } from "react-router-dom"
import PropTypes from "prop-types"
import { setAlert } from "../../redux/alert/alert.actions"

import Caption from "./Caption/Caption.component"
import AuthForm from "../../components/Layouts/AuthForm/AuthForm.component"
import Footer from "../../components/Layouts/Footer/Footer.component"

import googleLogo from "../../assets/google-logo.png"
import { GOOGLE_AUTH_URL } from "../../api/urls"
import "./Register.styles.scss"

const Register = () => {
  return (
    <Fragment>
      <div className='auth-page'>
        <div className='register-content'>
          <div className='register-grid'>
            <Caption />
            <a className='btn btn-block social-btn google' href={GOOGLE_AUTH_URL}>
              <img src={googleLogo} alt='Google' /> Sign up with Google
            </a>
          </div>
        </div>
      </div>
      <Footer />
    </Fragment>
  )
}

const mapStateToProps = (state) => ({
  isAuthenticated: state.auth.isAuthenticated,
})

export default connect(mapStateToProps, { setAlert })(Register)
