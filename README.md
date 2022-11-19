# 코플로와 (koflowa)

![main](/README/main.png)

## 기획 배경

국내 개발자 인원이 빠르게 증가하고 있는 상황에서 국내 개발 관련 플랫폼은 아직 크게 형성되지 않은 실정입니다. 저희는 개발자들에게 실질적인 도움을 줄 수 있는 플랫폼이 필요하다고 생각했습니다.

그렇게 코플로와가 탄생했습니다.

서비스를 구체화 하기 위해 국내 개발자 커뮤니티를 조사 했고, 주로 어느 이용 계층에서 플랫폼을 소모하는지 분류해 대상을 구체화했습니다. 크게, 개발 관련 지식을 습득하고자 하는 사람들 그리고 다른 개발자를 만나고자 하는 사람들로 분류 했습니다. 고객들이 요구하는 기능인 개발 지식 공유 기능, 의사소통 기능을 바탕으로 코플로와 커뮤니티 사이트의 기능을 구체화했습니다.

## 주요 기능 소개

### 태그 시스템

태그 시스템은 코플로와의 핵심 기능입니다.
나만의 선호 태그를 등록할 수 있고 질문 등록할 때마다 태그들을 등록해 같은 언어를 사용하는 개발자들에게 더 빠르게 노출시킬 수 있습니다. 이렇게 기록된 태그 정보를 기반으로 질문을 검색해주거나 추천해주어 이용자들에게 검색의 편리함을 더해줍니다.

- 나의 선호 태그 등록
- 질문 등록 시 태그 등록
- 태그 기반의 질문 검색
- 태그 기반의 질문 추천

### 명성 시스템

커뮤니티 활동의 보상으로 명성치를 부여해 활동을 장려하는 시스템입니다. 명성치가 쌓임으로 랭킹 산정이나 태그 생성 등에 사용됩니다.

- 명성을 통한 활동 장려
- 명성을 통한 권한 부여

### 커뮤니케이션 시스템

커뮤니케이션 시스템은 다른 개발자들을 만나거나 팀원을 구하는 유저들을 위해 구현 되었습니다. 코플로와에서의 소통 수단 '코톡'을 이용해 상대방과 메시지를 주고 받거나 화상회의 초대장을 보내어 화상 코드 리뷰 등 활동을 할 수 있습니다.

- 채팅 기능
- 화상 회의 기능
- 화면 공유 기능

## 주요 기술 스택

![기술 스택](/README/skill.png)

## 아키텍쳐

![Architecture](/README/Architecture.png)

## 프론트 구조

