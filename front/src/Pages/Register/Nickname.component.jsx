import "./Nickname.styles.scss"
import React, { useEffect, useState } from "react"
import GoogleLogin from "./img/google.png"
import { useLocation } from "react-router-dom"

import { signIn_Out, setUserNickname } from "../../api/sign"
import { useDispatch, useSelector } from "react-redux"
import { setUser, selectUser, selectToken } from "../../redux/slice/AuthSlice"

const Nickname = () => {
  const [nickname, setNickname] = useState("")
  const [userState, setUserState] = useState(useSelector(selectUser))
  const [acTkn, setAcTkn] = useState(useSelector(selectToken))

  //닉네임이 없을 시 기본으로 name을 입력
  useEffect(() => {
    setUserNickname(acTkn, userState.name)
  }, [])

  const onChange = (e) => {
    setNickname(e.target.value)
  }

  // 닉네임 입력 버튼 클릭 시
  const setUserNicknameFunc = () => {
    if (nickname === "") {
      alert("닉네임을 입력해 주세요.")
      return
    }

    setUserNickname(acTkn, nickname).then((res) => {
      if (res.data.status_code === 200) {
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
  console.log(userState)
  return (
    <div className='nickname-main'>
      <div className='nickname-main-div-1'>
        <span className='nickname-main-write-1'>거의 다 되었습니다, </span>
        <span className='nickname-main-write-1' style={{ color: "rgb(95, 139, 255)" }}>
          {userState.name}
        </span>
        <span className='nickname-main-write-1'>님!</span>
        <br />
        <br />
        <span className='nickname-main-write-2'>
          코플로와에 로그인하기 위해 구글 계정을 사용하여 새 계정을 생성하겠습니다.
        </span>
        <br />
        <br /> <br />
        <span className='nickname-main-write-3'>당신이 가입하려는 이메일은 {userState.email} 입니다.</span>
        <br />
        <span className='nickname-main-write-4'>
          당신에 대해 조금만 더 알려주시면 당신의 관심사와 연관된 질문을 알려드리겠습니다.
        </span>
      </div>
      <div className='nickname-main-div-2'>
        <div className='nickname-main-form'>
          <span className='nickname-main-form-content-1'>닉네임 설정</span>
          <br />
          <br />
          <input
            className='nickname-main-form-input'
            size='50'
            height='50'
            type='text'
            placeholder='개성있는 닉네임을 설정해주세요.'
            onChange={onChange}
            value={nickname}
          ></input>
          <br />
          <br />
          <span className='nickname-main-form-content-2'>
            닉네임은 프로필과 게시글 등 활동에 반영 되는 필수 입력 정보입니다
          </span>
          <br />
          <br />
          <a onClick={() => setUserNicknameFunc()}>
            <img className='googleLogin' alt='GoogleLogin' src={GoogleLogin} />
          </a>
        </div>
      </div>
    </div>
  )
}

export default Nickname
