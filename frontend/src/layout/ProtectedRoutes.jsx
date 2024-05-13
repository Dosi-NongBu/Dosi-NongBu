// 로그인 안된 사람이 로그인이 필요한 페이지에 들어가려 할떄

import React from "react";
import { Outlet, Navigate } from "react-router-dom";

const ProtectedRoutes = ({ isAuth }) => {
  return isAuth ? <Outlet /> : <Navigate to={"/login"} />;
};

export default ProtectedRoutes;