```
.
├── App.css
├── App.js
├── Pages
│   ├── AllTagsPage
│   │   ├── AllTagsPage.component.jsx
│   │   ├── AllTagsPage.styles.scss
│   │   └── TagPanel
│   │       └── TagPanel.component.jsx
│   ├── AllUsersPage
│   │   ├── AllUsersPage.component.jsx
│   │   ├── AllUsersPage.styles.scss
│   │   └── UserPanel
│   │       ├── UserPanel.component.jsx
│   │       └── UserPanel.styles.scss
│   ├── ChattingPage
│   │   └── ChattingPage.component.jsx
│   ├── HomePage
│   │   ├── HomePage.component.jsx
│   │   └── HomePage.styles.scss
│   ├── Login
│   │   └── Login.component.jsx
│   ├── MeetingCallPage
│   │   ├── assets
│   │   │   └── images
│   │   │       └── openvidu_logo.png
│   │   ├── components
│   │   │   ├── VideoRoomComponent.css
│   │   │   ├── VideoRoomComponent.js
│   │   │   ├── chat
│   │   │   │   ├── ChatComponent.css
│   │   │   │   └── ChatComponent.js
│   │   │   ├── dialog-extension
│   │   │   │   ├── DialogExtension.css
│   │   │   │   └── DialogExtension.js
│   │   │   ├── stream
│   │   │   │   ├── OvVideo.js
│   │   │   │   ├── StreamComponent.css
│   │   │   │   └── StreamComponent.js
│   │   │   └── toolbar
│   │   │       ├── ToolbarComponent.css
│   │   │       └── ToolbarComponent.js
│   │   ├── index.css
│   │   ├── index.js
│   │   ├── layout
│   │   │   └── openvidu-layout.js
│   │   ├── models
│   │   │   └── user-model.js
│   │   └── registerServiceWorker.js
│   ├── NotFound
│   │   ├── NotFound.component.jsx
│   │   └── NotFound.styles.scss
│   ├── Post
│   │   ├── AnswerSection
│   │   │   ├── AnswerForm
│   │   │   │   ├── AnswerForm.component.jsx
│   │   │   │   └── AnswerForm.styles.scss
│   │   │   ├── AnswerItem
│   │   │   │   ├── AnswerItem.component.jsx
│   │   │   │   └── AnswerItem.styles.scss
│   │   │   ├── AnswerSection.component.jsx
│   │   │   └── AnswerSection.styles.scss
│   │   ├── Post.component.jsx
│   │   ├── Post.styles.scss
│   │   └── QuestionSection
│   │       ├── CommentCell
│   │       │   ├── CommentCell.component.jsx
│   │       │   └── CommentCell.styles.scss
│   │       ├── PostCell
│   │       │   ├── PostCell.component.jsx
│   │       │   └── PostCell.styles.scss
│   │       ├── QuestionSection.component.jsx
│   │       ├── QuestionSection.styles.scss
│   │       └── VoteCell
│   │           ├── VoteCell.component.jsx
│   │           └── VoteCell.styles.scss
│   ├── PostForm
│   │   ├── AskForm
│   │   │   ├── AskForm.component.jsx
│   │   │   ├── AskForm.styles.scss
│   │   │   └── countries.js
│   │   ├── AskWidget
│   │   │   ├── AskWidget.component.jsx
│   │   │   └── AskWidget.styles.scss
│   │   ├── PostForm.component.jsx
│   │   └── PostForm.styles.scss
│   ├── ProfilePage
│   │   ├── ExternalUserDetails
│   │   │   ├── ExternalUserDetails.component.jsx
│   │   │   └── ExternalUserDetails.styles.scss
│   │   ├── ProfilePage.component.jsx
│   │   ├── ProfilePage.styles.scss
│   │   ├── UserActivity
│   │   │   ├── UserActivity.styles.scss
│   │   │   ├── UserAnswerActivity.component.jsx
│   │   │   ├── UserContent.component.jsx
│   │   │   ├── UserQuestionActivity.component.jsx
│   │   │   ├── UserTag.component.jsx
│   │   │   └── UserTagActivity.component.jsx
│   │   └── UserSection
│   │       ├── AvatarCard
│   │       │   ├── AvatarCard.component.jsx
│   │       │   └── AvatarCard.styles.scss
│   │       ├── ContentCard
│   │       │   ├── ContentCard.component.jsx
│   │       │   └── ContentCard.styles.scss
│   │       ├── UserSection.component.jsx
│   │       └── UserSection.styles.scss
│   ├── QuestionsPage
│   │   ├── QuestionsPage.component.jsx
│   │   └── QuestionsPage.styles.scss
│   ├── Register
│   │   ├── Caption
│   │   │   ├── Caption.component.jsx
│   │   │   └── Caption.styles.scss
│   │   ├── Nickname.component.jsx
│   │   ├── Nickname.styles.scss
│   │   ├── Register.component.jsx
│   │   ├── Register.styles.scss
│   │   └── img
│   │       └── google.png
│   ├── TagForm
│   │   ├── AskForm
│   │   │   ├── AskForm.component.jsx
│   │   │   └── AskForm.styles.scss
│   │   ├── AskWidget
│   │   │   ├── AskWidget.component.jsx
│   │   │   └── AskWidget.styles.scss
│   │   ├── TagForm.component.jsx
│   │   └── TagForm.styles.scss
│   ├── TagPage
│   │   ├── TagPage.component.jsx
│   │   └── TagPage.styles.scss
│   └── TestPage
│       └── TestPage.jsx
├── Router.jsx
├── api
│   ├── answer.js
│   ├── api.js
│   ├── meeting.js
│   ├── mypages.js
│   ├── question.js
│   ├── sign.js
│   ├── tags.js
│   ├── talk.js
│   └── urls.js
├── assets
├── components
│   ├── Alert
│   │   ├── Alert.component.jsx
│   │   └── Alert.styles.scss
│   ├── Components
│   │   ├── BaseButton
│   │   │   └── BaseButton.component.jsx
│   │   ├── ButtonGroup
│   │   │   └── ButtonGroup.component.jsx
│   │   ├── LinkButton
│   │   │   └── LinkButton.component.jsx
│   │   ├── PostItem
│   │   │   ├── PostItem.component.jsx
│   │   │   └── PostItem.styles.scss
│   │   ├── SearchBox
│   │   │   ├── SearchBox.component.jsx
│   │   │   └── SearchBox.styles.scss
│   │   ├── Spinner
│   │   │   ├── Spinner.component.jsx
│   │   │   └── Spinner.styles.scss
│   │   ├── TagBadge
│   │   │   ├── TagBadge.component.jsx
│   │   │   └── TagBadge.styles.scss
│   │   └── UserCard
│   │       ├── UserCard.component.jsx
│   │       └── UserCard.styles.scss
│   ├── Layouts
│   │   ├── AuthForm
│   │   │   ├── AuthForm.component.jsx
│   │   │   └── AuthForm.styles.scss
│   │   ├── Footer
│   │   │   ├── Footer.component.jsx
│   │   │   └── Footer.styles.scss
│   │   ├── Header
│   │   │   ├── Header.component.jsx
│   │   │   └── Header.styles.scss
│   │   ├── LayoutWrapper
│   │   │   ├── LayoutWrapper.component.jsx
│   │   │   ├── LayoutWrapperAll.component.jsx
│   │   │   ├── RightSideBar
│   │   │   │   ├── CustomTagWidget
│   │   │   │   │   └── CustomTagWidget.component.jsx
│   │   │   │   ├── RightSideBar.component.jsx
│   │   │   │   ├── RightSideBar.styles.scss
│   │   │   │   ├── TagList.component.jsx
│   │   │   │   └── TagsWidget
│   │   │   │       ├── TagsWidget
│   │   │   │       ├── TagsWidget.component.jsx
│   │   │   │       ├── TagsWidget.styles.scss
│   │   │   │       ├── TagsWidgetItem.component.jsx
│   │   │   │       └── TagsWidgetItem.styles.scss
│   │   │   └── SideBar
│   │   │       ├── SideBar.component.jsx
│   │   │       ├── SideBar.styles.scss
│   │   │       └── SideBarItem.component.jsx
│   │   ├── MarkdownEditor
│   │   │   ├── MarkdownEditor.component.jsx
│   │   │   └── MarkdownEditor.styles.scss
│   │   ├── MobileSideBar
│   │   │   ├── MobileSideBar.component.jsx
│   │   │   └── MobileSideBar.styles.scss
│   │   └── Pagination
│   │       └── Pagination.component.jsx
│   ├── PageTitle
│   │   └── PageTitle.component.jsx
│   └── atoms
│       └── box.atom.jsx
├── hooks
│   └── usePageTitle.jsx
├── index.js
├── redux
│   ├── alert
│   │   ├── alert.actions.js
│   │   ├── alert.reducer.js
│   │   └── alert.types.js
│   ├── slice
│   │   ├── AnswerSlice.js
│   │   ├── AuthSlice.js
│   │   ├── CharSlice.js
│   │   ├── MeetingSlice.js
│   │   ├── MypageSlice.js
│   │   ├── TagSlice.js
│   │   └── TalkSlice.js
│   └── store.js
└── utils
    ├── censorBadWords.js
    ├── handleFilter.js
    ├── handleSorting.js
    ├── htmlSubstring.js
    └── injectEllipsis.js
```

