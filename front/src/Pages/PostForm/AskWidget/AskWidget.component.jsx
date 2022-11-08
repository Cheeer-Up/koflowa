import React, { Fragment } from "react"

import "./AskWidget.styles.scss"

const AskWidget = () => {
  return (
    <Fragment>
      <div className='widget'>
        <div className='s-sidebarwidget--header'>
          {/* Step 1: Draft your question */}
          단계를 밟아 봅시다.
        </div>
        <div className='widget-content fc-black-800'>
          <div className='summary'>
            <p className='sec1'>
              {/* The community is here to help you with specific coding, algorithm, or language
              problems. */}
              좋은 질문 쓰기 <br />
              <br />
              커뮤니티는 특정 코딩, 알고리즘 또는 언어를 지원하기 위해 여기 있습니다.
            </p>
            {/* <p className='sec2'>Avoid asking opinion-based questions.</p> */}
            <p className='sec2'>의견 처럼 보이는 질문을 피해주세요.</p>
          </div>
          <ol className='step-section'>
            <li className='step'>
              <button>
                <div className='step-cell'>
                  <div>
                    <img
                      src='https://cdn.sstatic.net/Img/list-1.svg?v=e8dd475ba207'
                      width='16'
                      height='16'
                      alt='1.'
                    />
                  </div>
                  {/* <span>Summarize the problem</span> */}
                  <span>한 줄의 제목으로 문제를 요약합니다.</span>
                </div>
              </button>
              <div className='inst'>
                <div className='inst-content'>
                  <ul>
                    <li>
                      {/* <p>Include details about your goal</p> */}
                      <p>목표에 대한 세부정보를 포함하기</p>
                    </li>
                    <li>
                      {/* <p>Describe expected and actual results</p> */}
                      <p>예상 및 실제 결과 설명하기</p>
                    </li>
                    <li>
                      {/* <p className='except'>Include any error messages</p> */}
                      <p className='except'>오류 메시지 포함하기</p>
                    </li>
                  </ul>
                </div>
              </div>
            </li>
            <li className='step'>
              <button>
                <div className='step-cell'>
                  <div>
                    <img
                      src='https://cdn.sstatic.net/Img/list-2.svg?v=9382fc2c3631'
                      width='16'
                      height='16'
                      alt='2.'
                    />
                  </div>
                  <span>당신이 시도한 것과 일어날 것으로 예상했던 것을 기술하십시오..</span>
                </div>
              </button>
              <div className='inst'>
                <div className='inst-content'>
                  <p className='step2'>
                    Show what you’ve tried and tell us what you found (on this site or elsewhere)
                    and why it didn’t meet your needs. You can get better answers when you provide
                    research.
                  </p>
                </div>
              </div>
            </li>
            <li className='step'>
              <button>
                <div className='step-cell'>
                  <div>
                    <img
                      src='https://cdn.sstatic.net/Img/list-3.svg?v=9382fc2c3631'
                      width='16'
                      height='16'
                      alt='3.'
                    />
                  </div>
                  <span>문제를 더 자세히 설명하십시오.</span>
                </div>
              </button>
              <div className='inst'>
                <div className='inst-content'>
                  <p className='step2'>
                    Show what you’ve tried and tell us what you found (on this site or elsewhere)
                    and why it didn’t meet your needs. You can get better answers when you provide
                    research.
                  </p>
                </div>
              </div>
            </li>
            <li
              style={{
                borderBottomRightRadius: "3px",
                borderBottomLeftRadius: "3px",
              }}
              className='step except-step'
            >
              <button>
                <div className='step-cell'>
                  <div>
                    <img
                      src='https://cdn.sstatic.net/Img/list-4.svg?v=323a95564232'
                      width='16'
                      height='16'
                      alt='4.'
                    />
                  </div>
                  <span>당신이 시도한 것과 일어날 것으로 예상했던 것을 기술하십시오.</span>
                </div>
              </button>
              <div className='inst'>
                <div className='inst-content'>
                  <p className='step3'>
                    When appropriate, share the minimum amount of code others need to reproduce your
                    problem (also called a minimum, reproducible example)
                  </p>
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
