import React from "react"
import SideBarItem from "./SideBarItem.component"

import "./SideBar.styles.scss"

const SideBar = () => (
  <div className='side-bar-container'>
    <div className='side-bar-tabs'>
      <SideBarItem isHome={true} link='/' text='홈' />
      <SideBarItem isHome={true} link='/questions' text='질문' />
      <SideBarItem isHome={true} link='/tags' text='태그' />
      <SideBarItem isHome={true} link='/users' text='사용자' />
      <SideBarItem isHome={true} link='/jobs' text='코톡' />
      <SideBarItem isHome={true} link='/meeting' text='화상회의' />
    </div>
  </div>
)

export default SideBar