## 백엔드 구조

```
.
├── src/main/java
│   └── com
│       └── d202
│           └── koflowa
│               ├── KoflowaApplication.java
│               ├── S_J_O
│               │   ├── TokenMapping.java
│               │   ├── advice
│               │   │   ├── ApiControllerAdvice.java
│               │   │   ├── assertThat
│               │   │   │   └── DefaultAssert.java
│               │   │   ├── error
│               │   │   │   ├── DefaultAuthenticationException.java
│               │   │   │   ├── DefaultException.java
│               │   │   │   ├── DefaultNullPointerException.java
│               │   │   │   └── InvalidParameterException.java
│               │   │   └── payload
│               │   │       ├── ErrorCode.java
│               │   │       └── ErrorResponse.java
│               │   ├── auth
│               │   │   └── AuthController.java
│               │   ├── payload
│               │   │   ├── request
│               │   │   │   └── auth
│               │   │   │       ├── ChangePasswordRequest.java
│               │   │   │       ├── RefreshTokenRequest.java
│               │   │   │       ├── SignInRequest.java
│               │   │   │       └── SignUpRequest.java
│               │   │   └── response
│               │   │       ├── ApiResponse.java
│               │   │       ├── AuthResponse.java
│               │   │       └── Message.java
│               │   ├── repository
│               │   │   └── auth
│               │   │       ├── CustomAuthorizationRequestRepository.java
│               │   │       └── TokenRepository.java
│               │   ├── security
│               │   │   ├── OAuth2Config.java
│               │   │   ├── SecurityConfig.java
│               │   │   ├── WebMvcConfig.java
│               │   │   ├── auth
│               │   │   │   ├── OAuth2UserInfo.java
│               │   │   │   ├── OAuth2UserInfoFactory.java
│               │   │   │   └── company
│               │   │   │       └── Google.java
│               │   │   ├── handler
│               │   │   │   ├── CustomSimpleUrlAuthenticationFailureHandler.java
│               │   │   │   └── CustomSimpleUrlAuthenticationSuccessHandler.java
│               │   │   ├── token
│               │   │   │   ├── CurrentUser.java
│               │   │   │   ├── CustomAuthenticationEntryPoint.java
│               │   │   │   ├── CustomOncePerRequestFilter.java
│               │   │   │   └── UserPrincipal.java
│               │   │   └── util
│               │   │       └── CustomCookie.java
│               │   └── service
│               │       └── auth
│               │           ├── AuthService.java
│               │           ├── CustomDefaultOAuth2UserService.java
│               │           ├── CustomTokenProviderService.java
│               │           └── CustomUserDetailsService.java
│               ├── answer
│               │   ├── controller
│               │   │   └── AnswerController.java
│               │   ├── domain
│               │   │   ├── Answer.java
│               │   │   ├── AnswerUpdown.java
│               │   │   └── Comment.java
│               │   ├── dto
│               │   │   ├── AnswerDto.java
│               │   │   ├── AnswerUpdownDto.java
│               │   │   └── CommentDto.java
│               │   ├── repository
│               │   │   ├── AnswerRepository.java
│               │   │   ├── AnswerUpDownRepository.java
│               │   │   └── CommentRepository.java
│               │   └── service
│               │       └── AnswerService.java
│               ├── common
│               │   ├── domain
│               │   │   ├── AuthProvider.java
│               │   │   ├── BaseTimeEntity.java
│               │   │   ├── CDType.java
│               │   │   ├── CreateTimeEntity.java
│               │   │   ├── QAType.java
│               │   │   ├── Role.java
│               │   │   ├── TagStatus.java
│               │   │   └── UDType.java
│               │   ├── dto
│               │   │   └── ResponseDto.java
│               │   ├── exception
│               │   │   └── CommentNotFoundException.java
│               │   └── response
│               │       ├── Failure.java
│               │       ├── Response.java
│               │       ├── Result.java
│               │       └── Success.java
│               ├── config
│               │   ├── S3Config.java
│               │   ├── SwaggerConfig.java
│               │   ├── WebConfig.java
│               │   └── WebSocketConfig.java
│               ├── exception
│               │   ├── AnswerNotFoundException.java
│               │   ├── AnswerUpdownExistException.java
│               │   ├── ExceptionAdvice.java
│               │   ├── ImageConvertException.java
│               │   ├── QuestionNotFoundException.java
│               │   ├── SessionNotFoundException.java
│               │   ├── TagExistException.java
│               │   ├── TagNotFoundException.java
│               │   ├── UserNotFoundException.java
│               │   ├── UserTagExistException.java
│               │   └── UserTagNotFoundException.java
│               ├── meeting
│               │   ├── controller
│               │   │   └── MeetingController.java
│               │   ├── dto
│               │   │   ├── SessionDto.java
│               │   │   └── TokenDto.java
│               │   └── service
│               │       └── MeetingService.java
│               ├── question
│               │   ├── controller
│               │   │   └── QuestionController.java
│               │   ├── domain
│               │   │   ├── Question.java
│               │   │   ├── QuestionTag.java
│               │   │   └── QuestionUpdown.java
│               │   ├── dto
│               │   │   ├── QuestionDto.java
│               │   │   └── QuestionUpdownDto.java
│               │   ├── exception
│               │   │   ├── QuestionCommentNotFoundException.java
│               │   │   ├── QuestionUpException.java
│               │   │   ├── QuestionUserNotFoundException.java
│               │   │   └── SpecificQuestionNotFound.java
│               │   ├── repository
│               │   │   ├── QuestionRepository.java
│               │   │   ├── QuestionTagRepository.java
│               │   │   └── QuestionUpDownRepository.java
│               │   └── service
│               │       └── QuestionService.java
│               ├── redis
│               │   ├── controller
│               │   │   └── RedisTestController.java
│               │   ├── dto
│               │   │   └── RedisTest.java
│               │   ├── repository
│               │   │   └── RedisTestRepository.java
│               │   └── service
│               │       └── RedisTestService.java
│               ├── tag
│               │   ├── controller
│               │   │   └── TagController.java
│               │   ├── domain
│               │   │   └── Tag.java
│               │   ├── dto
│               │   │   └── TagDto.java
│               │   ├── repository
│               │   │   └── TagRepository.java
│               │   └── service
│               │       └── TagService.java
│               ├── talk
│               │   ├── controller
│               │   │   ├── MessageController.java
│               │   │   ├── RoomController.java
│               │   │   └── socket
│               │   │       ├── ChatController.java
│               │   │       └── ChatRoomController.java
│               │   ├── domain
│               │   │   ├── Message.java
│               │   │   ├── MessageLog.java
│               │   │   └── Room.java
│               │   ├── dto
│               │   │   ├── MessageDto.java
│               │   │   ├── MessageLogDto.java
│               │   │   ├── RoomDto.java
│               │   │   └── socket
│               │   │       ├── ChatDto.java
│               │   │       └── ChatRoomDto.java
│               │   ├── exception
│               │   │   ├── MessageListLoadException.java
│               │   │   ├── MessageLogNotFoundException.java
│               │   │   ├── MessageNotFoundException.java
│               │   │   ├── MessageNotSavedException.java
│               │   │   ├── Room1NoFoundException.java
│               │   │   ├── Room2NoFoundException.java
│               │   │   ├── RoomDeleteFailureException.java
│               │   │   ├── RoomNotCreatedException.java
│               │   │   ├── RoomNotFoundException.java
│               │   │   └── User1NotFoundException.java
│               │   ├── repository
│               │   │   ├── ChatRoomRepository.java
│               │   │   ├── MessageLogRepository.java
│               │   │   ├── MessageRepository.java
│               │   │   └── RoomRepository.java
│               │   └── service
│               │       ├── MessageService.java
│               │       └── RoomService.java
│               └── user
│                   ├── controller
│                   │   ├── MyPageController.java
│                   │   └── NicknameController.java
│                   ├── domain
│                   │   ├── ReputationLog.java
│                   │   ├── User.java
│                   │   └── UserTag.java
│                   ├── dto
│                   │   ├── ReputationLogDto.java
│                   │   ├── UserDto.java
│                   │   ├── UserTagCntDto.java
│                   │   └── UserTagDto.java
│                   ├── exception
│                   │   └── UserNotFoundException.java
│                   ├── repository
│                   │   ├── ReputationLogRepository.java
│                   │   ├── UserRepository.java
│                   │   └── UserTagRepository.java
│                   └── service
│                       ├── MyPageService.java
│                       ├── NicknameService.java
│                       ├── ReputationService.java
│                       └── UploadImgService.java
├── resources
│   ├── application-oauth.yml
│   └── application-secret.yml
├── resources-aws
│   └── application.yml
└── resources-dev
    └── application.yml

```

