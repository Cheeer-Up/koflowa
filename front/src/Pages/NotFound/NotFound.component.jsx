import React, { Fragment } from "react"
import { Link } from "react-router-dom"

import "./NotFound.styles.scss"

const NotFound = () => {
  return (
    <Fragment>
      <div className='page'>
        <div className='box'>
          <div className='box__ghost'>
            <div className='symbol' />
            <div className='symbol' />
            <div className='symbol' />
            <div className='symbol' />
            <div className='symbol' />
            <div className='symbol' />

            <div className='box__ghost-container'>
              <div className='box__ghost-eyes'>
                <div className='box__eye-left' />
                <div className='box__eye-right' />
              </div>
              <div className='box__ghost-bottom'>
                <div />
                <div />
                <div />
                <div />
                <div />
              </div>
            </div>
            <div className='box__ghost-shadow' />
          </div>
          <div className='box__description'>
            <div className='box__description-container'>
              <div className='box__description-title fc-black-800'>이런!</div>
              <div className='box__description-text fc-black-500'>
                죄송해요 당신이 원하는 페이지를 찾을 수가 없어요
              </div>
            </div>
            <Link to='/' className='box__button'>
              홈페이지로 이동
            </Link>
          </div>
        </div>
      </div>
    </Fragment>
  )
}

export default NotFound
