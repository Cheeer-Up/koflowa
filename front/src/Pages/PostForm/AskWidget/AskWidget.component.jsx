import React, { Fragment } from "react"

import "./AskWidget.styles.scss"

const AskWidget = () => {
  return (
    <Fragment>
      <div className='widget'>
        <div className='s-sidebarwidget--header'>
          {/* Step 1: Draft your question */}
          좋은글을 작성해 봅시다.
        </div>
        <div className='widget-content fc-black-800'>
          <div className='summary'>
            <p className='sec1'>
              {/* The community is here to help you with specific coding, algorithm, or language
              problems. */}
              <br />
              <br />
              커뮤니티는 특정 코딩, 알고리즘 또는 언어를 지원하기 위해 여기 있습니다.
              <br />
            </p>
            {/* <p className='sec2'>Avoid asking opinion-based questions.</p> */}
            <p className='sec2'>의견 처럼 보이는 질문을 피해주세요.</p>
            <ul>
              <li>한 줄의 제목으로 문제를 요약합니다.</li>
              <li>문제를 더 자세히 설명해주세요.</li>
              <li>당신이 시도한 것과 일어날 것으로 예상했던 것을 기술해주세요.</li>
              <li>커뮤니티 구성원에게 질문을 표시하는 데 도움이 되는 "태그"를 추가합니다.</li>
              <li>질문을 검토하고 사이트에 게시해주세요.</li>
            </ul>
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
                  <span>좋은 제목 쓰기</span>
                </div>
              </button>
              <div className='inst'>
                <div className='inst-content'>
                  <p className='step1'>제목은 문제를 요약해야 합니다.</p>
                  <p className='step1'>
                    질문의 나머지 부분을 작성하고 나면 제목에 대해 더 잘 알 수 있을 것입니다.
                  </p>
                  {/* <ul>
                    <li>
                      <p>Include details about your goal</p>
                      <p>목표에 대한 세부정보를 포함하기</p>
                    </li>
                    <li>
                      <p>Describe expected and actual results</p>
                      <p>예상 및 실제 결과 설명하기</p>
                    </li>
                    <li>
                      <p className='except'>Include any error messages</p>
                      <p className='except'>오류 메시지 포함하기</p>
                    </li>
                  </ul> */}
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
                  <span>문제 소개</span>
                </div>
              </button>
              <div className='inst'>
                <div className='inst-content'>
                  <p className='step1'>
                    해결하려고 하는 문제에 어떻게 부딪쳤는지, 그리고 스스로 해결하는 데 방해가 된
                    어려움을 설명해주세요.
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
                  <span>문제 확장</span>
                </div>
              </button>
              <div className='inst'>
                <div className='inst-content'>
                  <p className='step1'>
                    당신이 시도한 것을 보여주고, 무슨 일이 일어났는지, 왜 그것이 당신의 요구를
                    충족시키지 못했는지 알려주세요.
                  </p>
                  <p className='step1'>
                    모든 질문에 코드를 포함하는 것이 도움이 되는 것은 아니지만 작성한 코드로 문제를
                    더 잘 이해할 수 있다면 최소한의 재현 가능한 예를 포함해야 합니다.
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
                  <span>태그 추가</span>
                </div>
              </button>
              <div className='inst'>
                <div className='inst-content'>
                  <p className='step1'>
                    태그는 귀하의 질문이 적절한 사람들의 관심을 끌 수 있도록 도와줍니다.
                  </p>
                  <p className='step1'>
                    사람들이 더 쉽게 찾을 수 있도록 여러 가지 방법으로 항목에 태그를 지정합니다.
                    제품 라인, 프로젝트, 팀 및 사용된 특정 기술 또는 언어에 대한 태그를 추가합니다.
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
