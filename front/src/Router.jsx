import React from "react"
// import { Route } from "react-router-dom"

import LayoutWrapper from "./components/Layouts/LayoutWrapper/LayoutWrapper.component"
import LayoutWrapperAll from "./components/Layouts/LayoutWrapper/LayoutWrapperAll.component"
import LayoutNoFooterWrapper from "./components/Layouts/LayoutWrapper/LayoutNoFooterWrapper.component"
import usePageTitle from "./hooks/usePageTitle"
// 이부분은 페이지 타이틀 변경을 위한훅 신경x

export const LayoutRoute = ({ title, children, ...props }) => {
  usePageTitle(title)

  return (
    <div {...props}>
      <LayoutWrapper>{children}</LayoutWrapper>
    </div>
  )
}

export const LayoutNoFooterRoute = ({ title, children, ...props }) => {
  usePageTitle(title)

  return (
    <div {...props}>
      <LayoutNoFooterWrapper>{children}</LayoutNoFooterWrapper>
    </div>
  )
}

export const LayoutAllRoute = ({ title, children, ...props }) => {
  usePageTitle(title)

  return (
    <div {...props}>
      <LayoutWrapperAll>{children}</LayoutWrapperAll>
    </div>
  )
}

export const BaseRoute = ({ title, children, ...props }) => {
  usePageTitle(title)

  return <div {...props}>{children}</div>
}
