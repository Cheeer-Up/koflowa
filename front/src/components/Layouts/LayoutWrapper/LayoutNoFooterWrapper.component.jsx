import React, { Fragment } from "react"
import SideBar from "./SideBar/SideBar.component"

const LayoutNoFooterWrapper = ({ children }) => {
  return (
    <Fragment>
      <div className='page'>
        <SideBar />
        <div id='content'>{children}</div>
      </div>
    </Fragment>
  )
}

export default LayoutNoFooterWrapper
