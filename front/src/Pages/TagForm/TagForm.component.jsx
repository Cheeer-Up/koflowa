import React, { Fragment } from "react"
import { connect } from "react-redux"
import { Navigate } from "react-router-dom"
import PropTypes from "prop-types"

import Spinner from "../../components/Components/Spinner/Spinner.component"
import AskWidget from "./AskWidget/AskWidget.component"
import AskForm from "./AskForm/AskForm.component"
import Footer from "../../components/Layouts/Footer/Footer.component"

import "./TagForm.styles.scss"

const TagForm = ({ auth: { isAuthenticated, loading } }) => {
  // if (!isAuthenticated) {
  //   return <Navigate to='/login' />
  // }

  return loading === null ? (
    <Spinner type='page' width='75px' height='200px' />
  ) : (
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

TagForm.propTypes = {
  // auth: PropTypes.object.isRequired,
}

const mapStateToProps = (state) => ({
  auth: state.auth,
})

export default connect(mapStateToProps, null)(TagForm)