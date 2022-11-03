import React from "react"
import Helmet from "react-helmet"

const PageTitle = ({ title }) => {
  let defaultTitle =
    "코플로와 - 막히는 부분, 궁금한 부분등을 물어보거나 자신의 지식을 공유해 보아요"

  return (
    <Helmet>
      <title>{title ? title : defaultTitle}</title>
    </Helmet>
  )
}

export default PageTitle
