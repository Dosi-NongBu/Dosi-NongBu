import React, { useState, useEffect } from "react";
import { AiOutlineBell } from "react-icons/ai";
import { GiWateringCan } from "react-icons/gi";
import { PiPottedPlantDuotone } from "react-icons/pi";

import "./style/MyCropDetailContainer.css";

import { mockData5 } from "../../util/api";
const MyCropDetailContainer = () => {
  const [cropData, setCropData] = useState({});
  useEffect(() => {
    setCropData(mockData5());
  }, []);

  return (
    <div className="myCropDetailContainer">
      <div className="myCropDetailBox">
        <div className="myCrop-header">
          <h3>상추 일지</h3>
          <h3>
            알림 설정 <AiOutlineBell />
          </h3>
        </div>

        <div className="myCrop-info">
          {cropData && (
            <>
              <div className="myCrop-info-image"></div>
              <div className="myCrop-info-info">
                <div className="myCrop-info-eachInfo">
                  <span>물 주기</span>
                  <span>5일</span>
                </div>
                <div className="myCrop-info-eachInfo">
                  <span>다음 물 주기</span>
                  <span>내일</span>
                </div>

                <div className="myCrop-info-eachInfo">
                  <span>적정 온도</span>
                  <span>15도</span>
                </div>
              </div>
            </>
          )}
        </div>

        <div className="myCrop-activity">
          <GiWateringCan size={50} />
          <PiPottedPlantDuotone size={50} />
          <GiWateringCan size={50} />
          <PiPottedPlantDuotone size={50} />
        </div>
      </div>
    </div>
  );
};

export default MyCropDetailContainer;
