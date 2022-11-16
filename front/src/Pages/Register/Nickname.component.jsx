import React from "react"
import "./Nickname.styles.scss"
import Button from "react-bootstrap/Button"
import Form from "react-bootstrap/Form"
import GoogleLogin from "./img/google.png"
const Nickname = () => {
  return (
    <div class='nickname-main'>
      <div class='nickname-main-div-1'>
        <span class='nickname-main-write-1'>거의 다 되었습니다!</span>
        <br></br>
        <span class='nickname-main-write-2'>
          코플러와에 로그인하기 위해 구글 계정을 사용하여 새 계정을 생성하겠습니다.
        </span>
        <br></br>
        <span class='nickname-main-write-3'>(obama@naver.com)</span>
        <br></br>
        <span class='nickname-main-write-4'>
          당신에 대해 조금만 더 알려주시면 당신의 관심사와 연관된 질문을 알려드리겠습니다.
        </span>
      </div>
      <div class='nickname-main-div-2'>
        <div class='nickname-main-form'>
          <span class='nickname-main-form-content-1'>닉네임</span>
          <br></br>
          <input
            class='nickname-main-form-input'
            size='59'
            height='50'
            type='text'
            placeholder='닉네임을 입력해주세요.'
          ></input>
          <br></br>
          <span class='nickname-main-form-content-2'>닉네임은 프로필과 게시글 등 활동에 반영됩니다.</span>
          <br></br>
          <a className='google-btn'>
            <img className='googleLogin' alt='GoogleLogin' src={GoogleLogin} />
          </a>
        </div>
      </div>
    </div>
  )
}

export default Nickname
