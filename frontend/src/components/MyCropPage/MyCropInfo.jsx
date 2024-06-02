import React, { useState } from "react";
import "./style/MyCropInfo.css";

const MyCropInfo = ({ cropData }) => {
  return (
    <div>
      <div className="myCrop-elements myCrop-elements-gray myCrop-info">
        <img src="../../../public/picture2.png" />

        <div className="myCrop-info-info">
          <div className="myCrop-info-eachInfo">
            <span>물 주기</span>
            <span>{cropData.period}일에 한 번 </span>
          </div>
          <div className="myCrop-info-eachInfo">
            <span>다음 물 주기</span>
            <span>{cropData.prePeriod}일 후 </span>
          </div>

          <div className="myCrop-info-eachInfo">
            <span>적정 온도</span>
            <span>
              {cropData.minTemperature} ~ {cropData.maxTemperature}도
            </span>
          </div>

          <div className="myCrop-info-eachInfo">
            <span>적정 습도</span>
            <span>{cropData.humidity} 도 </span>
          </div>
        </div>
      </div>
    </div>
  );
};

export default MyCropInfo;
