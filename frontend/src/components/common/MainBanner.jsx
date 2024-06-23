import React from "react";
import { FaLeaf } from "react-icons/fa";
import "./style/MainBanner.css";

const MainBanner = () => {
  return (
    <div className="main-banner">
      <div className="banner-content">
        <div className="banner-text">
          <h1 className="banner-title">
            베란다에서
            <br />
            손쉽게
            <br />
            작물을 키워보세요
          </h1>
        </div>
        <div className="banner-icon">
          <FaLeaf size={150} />
        </div>
      </div>
    </div>
  );
};

export default MainBanner;
