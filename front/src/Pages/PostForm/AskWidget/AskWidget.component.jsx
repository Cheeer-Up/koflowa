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
              코플로와는 특정 문제, 알고리즘 또는 언어를 지원하기 위해 여기 있습니다.
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
                    <img src='https://cdn.sstatic.net/Img/list-1.svg?v=e8dd475ba207' width='16' height='16' alt='1.' />
                  </div>
                  {/* <span>Summarize the problem</span> */}
                  <span>좋은 제목 쓰기</span>
                </div>
              </button>
              <div className='inst'>
                <div className='inst-content'>
                  <p className='step1'>제목은 문제를 요약해야 합니다.</p>
                  <p className='step1'>상대방이 쉽게 이해할 수 있으면 좋습니다.</p>
                </div>
              </div>
            </li>
            <li className='step'>
              <button>
                <div className='step-cell'>
                  <div>
                    <img src='https://cdn.sstatic.net/Img/list-2.svg?v=9382fc2c3631' width='16' height='16' alt='2.' />
                  </div>
                  <span>문제 소개</span>
                </div>
              </button>
              <div className='inst'>
                <div className='inst-content'>
                  <p className='step1'>
                    무엇을 하고 싶었고, 어떤 문제가 발생한 것인지, 그리고 해결하려고 어떤 방법을 써보았는지 등을 자세히
                    설명해 주세요.
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
                    <img src='https://cdn.sstatic.net/Img/list-3.svg?v=323a95564232' width='16' height='16' alt='4.' />
                  </div>
                  <span>태그 추가</span>
                </div>
              </button>
              <div className='inst'>
                <div className='inst-content'>
                  <p className='step1'>
                    태그는 질문이 보다 문제 해결에 적절한 사람들의 관심을 끌 수 있도록 도와줍니다.
                  </p>
                  <p className='step1'>
                    사람들이 더 쉽게 찾을 수 있도록 여러 가지 방법으로 항목에 태그를 지정합니다. 사용된 특정 기술 또는
                    언어에 대한 태그를 추가합니다.
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
