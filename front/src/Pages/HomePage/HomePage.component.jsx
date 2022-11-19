import React, { Fragment, useEffect, useState } from "react"
import "./HomePage.styles.scss"

import { signIn_Out } from "../../api/sign"
import { useDispatch, useSelector } from "react-redux"
import { setUser, selectUser, setToken, setIsAuthenticated } from "../../redux/slice/AuthSlice"
import { Link, useNavigate } from "react-router-dom"

const HomePage = () => {
  const navigate = useNavigate()
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
            nickname: information.nickname,
          }
          // token, user 입력 및 authenticated 수정
          dispatch(setToken(token))
          dispatch(setUser(user))
          setUserState(user)
          console.log("user data: ", user)
          if (user.nickname === null) {
            alert("잠깐! 닉네임을 설정해주세요!")
            setTimeout(() => {
              window.location.href = "/nickname"
            }, 50)
            return
          }
          setTimeout(() => {
            window.location.href = "/"
          }, 50)
        })
        .catch((err) => {
          console.log(err)
          alert("로그인 중 에러가 발생했습니다. 다시 로그인해주세요.")
          localStorage.clear()
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
      <br />
      <br />
    </Fragment>
  )
}

export default HomePage
