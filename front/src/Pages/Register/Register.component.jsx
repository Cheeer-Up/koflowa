import React, { Fragment } from "react"
import { connect } from "react-redux"
import { Navigate } from "react-router-dom"
import PropTypes from "prop-types"
import { setAlert } from "../../redux/alert/alert.actions"

import Caption from "./Caption/Caption.component"
import AuthForm from "../../components/Layouts/AuthForm/AuthForm.component"
import Footer from "../../components/Layouts/Footer/Footer.component"

import "./Register.styles.scss"

const Register = ({ isAuthenticated }) => {
  if (isAuthenticated) {
    return <Navigate to='/' />
  }

  return (
    <Fragment>
      <div className='auth-page'>
        <div className='register-content'>
          <div className='register-grid'>
            <Caption />
            <AuthForm action={"Sign up"} />
          </div>
        </div>
      </div>
      <Footer />
    </Fragment>
  )
}

Register.propTypes = {
  // setAlert: PropTypes.func.isRequired,
  // isAuthenticated: PropTypes.bool,
}

const mapStateToProps = (state) => ({
  isAuthenticated: state.auth.isAuthenticated,
})

export default connect(mapStateToProps, { setAlert })(Register)
