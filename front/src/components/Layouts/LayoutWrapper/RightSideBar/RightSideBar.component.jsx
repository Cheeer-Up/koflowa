import React, { Fragment } from "react"

import TagsWidget from "./TagsWidget/TagsWidget.component"
import WatchedTagWidget from "./WatchedTagWidget/WatchedTagWidget.component"
import IgnoreTagWidget from "./IgnoreTagWidget/IgnoreTagWidget.component"

import "./RightSideBar.styles.scss"

const RightSideBar = () => {
  return (
    <Fragment>
      <div id='sidebar' className='side-bar'>
        <WatchedTagWidget />
        <IgnoreTagWidget />
        <TagsWidget />
      </div>
    </Fragment>
  )
}

export default RightSideBar
