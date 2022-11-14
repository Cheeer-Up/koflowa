import React, { Fragment } from "react"

import "./AskWidget.styles.scss"

const AskWidget = () => {
  return (
    <Fragment>
      <div className='widget'>
        <div className='s-sidebarwidget--header'>태그 추가하기</div>
        <div className='widget-content fc-black-800'>
          <ol className='step-section'>
            <li className='step'>
              <button>
                <div className='step-cell'>
                  <div>
                    <img src='https://cdn.sstatic.net/Img/list-1.svg?v=e8dd475ba207' width='16' height='16' alt='1.' />
                  </div>
                  <span>태그 이름 쓰기</span>
                </div>
              </button>
              <div className='inst'>
                <div className='inst-content'>
                  <p className='step1'>제품 라인, 프로젝트, 팀 및 사용된 특정 기술 또는 언어를 태그로 사용해주세요.</p>
                  <p className='step1'>태그 이름은 25자 이내의 공백없는 알파벳으로 이루어져야 합니다.</p>
                </div>
              </div>
            </li>
            <li className='step'>
              <button>
                <div className='step-cell'>
                  <div>
                    <img src='https://cdn.sstatic.net/Img/list-2.svg?v=9382fc2c3631' width='16' height='16' alt='2.' />
                  </div>
                  <span>태그 내용 쓰기</span>
                </div>
              </button>
              <div className='inst'>
                <div className='inst-content'>
                  <p className='step1'>태그에 대한 상세한 설명을 적어주세요.</p>
                </div>
              </div>
            </li>
          </ol>
        </div>
      </div>
    </Fragment>
  )
}

export default AskWidget
