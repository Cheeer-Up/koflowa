import React, { Fragment } from "react"
import SideBar from "./SideBar/SideBar.component"
import Footer from "../Footer/Footer.component"

const LayoutWrapper = ({ children }) => {
  return (
    <Fragment>
      <div className='page'>
        <SideBar />
        <div id='content'>{children}</div>
      </div>
      <Footer />
    </Fragment>
  )
}

export default LayoutWrapper
