// 기본패키지들 임포트
import React, { useEffect } from "react"
import { Provider } from "react-redux"
import { Switch } from "react-router-dom"
import store from "./redux/store"
import setAuthToken from "./redux/auth/auth.utils"
import { loadUser } from "./redux/auth/auth.actions"

// 컴포넌트들(페이지, 콤포, 레이아웃등)을 들고옴
import Header from "./components/Layouts/Header/Header.component"
// import Alert from "./components/Alert/Alert.component"
import HomePage from "./Pages/HomePage/HomePage.component"
import QuestionsPage from "./Pages/QuestionsPage/QuestionsPage.component"
import AllTagsPage from "./Pages/AllTagsPage/AllTagsPage.component"
import AllUsersPage from "./Pages/AllUsersPage/AllUsersPage.component"
import Register from "./Pages/Register/Register.component"
import Login from "./Pages/Login/Login.component"
import Post from "./Pages/Post/Post.component"
import PostForm from "./Pages/PostForm/PostForm.component"
import TagPage from "./Pages/TagPage/TagPage.component"
import ProfilePage from "./Pages/ProfilePage/ProfilePage.component"
import NotFound from "./Pages/NotFound/NotFound.component"
import { BaseRoute, LayoutRoute } from "./Router"

// css 추가
import "./App.css"

if (localStorage.token) {
  setAuthToken(localStorage.token)
}

const App = () => {
  useEffect(() => {
    store.dispatch(loadUser())
  }, [])

  return (
    <Provider store={store}>
      <div className='App'>
        <Header />
        {/* App에 헤더만 있고 사이드, 푸터가 안보이는데
            좌, 우 사이드바, 푸터가 필요한 페이지는 LayoutRoute
            아무것도 안넣을 페이지는 BaseRoute 컴포넌트로 만들어주면된다. 
        */}

        {/*  */}

        {/* <Alert /> */}
        <Switch>
          {/* 
              {}로 감싸진 부분이 props로 넘어갈 부분
              {
              exact path : "" 에 url 경로
              title : "" 보여질 타이틀
              }
              <보여줄 컴포넌트 /> 를 넣어주면 됨
           */}
          <LayoutRoute
            exact
            path='/'
            title='코플로와 - 막히는 부분, 궁금한 부분등을 물어보거나 자신의 지식을 공유해 보아요'
          >
            <HomePage />
          </LayoutRoute>

          <LayoutRoute exact path='/questions' title='질문 - 코플로와'>
            <QuestionsPage />
          </LayoutRoute>
          <LayoutRoute exact path='/tags' title='태그 - 코플로와'>
            <AllTagsPage />
          </LayoutRoute>
          <LayoutRoute exact path='/users' title='사용자 - 코플로와'>
            <AllUsersPage />
          </LayoutRoute>
          <BaseRoute exact path='/register' title='회원가입 - 코플로와'>
            <Register />
          </BaseRoute>
          <BaseRoute exact path='/login' title='로그인 - 코플로와'>
            <Login />
          </BaseRoute>
          <LayoutRoute exact path='/questions/:id' title='Users - 코플로와'>
            <Post />
          </LayoutRoute>
          <LayoutRoute exact path='/users/:id' title='Users - 코플로와'>
            <ProfilePage />
          </LayoutRoute>
          <LayoutRoute exact path='/tags/:tagname' title='Users - 코플로와'>
            <TagPage />
          </LayoutRoute>
          <BaseRoute exact path='/add/question' title='질문하기 - 코플로와'>
            <PostForm />
          </BaseRoute>
          <BaseRoute path='*' title='Error 404'>
            <NotFound />
          </BaseRoute>
        </Switch>
      </div>
    </Provider>
  )
}

export default App
