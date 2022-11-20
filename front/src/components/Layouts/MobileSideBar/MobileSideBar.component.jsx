import React, { useState } from "react"
import { NavLink } from "react-router-dom"

// import { ReactComponent as Hamburger } from "../../../assets/LogoGlyphMd.svg";
import { ReactComponent as Hamburger } from "../../../assets/KoflowaSmLogo.svg"
// import { ReactComponent as Stack } from "../../../assets/LogoMd.svg"
import { ReactComponent as Stack } from "../../../assets/KoflowaHeaderMdDark.svg"
import { ReactComponent as GlobalIcon } from "../../../assets/Globe.svg"

import "./MobileSideBar.styles.scss"

const SidebarUI = ({ isOpen, ...rest }) => {
  const classes = ["Sidebar", isOpen ? "is-open" : ""]

  return <div aria-hidden={!isOpen} className={classes.join(" ")} {...rest} />
}

SidebarUI.Overlay = (props) => <div className='SidebarOverlay' {...props} />

SidebarUI.Content = ({ width = "20rem", isRight = false, ...rest }) => {
  const classes = ["SidebarContent", isRight ? "is-right" : ""]
  const style = {
    width,
    height: "100%",
    top: 0,
    right: isRight ? `-${width}` : "auto",
    left: !isRight ? `-${width}` : "auto",
  }

  return <div className={classes.join(" ")} style={style} {...rest} />
}

const MobileSideBar = (props) => {
  const [isOpen, setIsOpen] = useState(false)

  function openSidebar(isOp = true) {
    setIsOpen(isOp)
  }

  const { hasOverlay, isRight } = props

  return (
    <SidebarUI isOpen={isOpen}>
      <Hamburger onClick={openSidebar} className='ham' />

      <SidebarUI.Content isRight={isRight} onClick={() => openSidebar(false)}>
        <div className='content-inner'>
          <div className='side-bar-tabs'>
            <NavLink activeclassname='active' className='home-link' to='/questions'>
              <p>질문</p>
            </NavLink>
            <NavLink activeclassname='active' className='home-link' to='/tags'>
              <p>태그</p>
            </NavLink>
            <NavLink activeclassname='active' className='home-link' to='/users'>
              <p>사용자</p>
            </NavLink>
            <NavLink activeclassname='active' className='home-link' to='/talk'>
              <p>코톡</p>
            </NavLink>
          </div>
        </div>
      </SidebarUI.Content>
      {hasOverlay ? <SidebarUI.Overlay onClick={() => openSidebar(false)} /> : false}
    </SidebarUI>
  )
}

export default MobileSideBar
