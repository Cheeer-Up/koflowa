// 기본패키지들 임포트
import React, { useEffect } from "react"
import { Route, Routes, useLocation, Navigate } from "react-router-dom"

// 컴포넌트들(페이지, 콤포, 레이아웃등)을 들고옴
import Header from "./components/Layouts/Header/Header.component"
// import Alert from "components/Alert/Alert.component"
import HomePage from "Pages/HomePage/HomePage.component"
import QuestionsPage from "Pages/QuestionsPage/QuestionsPage.component"
import AllTagsPage from "Pages/AllTagsPage/AllTagsPage.component"
import AllUsersPage from "./Pages/AllUsersPage/AllUsersPage.component"
import Nickname from "Pages/Register/Nickname.component"
import Login from "Pages/Login/Login.component"
import Post from "Pages/Post/Post.component"
import PostForm from "Pages/PostForm/PostForm.component"
import TagPage from "Pages/TagPage/TagPage.component"
import TagForm from "Pages/TagForm/TagForm.component"
import ProfilePage from "Pages/ProfilePage/ProfilePage.component"
import MeetingCallPage from "Pages/MeetingCallPage/components/VideoRoomComponent"
import NotFound from "Pages/NotFound/NotFound.component"
import { BaseRoute, LayoutRoute, LayoutAllRoute, LayoutNoFooterRoute } from "./Router"
import TalkPage from "Pages/TalkPage/TalkPage.component"
import Footer from "components/Layouts/Footer/Footer.component"
// css 추가

// if (localStorage.token) {
//   setAuthToken(localStorage.token)
// }
const titles = {
  // 해당 페이지에 들어갈 타이틀
  "/": "코플로와 - 막히는 부분, 궁금한 부분등을 물어보거나 자신의 지식을 공유해 보아요",
  "/questions": "질문 - 코플로와",
  "/tags": "태그 - 코플로와",
  "/users": "사용자 - 코플로와",
  "/login": "로그인 - 코플로와",
  "/register": "회원가입 - 코플로와",
  "/404": "이런! 페이지를 찾을수가 없어요 - 코플로와",
  "/add/question": "질문하기 - 코플로와",
}

const App = () => {
  const location = useLocation()
  useEffect(() => (document.title = titles[location.pathname] ?? "코플로와"), [location])

  return (
    <div className='App'>
      <Header />
      {/* App에 헤더만 있고 사이드, 푸터가 안보이는데
            좌, 우 사이드바가 필요한 페이지는 LayoutRoute
            아무것도 안넣을 페이지는 BaseRoute 컴포넌트로 만들어주면된다. 
            푸터는 해당 페이지 컴포넌트를 불러올때 추가로 작성해 줘야한다.
          */}
      <Routes>
        {/* 홈 */}
        <Route
          path='/'
          element={
            <BaseRoute>
              <HomePage />
              <Footer />
            </BaseRoute>
          }
        />
        {/* 질문 페이지 */}
        <Route
          path='/questions'
          element={
            <LayoutAllRoute>
              <QuestionsPage />
            </LayoutAllRoute>
          }
        />

        {/* 태그 페이지 */}
        <Route
          path='/tags'
          element={
            <LayoutRoute>
              <AllTagsPage />
            </LayoutRoute>
          }
        />
        {/* 태그 상세 페이지 */}
        <Route
          path='/tags/:tagName'
          element={
            <LayoutRoute>
              <TagPage />
            </LayoutRoute>
          }
        />

        {/* 사용자 페이지 */}
        <Route
          path='/users'
          element={
            <LayoutRoute>
              <AllUsersPage />
            </LayoutRoute>
          }
        />

        <Route
          path='/users/:userSeq'
          element={
            <LayoutRoute>
              <ProfilePage />
            </LayoutRoute>
          }
        />

        {/* 로그인 페이지 */}
        <Route
          path='/login'
          element={
            <BaseRoute>
              <Login />
            </BaseRoute>
          }
        />

        {/* 회원가입 페이지 */}
        <Route
          path='/register'
          element={
            <BaseRoute>
              <Login />
            </BaseRoute>
          }
        />

        {/* 회원가입 페이지 */}
        <Route
          path='/nickname'
          element={
            <BaseRoute>
              <Nickname />
            </BaseRoute>
          }
        />

        {/* 질문 생성 페이지 */}
        <Route
          path='/add/question'
          element={
            <BaseRoute>
              <PostForm />
            </BaseRoute>
          }
        />
        <Route
          path='/talk'
          element={
            <LayoutNoFooterRoute>
              <TalkPage />
            </LayoutNoFooterRoute>
          }
        />
        {/* 화상 회의 페이지 */}
        <Route
          path='/meeting'
          element={
            <BaseRoute>
              <MeetingCallPage />
            </BaseRoute>
          }
        />

        {/* 태그 생성 페이지 */}
        <Route
          path='/add/tag'
          element={
            <BaseRoute>
              <TagForm />
            </BaseRoute>
          }
        />

        {/* 404 not found */}
        <Route
          path='/404'
          element={
            <BaseRoute>
              <NotFound />
            </BaseRoute>
          }
        />

        {/* 질문 상세 페이지 */}
        <Route
          path='/questions/:questionSeq'
          element={
            <LayoutRoute>
              <Post />
            </LayoutRoute>
          }
        />

        {/* 못찾는 경로 404로 리다이렉트 */}
        <Route path='*' element={<Navigate to='/404' />} />

        {/* 추가가 되어야할 페이지들 */}
      </Routes>
    </div>
  )
}

export default App
