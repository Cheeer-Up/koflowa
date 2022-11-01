import React, { useEffect } from "react";
import { Provider } from "react-redux";
import { Switch } from "react-router-dom";

import store from "./redux/store";
import setAuthToken from "./redux/auth/auth.utils";
import { loadUser } from "./redux/auth/auth.actions";

import Header from "./components/Layouts/Header/Header.component";
import Alert from "./components/Alert/Alert.component";
import HomePage from "./Pages/HomePage/HomePage.component";
import QuestionsPage from "./Pages/QuestionsPage/QuestionsPage.component";
import AllTagsPage from "./Pages/AllTagsPage/AllTagsPage.component";
import AllUsersPage from "./Pages/AllUsersPage/AllUsersPage.component";
import Register from "./Pages/Register/Register.component";
import Login from "./Pages/Login/Login.component";
import Post from "./Pages/Post/Post.component";
import PostForm from "./Pages/PostForm/PostForm.component";
import TagPage from "./Pages/TagPage/TagPage.component";
import ProfilePage from "./Pages/ProfilePage/ProfilePage.component";
import NotFound from "./Pages/NotFound/NotFound.component";

import { BaseRoute, LayoutRoute } from "./Router";

import "./App.css";

if (localStorage.token) {
  setAuthToken(localStorage.token);
}

const App = () => {
  useEffect(() => {
    store.dispatch(loadUser());
  }, []);

  return (
    <Provider store={store}>
      <div className="App">
        <Header />
        <Alert />
        <Switch>
          <LayoutRoute
            exact
            path="/"
            title="CLONE Stack Overflow - Where Developers Learn, Share, & Build Careers"
          >
            <HomePage />
          </LayoutRoute>
          <LayoutRoute
            exact
            path="/questions"
            title="All Questions - CLONE Stack Overflow"
          >
            <QuestionsPage />
          </LayoutRoute>
          <LayoutRoute exact path="/tags" title="Tags - CLONE Stack Overflow">
            <AllTagsPage />
          </LayoutRoute>
          <LayoutRoute exact path="/users" title="Users - CLONE Stack Overflow">
            <AllUsersPage />
          </LayoutRoute>
          <BaseRoute
            exact
            path="/register"
            title="Sign Up - CLONE Stack Overflow"
          >
            <Register />
          </BaseRoute>
          <BaseRoute exact path="/login" title="Log In - CLONE Stack Overflow">
            <Login />
          </BaseRoute>
          <LayoutRoute
            exact
            path="/questions/:id"
            title="Users - CLONE Stack Overflow"
          >
            <Post />
          </LayoutRoute>
          <LayoutRoute
            exact
            path="/users/:id"
            title="Users - CLONE Stack Overflow"
          >
            <ProfilePage />
          </LayoutRoute>
          <LayoutRoute
            exact
            path="/tags/:tagname"
            title="Users - CLONE Stack Overflow"
          >
            <TagPage />
          </LayoutRoute>
          <BaseRoute
            exact
            path="/add/question"
            title="Ask a Question - CLONE Stack Overflow"
          >
            <PostForm />
          </BaseRoute>
          <BaseRoute path="*" title="Error 404">
            <NotFound />
          </BaseRoute>
        </Switch>
      </div>
    </Provider>
  );
};

export default App;
