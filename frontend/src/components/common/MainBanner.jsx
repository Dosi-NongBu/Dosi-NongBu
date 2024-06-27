import React from "react";
import Lottie from "lottie-react";
import Plant from "../../assets/lottie/Plant.json";

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
          <Lottie
            animationData={Plant}
            loop={false}
            style={{ width: "300px", height: "300px" }}
          />
        </div>
      </div>
    </div>
  );
};

export default MainBanner;
