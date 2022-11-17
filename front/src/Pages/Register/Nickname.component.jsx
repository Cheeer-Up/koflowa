import "./Nickname.styles.scss"
import React, { useEffect, useState } from "react"
import GoogleLogin from "./img/google.png"
import { useLocation } from "react-router-dom"

import { signIn_Out, setUserNickname } from "../../api/sign"
import { useDispatch, useSelector } from "react-redux"
import { setUser, selectUser, setToken, setIsAuthenticated, selectToken } from "../../redux/slice/AuthSlice"

const Nickname = () => {
  const dispatch = useDispatch()
  const [nickname, setNickname] = useState("")
  const [userState, setUserState] = useState(useSelector(selectUser))
  const [accessToken, setAccessToken] = useState("")

  const onChange = (e) => {
    setNickname(e.target.value)
  }

  // 닉네임 입력 버튼 클릭 시
  const setUserNicknameFunc = () => {
    if (nickname === "") {
      alert("닉네임을 입력해 주세요.")
      return
    }

    setUserNickname(accessToken, nickname).then((res) => {
      if (res.data.status_code == 200) {
        alert("정상적으로 닉네임이 등록되었습니다.")
        setUserState({ ...userState, nickname: nickname })
        //변경이 완료된 경우
        document.location.href = "/"
      } else {
        alert("닉네임 설정에 오류가 발생했습니다. 홈 화면으로 이동합니다.")
        document.location.href = "/"
      }
    })
  }

  useEffect(() => {
    const token = getUrlParameter("token")
    const error = getUrlParameter("error")

    // token
    if (token) {
      localStorage.setItem("accessToken", token)
      localStorage.setItem("refreshToken", null)
      setAccessToken(token)

      // if (!token) {
      //   return Promise.reject("No access token set.")
      // }

      signIn_Out(token)
        .then(({ data }) => {
          console.log("this is user data: ", data)
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

          console.log("user 데이터ㅣ ", user)

          // token, user 입력 및 authenticated 수정
          dispatch(setToken(token))
          dispatch(setUser(user))
          setUserState(user)

          // 로그인 => nickname이 있으면 바로 홈으로 가고
          //        ㄴ> nickname이 없으면 현재 페이지에 남아서 원하는 닉네임 등록(기본적으로 유저 name을 nickname으로 등록)
          if (user.nickname) {
            setTimeout(() => {
              window.location.href = "/"
            }, 20)
          }

          // 로그인 데이터만 받아온 상황.
          // 헤더에 적용시켜주기 위해 리로드
          // window.location.href 같은 즉시 이동은 redux 저장이나 state 저장 전에 실행
          // setTimeOut으로 조절
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
    <div className='nickname-main'>
      <div className='nickname-main-div-1'>
        <span className='nickname-main-write-1'>거의 다 되었습니다!</span>
        <br></br>
        <span className='nickname-main-write-2'>
          코플러와에 로그인하기 위해 구글 계정을 사용하여 새 계정을 생성하겠습니다.
        </span>
        <br></br>
        {/* <span className='nickname-main-write-3'>{userState.email}</span>   */}
        <br></br>
        <span className='nickname-main-write-4'>
          당신에 대해 조금만 더 알려주시면 당신의 관심사와 연관된 질문을 알려드리겠습니다.
        </span>
      </div>
      <div className='nickname-main-div-2'>
        <div className='nickname-main-form'>
          <span className='nickname-main-form-content-1'>닉네임 설정</span>
          <br></br>
          <input
            className='nickname-main-form-input'
            size='50'
            height='50'
            type='text'
            placeholder='닉네임을 입력해주세요.'
            onChange={onChange}
            value={nickname}
          ></input>
          <br></br>
          <span className='nickname-main-form-content-2'>
            닉네임은 프로필과 게시글 등 활동에 반영 되는 필수 입력 정보입니다
          </span>
          <br></br>
          <a onClick={() => setUserNicknameFunc()}>
            <img className='googleLogin' alt='GoogleLogin' src={GoogleLogin} />
          </a>
        </div>
      </div>
    </div>
  )
}

export default Nickname