## ERD

![ERD](/README/ERD_koflowa.png)

## 개발 기간 및 팀원 역할 소개

### 개발 기간

2022-10-10 ~ 2022-11-25 (6주)

### 팀원

<table>
    <tr>
        <td height="140px" align="center"> <a href="https://github.com/devyoseph">
            <img src="https://avatars.githubusercontent.com/u/89521661?v=4" width="140px" /> <br>양요셉</a> <br>
        </td>
        <td height="140px" align="center"> <a href="https://github.com/ljhyung">
            <img src="https://avatars.githubusercontent.com/u/97655625?v=4" width="140px" /> <br>이주형</a> <br>
        </td>
        <td height="140px" align="center"> <a href="https://github.com/mhlee21">
            <img src="https://avatars.githubusercontent.com/u/79842676?v=4" width="140px" /> <br>이미현</a> <br>
        </td>
        <td height="140px" align="center"> <a href="https://github.com/YeonJeongLee00">
            <img src="https://avatars.githubusercontent.com/u/67946956?v=4" width="140px" /> <br>이연정</a> <br>
        </td>
        <td height="140px" align="center"> <a href="https://github.com/S2econdBlue">
            <img src="https://avatars.githubusercontent.com/u/101093707?v=4" width="140px" /> <br>이청</a> <br>
        </td>
        <td height="140px" align="center"> <a href="https://github.com/sw133v">
            <img src="https://avatars.githubusercontent.com/u/80740256?v=4" width="140px" /> <br>정성우</a> <br>
        </td>
    </tr>
    <tr>
        <td align="center">
        팀장
        </td>
        <td align="center">
        부팀장
        </td>
        <td align="center">
        팀원
        </td>
        <td align="center">
        팀원
        </td>
        <td align="center">
        팀원
        </td>
        <td align="center">
        팀원
        </td>
    </tr>
</table>

# 프로젝트 산출물

## Notion

[코플로와 노션 바로가기](https://www.notion.so/Cheer-Up-Koflowa-df58afae0ed14b728aa75a81dcb71f78)

## Mockup

![mockup](/README/mockup.png)
