// import logo from "./logo.svg";
import React, { useEffect } from "react";
// import { Provider } from "react-redux";
import { Routes, Route } from "react-router-dom";
import { useLocation } from "react-router-dom";

// import store from "./redux/store.js";

import "./App.css";
import NotFound from "./components/page/NotFound/NotFound.jsx";
import MainPage from "./components/page/MainPage/MainPage.jsx";
import QuestionListPage from "./components/page/QuestionListPage/QuestionListPage.jsx";
import TagListPage from "./components/page/TagListPage/TagListPage.jsx";
import TagDetailPage from "./components/page/TagDetailPage/TagDetailPage.jsx";
import UserListPage from "./components/page/UserListPage/UserListPage.jsx";

const titles = {
  "/": "코플로와 - 궁금한 것을 물어보고 & 여러분들의 지식을 나눠 보세요!",
  "/tags": "태그 - 코플로와",
  "/questions": "질문 - 코플로와",
  "/users": "사용자 - 코플로와",
  // "/*": "404오류 페이지를 찾을수 없습니다 - 코플로와",
};

function App() {
  const location = useLocation();
  useEffect(() => (document.title = titles[location.pathname]), [location]);
  return (
    <div className='App'>
      <Routes>
        <Route
          path='/'
          title='메인페이지'
          element={
            <MainPage title='코플로와 - 궁금한 것을 물어보고 & 여러분들의 지식을 나눠 보세요!' />
          }
        />
        <Route path='/tags' element={<TagListPage />} />
        <Route path='/tags/:tag' element={<TagDetailPage />} />
        <Route path='/questions' element={<QuestionListPage />} />
        <Route path='/users' element={<UserListPage />} />
        <Route
          path='*'
          element={<NotFound title='404오류 페이지를 찾을수 없습니다 - 코플로와' />}
        />
      </Routes>
    </div>
  );
}

export default App;

// <div className='App'>
//   <header className='App-header'>
//     {/* <img src={logo} className='App-logo' alt='logo' /> */}
//     <p>
//       Edit <code>src/App.js</code> and save to reload.
//     </p>
//     <a
//       className='App-link'
//       href='https://reactjs.org'
//       target='_blank'
//       rel='noopener noreferrer'
//     >
//       Learn React
//     </a>
//   </header>
// </div>

// <Provider store={store}>
// </Provider>
