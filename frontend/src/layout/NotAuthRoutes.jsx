// 로그인 된 사람이 로그인, 회원가입 페이지에 들어갈때

import React from "react";
import { Navigate, Outlet } from "react-router-dom";
const NotAuthRoutes = ({ isAuth }) => {
  return isAuth ? <Navigate to={"/"} /> : <Outlet />;
};

export default NotAuthRoutes;
