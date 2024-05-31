import React, { useState } from "react";
import "./style/MySpace.css";
import SpaceBoxArea from "../common/SpaceBoxArea";

const MySpace = () => {
  return (
    <div className="mySpace">
      <div className="mySpace-container">
        <h2>내 공간 보기</h2>
        <SpaceBoxArea
          onSelectSpace={() => {
            console.log("clicked");
          }}
        />
      </div>
    </div>
  );
};

export default MySpace;
