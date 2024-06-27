import React from "react";
import { GiWateringCan } from "react-icons/gi";
import { PiPottedPlantDuotone } from "react-icons/pi";
import { FaWind } from "react-icons/fa6";
import { IoIosGitBranch } from "react-icons/io";

const MyCropActivity = ({ onAddTimeline }) => {
  return (
    <div>
      <div className="myCrop-elements myCrop-activity">
        <h3>오늘은 어떤 활동을 하셨나요?</h3>

        <div className="myCrop-activity-button">
          <div
            className="myCrop-activity-each"
            onClick={() => onAddTimeline("물주기")}
          >
            <GiWateringCan size={40} />
            <span>물 주기</span>
          </div>
          <div
            className="myCrop-activity-each"
            onClick={() => onAddTimeline("분갈이")}
          >
            <PiPottedPlantDuotone size={40} />
            <span>분갈이</span>
          </div>
          <div
            className="myCrop-activity-each"
            onClick={() => onAddTimeline("환기하기")}
          >
            <FaWind size={40} />
            <span>환기하기</span>
          </div>
          <div
            className="myCrop-activity-each"
            onClick={() => onAddTimeline("가지치기")}
          >
            <IoIosGitBranch size={40} />
            <span>가지치기</span>
          </div>
        </div>
      </div>
    </div>
  );
};

export default MyCropActivity;
