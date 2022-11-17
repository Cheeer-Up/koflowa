import React, { Fragment, useEffect, useState } from "react"
import "./HomePage.styles.scss"

import { signIn_Out } from "../../api/sign"
import { useDispatch, useSelector } from "react-redux"
import { setUser, selectUser, setToken, setIsAuthenticated } from "../../redux/slice/AuthSlice"
import { Link } from "react-router-dom"

const HomePage = () => {
  // let navigate = useNavigate()

  // setTimeout(function () {
  //   navigate("/questions")
  // }, 10000)

  const dispatch = useDispatch()
  const [userState, setUserState] = useState(useSelector(selectUser))

  useEffect(() => {
    const token = getUrlParameter("token")
    const error = getUrlParameter("error")

    // token
    if (token) {
      localStorage.setItem("accessToken", token)
      localStorage.setItem("refreshToken", null)

      if (!localStorage.getItem("accessToken")) {
        return Promise.reject("No access token set.")
      }
      signIn_Out(token)
        .then(({ data }) => {
          console.log(data)
          let information = data.information
          let user = {
            authProvider: information.authProvider,
            email: information.email,
            name: information.name,
            profile: information.profile,
            role: information.role,
            seq: information.seq,
          }
          // token, user 입력 및 authenticated 수정
          dispatch(setToken(token))
          dispatch(setUser(user))
          dispatch(setIsAuthenticated(true))
          setUserState(user)

          // 로그인 데이터만 받아온 상황.
          // 헤더에 적용시켜주기 위해 리로드
          // window.location.href 같은 즉시 이동은 redux 저장이나 state 저장 전에 실행
          // setTimeOut으로 조절
          setTimeout(() => {
            window.location.href = "/"
          }, 50)
        })
        .catch((err) => {
          console.log(err)
        })
    }
    // 에러시 경고 생성 (외부 alert 라이브러리 사용 예정)
    if (error) {
    }
  }, [dispatch])

  /**token을 받아올 수 있는 주소를 parsing */
  const getUrlParameter = (name) => {
    name = name.replace(/[\\[]/, "\\[").replace(/[\]]/, "\\]")
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)")

    var results = regex.exec(window.location.search)
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "))
  }

  return (
    <Fragment>
      <p>
        <img className='koflowa vibe' src='/koflowa.png' alt='코플로와' />
      </p>
      <p>
        <img className='koflowa-text' src='/koflowText.png' alt='코플로와 텍스트' />
      </p>
      <Link className='sbox__button' to={"/questions"}>
        코플로와 시작하기
      </Link>
    </Fragment>
  )
}

export default HomePage
