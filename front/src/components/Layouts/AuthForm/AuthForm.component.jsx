import React, { Fragment, useState } from "react"
import { Link } from "react-router-dom"
import PropTypes from "prop-types"

import { ReactComponent as Logo } from "../../../assets/LogoGlyphMd.svg"
// import { ReactComponent as ExternalLink } from "../../../assets/ExternalLink.svg"

import "./AuthForm.styles.scss"

const AuthForm = ({ register, login, action }) => {
  const [formData, setFormData] = useState({
    username: "",
    password: "",
  })

  const { username, password } = formData

  const onChange = (e) => setFormData({ ...formData, [e.target.name]: e.target.value })

  const onSubmit = async (e) => {
    e.preventDefault()
    if (action === "Sign up") {
      register({ username, password })
    } else {
      login({ username, password })
    }
  }

  const signUpLink = (
    <Fragment>
      이미 계정을 가지고 계신가요?{" "}
      <Link to='/login' name='login'>
        로그인
      </Link>
    </Fragment>
  )

  const logInLink = (
    <Fragment>
      계정이 없으신가요?{" "}
      <Link to='/register' name='register'>
        회원가입
      </Link>
    </Fragment>
  )

  return (
    <Fragment>
      <div>
        <div className='icon-holder'>
          <Logo className='icon' />
        </div>
        <div className='form-container'>
          <form className='login-form' onSubmit={(e) => onSubmit(e)}>
            <div>
              <label className='form-label s-label fc-black-600'>사용자명</label>
              <input
                className='form-input s-input'
                type='text'
                name='username'
                value={username}
                onChange={(e) => onChange(e)}
                id='username'
                required
              />
            </div>
            <div>
              <label className='form-label s-label fc-black-600'>비밀번호</label>
              <input
                className='form-input s-input'
                type='password'
                name='password'
                value={password}
                onChange={(e) => onChange(e)}
                id='password'
                required
              />
            </div>
            <div className='grid gs4 gsy fd-column js-auth-item '>
              <button className='s-btn s-btn__primary' id='submit-button' name='submit-button'>
                {action}
              </button>
              {/*  */}
              {/* <div style={{ width: "300px", height: "100px" }}> */}
              <div>
                <div
                  id='g_id_onload'
                  data-client_id='1002048860758-t0jrbroq6opqj23a92aajh4eolm5jl2f.apps.googleusercontent.com'
                  data-login_uri='http://localhost:8081'
                  data-auto_prompt='false'
                ></div>
                <div
                  className='g_id_signin'
                  data-type='standard'
                  data-size='large'
                  data-theme='outline'
                  data-text='sign_in_with'
                  data-shape='rectangular'
                  data-logo_alignment='left'
                ></div>
              </div>
              {/*  */}
            </div>
          </form>
          {/* <div className='fs-caption license fc-black-500'>
            By clicking “{action}”, you agree to our{" "}
            <Link to='https://stackoverflow.com/legal/terms-of-service/public' className='-link'>
              terms of service
            </Link>
            ,{" "}
            <Link
              to='https://stackoverflow.com/legal/privacy-policy'
              name='privacy'
              className='-link'
            >
              privacy policy
            </Link>{" "}
            and{" "}
            <Link to='https://stackoverflow.com/legal/cookie-policy' className='-link'>
              cookie policy
            </Link>
            <input type='hidden' name='legalLinksShown' value='1' />
          </div> */}
        </div>
        <div className='redirects fc-black-500'>
          {action === "Sign up" ? signUpLink : logInLink}
          {/* <div>
            Are you an employer?{" "}
            <Link to='https://careers.stackoverflow.com/employer/login' name='talent'>
              Sign up on Talent <ExternalLink />
            </Link>
          </div> */}
        </div>
      </div>
    </Fragment>
  )
}

AuthForm.propTypes = {
  // register: PropTypes.func.isRequired,
  // login: PropTypes.func.isRequired,
  // isAuthenticated: PropTypes.bool,
}

export default AuthForm
