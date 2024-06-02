import React from "react";
import Profile from "../../components/MyPage/Profile";
import MyCrop from "../../components/MyPage/MyCrop";
import MyPost from "../../components/MyPage/MyPost";
import MySpace from "../../components/MyPage/MySpace";

const MyPage = () => {
  return (
    <div>
      <Profile />
      <MyCrop />
      <MyPost />
      <MySpace />
    </div>
  );
};

export default MyPage;
