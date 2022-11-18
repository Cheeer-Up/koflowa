import React from "react"
import SideBarItem from "./SideBarItem.component"

import "./SideBar.styles.scss"

const SideBar = () => (
  <div className='side-bar-container'>
    <div className='side-bar-tabs'>
      <SideBarItem isHome={true} link='/questions' text='질문' />
      <SideBarItem isHome={true} link='/tags' text='태그' />
      <SideBarItem isHome={true} link='/users' text='사용자' />
      <SideBarItem isHome={true} link='/meeting' text='코톡' />
      <div className='teams-tabs'>
        <a href='https://localhost:3000/meeting' className='title fc-light' target='_blank' rel='noreferrer'>
          화상 회의
        </a>
      </div>
    </div>
  </div>
)

export default SideBar
