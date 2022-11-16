import React, { Fragment } from "react"

function IgnoreTagWidget() {
  return (
    <Fragment>
      <div className='widget'>
        <div className='s-sidebarwidget--header'>
          <span>무시 태그</span>
          <button>수정</button>
          <button>종료</button>
        </div>
        <div></div>
      </div>
    </Fragment>
  )
}

export default IgnoreTagWidget
