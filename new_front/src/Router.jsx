import React from "react";
import { Route } from "react-router-dom";

import LayoutWrapper from "./components/Layouts/LayoutWrapper/LayoutWrapper.component";

import usePageTitle from "./hooks/usePageTitle";
// 이부분은 페이지 타이틀 변경을 위한훅 신경x

export const LayoutRoute = ({ title, children, ...props }) => {
  usePageTitle(title);

  return (
    <Route {...props}>
      <LayoutWrapper>{children}</LayoutWrapper>
    </Route>
  );
};

export const BaseRoute = ({ title, children, ...props }) => {
  usePageTitle(title);

  return <Route {...props}>{children}</Route>;
};
