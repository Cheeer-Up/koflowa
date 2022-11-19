import React, { Fragment, useState, useEffect } from "react"
import { connect, useSelector } from "react-redux"
import { selectToken } from "redux/slice/AuthSlice"
import { useNavigate } from "react-router-dom"

import AskWidget from "./AskWidget/AskWidget.component"
import AskForm from "./AskForm/AskForm.component"
import Footer from "../../components/Layouts/Footer/Footer.component"

import "./TagForm.styles.scss"

const TagForm = () => {
  // 로그인되어있지 않을 시 route 이동
  let navigate = useNavigate()
  // redux로부터 accessToken을 받아옴
  const [acToken] = useState(useSelector(selectToken))
  // 컴포넌트 첫 랭더링 시 토큰 확인 후 없다면 로그인 페이지로 이동
  // isAuthenticated는 acToken과 동일한 역할이기 때문에 삭제 무방
  useEffect(() => {
    if (!acToken) {
      alert("로그인해주세요.")
      navigate("/login")
    }
  }, [])

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
