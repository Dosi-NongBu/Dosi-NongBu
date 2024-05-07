import React, { useState, useRef, Fragment, useEffect } from "react";

import "./style/Progress.css";

const Progress = ({ curStep }) => {
  const [step, setStep] = useState([
    { order: 1, name: "작물 선택", done: true },
    { order: 2, name: "기본 설정", done: false },
    // { order: 3, name: "알림 설정", done: false },
    { order: 3, name: "완료", done: false },
  ]);

  useEffect(() => {
    setStep((step) =>
      step.map((item) => ({
        ...item,
        done: item.order <= curStep,
      }))
    );
  }, [curStep]);

  return (
    <div className="progress">
      <div className="circle-bar-container">
        {step.map((item, index) => (
          <Fragment key={index}>
            {index !== 0 && (
              <div className={`bar ${item.done ? "bar-active" : ""}`}></div>
            )}
            <div
              className={`circle ${item.done ? "circle-active" : ""} ${
                item.order === curStep ? "circle-on" : ""
              }`}
            ></div>
          </Fragment>
        ))}
      </div>
      <div className="step-name-container">
        {step.map((item, index) => (
          <Fragment key={index}>
            <div className={`each-name ${item.done ? `name-active` : ""}`}>
              {item.name}
            </div>
          </Fragment>
        ))}
      </div>
    </div>
  );
};

export default Progress;
