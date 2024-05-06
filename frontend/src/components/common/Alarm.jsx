import Button from "./Button";
import "./style/Alarm.css";

import React, { useState } from "react";

const Toggle = ({ isActive, onChangeToggle }) => {
  return (
    <div className="toggle">
      <label>
        <input
          role="switch"
          type="checkbox"
          checked={isActive}
          onChange={onChangeToggle}
        />
      </label>
    </div>
  );
};

const EachAlarm = ({
  title,
  max,
  isActive,
  value,
  onChangeToggle,
  onChangeValue,
}) => {
  return (
    <div className="each-alarm">
      <h3>{title}</h3>
      <input
        type="number"
        value={value}
        onChange={onChangeValue}
        disabled={!isActive}
      />
      <span style={{ color: !isActive ? "gray" : "black" }}>
        {"일에 한 번"} <span>{` ( 최대 ${max}일 )`}</span>
      </span>

      <Toggle isActive={isActive} onChangeToggle={onChangeToggle} />
    </div>
  );
};

const Alarm = ({ onSubmitButton }) => {
  const [alarms, setAlarms] = useState([
    { id: 1, title: "물갈이", max: 360, isActive: true, value: 0 },
    { id: 2, title: "분갈이", max: 720, isActive: true, value: 0 },
    { id: 3, title: "환기", max: 360, isActive: true, value: 0 },
    { id: 4, title: "가지치기", max: 360, isActive: true, value: 0 },
  ]);

  const handleChangeToggle = (id) => {
    const newAlarms = alarms.map((alarm) =>
      alarm.id === id ? { ...alarm, isActive: !alarm.isActive } : alarm
    );
    setAlarms(newAlarms);
  };

  const handleChangeValue = (id, newValue) => {
    const newAlarms = alarms.map((alarm) =>
      alarm.id === id ? { ...alarm, value: newValue } : alarm
    );
    setAlarms(newAlarms);
  };

  return (
    <div className="alarm">
      {alarms.map((item) => (
        <EachAlarm
          key={item.id}
          title={item.title}
          max={item.max}
          isActive={item.isActive}
          value={item.value}
          onChangeToggle={() => {
            handleChangeToggle(item.id);
          }}
          onChangeValue={(e) => {
            handleChangeValue(item.id, e.target.value);
          }}
        />
      ))}

      <div className="alarm-button-container">
        <Button
          title={"등록하기"}
          type={"positive"}
          onClick={() => {
            onSubmitButton(alarms);
          }}
        />
      </div>
    </div>
  );
};
export default Alarm;
