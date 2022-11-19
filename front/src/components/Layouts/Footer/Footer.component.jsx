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
          {/* <div className='social-item'>
            <a href='https://github.com/' target='_blank' rel='noreferrer'>
              <GitHub />
            </a>
            <p>
              <strong>Frontend</strong>
            </p>
          </div>
          <div className='social-item'>
            <a href='https://naver.com' target='_blank' rel='noreferrer'>
              <Database />
            </a>
            <p>
              <strong>Backend</strong>
            </p>
          </div> */}
          <div className='social-item'>
            <a href={process.env.REACT_APP_PUBLIC_URL} target='_self' rel='noreferrer'>
              <Koflowa />
            </a>
          </div>
        </div>
      </div>
    </Fragment>
  )
}

export default Footer
