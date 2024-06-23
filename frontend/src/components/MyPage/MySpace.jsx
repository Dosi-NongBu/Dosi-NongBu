import React, { useState } from "react";
import "./style/MyEach.css";
import SpaceBoxArea from "../common/SpaceBoxArea";

const MySpace = () => {
  return (
    <div className="myPage-each">
      <div className="myPage-each-container">
        <h2>내 공간 보기</h2>
        <SpaceBoxArea onSelectSpace={() => {}} />
      </div>
    </div>
  );
};

export default MySpace;
