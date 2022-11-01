# 사용법

```bash
# nodejs 모듈 설치
npm install package.json
or
npm install

# 미리보기 실행
npm start

#  배포를 위한 빌드생성
npm run build

```

# 다뤄져야할 컴포넌트(jsx, js 파일)

## 큰틀

- **App.js** - 라우터 설정

## 레이아웃

- **Header.jsx** : 헤더 내용

  위치 : components/Layouts/Header/

- **Footer.jsx** : 푸터

  위치 : components/Layouts/Footer/

## 페이지

- **홈페이지** : 기본 사이트에 접속하였을때 보여줄 페이지

- **질문리스트 페이지** : 모든 질문글들이 보여질 페이지

  위치 : src/Pages/QuestionPage/

- **태그 페이지** : 모든 태그들의 정보를 보여줄 페이지

  위치 : src/Pages/AllTagsPage/

- **사용자들 페이지** : 모든 사용자들의 정보를 보여줄 페이지

  위치 : src/Pages/AllUsersPage/

  위치 : src/Pages/HomePage/

- **회원가입 페이지** : 회원가입시 보여질 페이지

  위치 : src/Pages/Register/

- **로그인 페이지** : 로그인시 보여질 페이지

  위치 : src/Pages/Login/

- **게시글 상세 페이지** : 게시글 상세정보 ex 질문 내용, 답변 내용, 채택여부 등등등

  위치 : src/Pages/Post/

- **사용자 프로필 페이지** : 특정 사용자들의 정보가 나타나는 페이지

  위치 : src/Pages/ProfilePage/

- **태그 페이지** : 해당 태그의 디테일한 정보가 있고, 관심, 무시 기능이 존재하는 페이지

  위치 : src/Pages/TagPage/

- **질문하는 페이지** : 질문하기 버튼을 눌렀을때 보여질 페이지, 글작성이 여기서 이루어지

  위치 : src/Pages/PostForm

- **404 페이지** : 라우터 테이블상 존재하지 않는 url에 접속했을시 보여질 페이지

  위치 : src/Pages/NotFound/

# 리덕스 사용법

# 컴포넌트 구성법

스니펫 사용해도 상관x

```jsx
// 함수형으로 사용할때
import React from 'react';

function App(props) {
  return (
    <div></div>
  );
}

export default App;

// 화살표 함수 형식
import React from 'react';

const App = () => {
  return (
    <div></div>
  );
};

export default App;

// 클래스형 + 콤포넌트
import React, { Component } from 'react';

class App extends Component {
  render() {
    return (
      <div></div>
    );
  }
}

export default App;
// 등등 다양하게 만들수 있다.
// https://www.hanl.tech/blog/vs-code-react-time-awesome-snippets/ 참조글

```
