import React, { Fragment } from "react"

// import { ReactComponent as GitHub } from "../../../assets/GitHub.svg";
// import { ReactComponent as Database } from "../../../assets/Database.svg";
import { ReactComponent as Koflowa } from "../../../assets/KoflowaFooter.svg"

import "./Footer.styles.scss"

const Footer = () => {
  return (
    <Fragment>
      <div className='footer'>
        <div className='socials'>
          <div className='social-item'>
            <a href={process.env.REACT_APP_PUBLIC_URL} target='_self' rel='noreferrer'>
              <Koflowa />
            </a>
          </div>
        </div>
        <div className='item'>
          <span className='footer-item'>{"ⓒ 코플로와           "}</span>
          <div className='footer-link-list'>
            <span>
              <a
                href='https://dull-jumbo-928.notion.site/Koflowa-52b44b7f45914ef887303bbda5356f20'
                className='footerItem'
              >
                도움말
              </a>
            </span>
            {" | "}
            <span>
              <a href='' className='footerItem'>
                이용약관
              </a>
            </span>
            {" | "}
            <span>
              <a href='' className='footerItem'>
                개인정보 처리지침
              </a>
            </span>
            {" | "}
            <span>
              <a href='' className='footerItem'>
                청소년 보호 정책
              </a>
            </span>
          </div>
        </div>
      </div>
    </Fragment>
  )
}

export default Footer
