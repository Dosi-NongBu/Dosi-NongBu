import { getCropNotification, putCropNotification } from "../../util/api";
import Button from "./Button";
import "./style/Alarm.css";

import React, { useEffect, useState } from "react";

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

const Alarm = ({ userCropId }) => {
  const [alarms, setAlarms] = useState([
    { id: 1, title: "물갈이", max: 360, isActive: true, value: 0 },
    { id: 2, title: "분갈이", max: 720, isActive: true, value: 0 },
    { id: 3, title: "환기", max: 360, isActive: true, value: 0 },
    { id: 4, title: "가지치기", max: 360, isActive: true, value: 0 },
  ]);

  // 알림 조회
  // useEffect(()=>{
  //   const fetchData = async()=>{
  //     const data=await getCropNotification(Number(userCropId));

  //     const updatedAlarms = alarms.map((alarm) => {
  //       switch (alarm.title) {
  //         case "물갈이":
  //           return { ...alarm, isActive: data.isWaterAlarm, value: data.water };
  //         case "분갈이":
  //           return { ...alarm, isActive: data.isRepotAlarm, value: data.repot };
  //         case "환기":
  //           return { ...alarm, isActive: data.isVentilationAlarm, value: data.ventilation };
  //         case "가지치기":
  //           return { ...alarm, isActive: data.isPruningAlarm, value: data.pruning };
  //         default:
  //           return alarm;
  //       }
  //     });

  //     setAlarms(updatedAlarms);
  //   }
  //   fetchData();
  // },[]);

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

  const handleSubmitAlarm = () => {
    console.log("new alarm - ", alarms);
    const ret = {};

    alarms.forEach((alarm) => {
      switch (alarm.title) {
        case "물갈이":
          ret.isWaterAlarm = alarm.isActive;
          ret.water = Number(alarm.value);
          break;
        case "분갈이":
          ret.isRepotAlarm = alarm.isActive;
          ret.repot = Number(alarm.value);
          break;
        case "환기":
          ret.isVentilationAlarm = alarm.isActive;
          ret.ventilation = Number(alarm.value);
          break;
        case "가지치기":
          ret.isPruningAlarm = alarm.isActive;
          ret.pruning = Number(alarm.value);
          break;
      }
    });
    console.log(ret);
    console.log("id~~ = ", userCropId);

    const fetchData = async () => {
      const response = await putCropNotification(Number(userCropId), ret);
    };
    fetchData();
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
          onClick={handleSubmitAlarm}
        />
      </div>
    </div>
  );
};
export default Alarm;
