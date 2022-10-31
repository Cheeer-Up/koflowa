import React from "react";
import { Route } from "react-router-dom";

import PageTitle from "./PageTitle";

export const BaseRoute = ({ title, children, ...props }) => {
  PageTitle(title);
  return <Route {...props}>{children}</Route>;
};
