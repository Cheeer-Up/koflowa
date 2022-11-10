import React, { Component } from "react"
import "./ToolbarComponent.css"
import { NavLink } from "react-router-dom"

import AppBar from "@mui/material/AppBar"
import Toolbar from "@mui/material/Toolbar"

import { ReactComponent as Logo } from "assets/KoflowaHeaderMdDark.svg"
import { ReactComponent as SmallLogo } from "assets/KoflowaHeaderTextDark.svg"
import Mic from "@mui/icons-material/Mic"
import MicOff from "@mui/icons-material/MicOff"
import Videocam from "@mui/icons-material/Videocam"
import VideocamOff from "@mui/icons-material/VideocamOff"
import Fullscreen from "@mui/icons-material/Fullscreen"
import FullscreenExit from "@mui/icons-material/FullscreenExit"
import SwitchVideoIcon from "@mui/icons-material/SwitchVideo"
import PictureInPicture from "@mui/icons-material/PictureInPicture"
import ScreenShare from "@mui/icons-material/ScreenShare"
import StopScreenShare from "@mui/icons-material/StopScreenShare"
import Tooltip from "@mui/material/Tooltip"
import PowerSettingsNew from "@mui/icons-material/PowerSettingsNew"
import QuestionAnswer from "@mui/icons-material/QuestionAnswer"

import IconButton from "@mui/material/IconButton"

const logo = require("../../assets/images/openvidu_logo.png")

export default class ToolbarComponent extends Component {
  constructor(props) {
    super(props)
    this.state = { fullscreen: false }
    this.camStatusChanged = this.camStatusChanged.bind(this)
    this.micStatusChanged = this.micStatusChanged.bind(this)
    this.screenShare = this.screenShare.bind(this)
    this.stopScreenShare = this.stopScreenShare.bind(this)
    this.toggleFullscreen = this.toggleFullscreen.bind(this)
    this.switchCamera = this.switchCamera.bind(this)
    this.leaveSession = this.leaveSession.bind(this)
    this.toggleChat = this.toggleChat.bind(this)
  }

  micStatusChanged() {
    this.props.micStatusChanged()
  }

  camStatusChanged() {
    this.props.camStatusChanged()
  }

  screenShare() {
    this.props.screenShare()
  }

  stopScreenShare() {
    this.props.stopScreenShare()
  }

  toggleFullscreen() {
    this.setState({ fullscreen: !this.state.fullscreen })
    this.props.toggleFullscreen()
  }

  switchCamera() {
    this.props.switchCamera()
  }

  leaveSession() {
    this.props.leaveSession()
  }

  toggleChat() {
    this.props.toggleChat()
  }

  render() {
    const mySessionId = this.props.sessionId
    const localUser = this.props.user
    return (
      <AppBar className='toolbar' id='header'>
        <Toolbar className='toolbar'>
          <div id='navSessionInfo'>
            {/* <img id='header_img' alt='OpenVidu Logo' src={logo} /> */}
            <NavLink to={"/"}>
              <Logo className='full-logo' />
              <SmallLogo className='glyph-logo' />
            </NavLink>

            {this.props.sessionId && (
              <div id='titleContent'>
                <span id='session-title'>{mySessionId}</span>
              </div>
            )}
          </div>

          <div className='buttonsContent'>
            <IconButton
              color='inherit'
              className='navButton'
              id='navMicButton'
              onClick={this.micStatusChanged}
            >
              {localUser !== undefined && localUser.isAudioActive() ? (
                <Mic color='primary' />
              ) : (
                <MicOff color='error' />
              )}
            </IconButton>

            <IconButton
              color='inherit'
              className='navButton'
              id='navCamButton'
              onClick={this.camStatusChanged}
            >
              {localUser !== undefined && localUser.isVideoActive() ? (
                <Videocam color='primary' />
              ) : (
                <VideocamOff color='error' />
              )}
            </IconButton>

            <IconButton color='primary' className='navButton' onClick={this.screenShare}>
              {localUser !== undefined && localUser.isScreenShareActive() ? (
                <PictureInPicture />
              ) : (
                <ScreenShare />
              )}
            </IconButton>

            {localUser !== undefined && localUser.isScreenShareActive() && (
              <IconButton onClick={this.stopScreenShare} id='navScreenButton' color='primary'>
                <StopScreenShare color='error' />
              </IconButton>
            )}

            <IconButton color='primary' className='navButton' onClick={this.switchCamera}>
              <SwitchVideoIcon />
            </IconButton>
            <IconButton color='primary' className='navButton' onClick={this.toggleFullscreen}>
              {localUser !== undefined && this.state.fullscreen ? (
                <FullscreenExit />
              ) : (
                <Fullscreen />
              )}
            </IconButton>
            <IconButton
              color='error'
              className='navButton'
              onClick={this.leaveSession}
              id='navLeaveButton'
            >
              <PowerSettingsNew />
            </IconButton>
            <IconButton color='primary' onClick={this.toggleChat} id='navChatButton'>
              {this.props.showNotification && <div id='point' className='' />}
              <Tooltip title='Chat'>
                <QuestionAnswer />
              </Tooltip>
            </IconButton>
          </div>
        </Toolbar>
      </AppBar>
    )
  }
}
