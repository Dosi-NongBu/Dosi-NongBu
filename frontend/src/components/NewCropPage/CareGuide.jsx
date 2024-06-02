import React, { useEffect, useState } from "react";

import { FaCheck } from "react-icons/fa";

import "./style/CareGuide.css";
import { stringToNumberArray } from "../../util/stringToArray";

const EachGuide = ({ title, content, tip }) => {
  return (
    <div className="eachGuide">
      <div className="eachGuide-title">
        <FaCheck size={30} />
        <h3>{title}</h3>
      </div>
      <div className={`eachGuide-content ${tip}`}>
        {Array.isArray(content) && (
          <div className="month-container">
            {content.map((item, index) => (
              <div key={index} className="month">
                {item}월
              </div>
            ))}
          </div>
        )}
        {!Array.isArray(content) && <div>{content}</div>}
      </div>
    </div>
  );
};

const CareGuide = ({ data }) => {
  return (
    <div className="careGuide">
      {/* <EachGuide title="재배일정" content={stringToNumberArray(data.plant)} /> */}
      <EachGuide title="재배일정" content={data.month} />
      <EachGuide title="심는법" content={data.planting} />
      <EachGuide title="병해충" content={data.pest} />
      <EachGuide title="재배팁" content={data.tip} tip={"tip"} />
      <EachGuide title="수확 후 관리방법" content={data.harvest_manage} />
    </div>
  );
};

export default CareGuide;
