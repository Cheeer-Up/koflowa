import React, { Fragment } from "react"
import { connect, useSelector } from "react-redux"
import { selectIsAuthenticated } from "redux/slice/AuthSlice"
import { Navigate } from "react-router-dom"

import AskWidget from "./AskWidget/AskWidget.component"
import AskForm from "./AskForm/AskForm.component"
import Footer from "../../components/Layouts/Footer/Footer.component"

import "./TagForm.styles.scss"

const TagForm = () => {
  const isAuthenticated = useSelector(selectIsAuthenticated)
  if (!isAuthenticated) {
    return <Navigate to='/login' />
  }

  return (
    <Fragment>
      <div className='post-form-container'>
        <div className='post-form-content'>
          <div className='post-form-header'>
            <div className='post-form-headline fc-black-800'>태그 생성하기</div>
          </div>
          <div className='post-form-section'>
            <div className='postform' style={{ width: "100%" }}>
              <AskForm />
            </div>
            <aside>
              <div className='right-panel'>
                <AskWidget />
              </div>
            </aside>
          </div>
        </div>
      </div>
      <Footer />
    </Fragment>
  )
}

const mapStateToProps = (state) => ({
  auth: state.auth,
})

export default connect(mapStateToProps, null)(TagForm)
