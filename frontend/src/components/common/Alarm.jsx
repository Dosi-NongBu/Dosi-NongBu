import { getCropNotification, putCropNotification } from "../../util/api";
import Modal from "react-modal";
import ReactModal from "react-modal";
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
          checked={isActive ? isActive : true}
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
      <h3>
        {title} {isActive}{" "}
      </h3>
      <input
        type="number"
        value={value ? value : 0}
        onChange={onChangeValue}
        disabled={!isActive}
      />
      <span style={{ color: !isActive ? "gray" : "black", fontSize: "0.8em" }}>
        {"일에 한 번"} <span>{` ( 최대 ${max}일 )`}</span>
      </span>

      <Toggle isActive={isActive} onChangeToggle={onChangeToggle} />
    </div>
  );
};

const Alarm = ({ userCropId, isOpen, onSubmit }) => {
  const [alarms, setAlarms] = useState([
    { id: 1, title: "물 주기", max: 360, isActive: true, value: 0 },
    { id: 2, title: "분갈이", max: 720, isActive: true, value: 0 },
    { id: 3, title: "환기", max: 360, isActive: true, value: 0 },
    { id: 4, title: "가지치기", max: 360, isActive: true, value: 0 },
  ]);
  const [isModalOpen, setIsModalOpen] = useState();

  useEffect(() => {
    setIsModalOpen(isOpen);
  }, [isOpen]);

  // 커스텀 스타일 정의
  const customModalStyles = {
    overlay: {
      backgroundColor: "rgba(0, 0, 0, 0.4)",
      width: "100%",
      height: "100vh",
      zIndex: 10,
      position: "fixed",
      top: "0",
      left: "0",
    },
    content: {
      width: "40%",
      height: "80%",
      zIndex: 150,
      position: "absolute",
      top: "50%",
      left: "50%",
      transform: "translate(-50%, -50%)",
      borderRadius: "10px",
      boxShadow: "2px 2px 2px rgba(0, 0, 0, 0.25)",
      backgroundColor: "white",
      justifyContent: "center",
      overflow: "auto",
    },
  };

  //알림 조회
  useEffect(() => {
    const fetchData = async () => {
      const data = await getCropNotification(Number(userCropId));

      const updatedAlarms = alarms.map((alarm) => {
        switch (alarm.title) {
          case "물 주기":
            return {
              ...alarm,
              isActive: data.isWaterAlarm,
              value: data.water || 0,
            };
          case "분갈이":
            return {
              ...alarm,
              isActive: data.isRepotAlarm,
              value: data.repot || 0,
            };
          case "환기":
            return {
              ...alarm,
              isActive: data.isVentilationAlarm,
              value: data.ventilation || 0,
            };
          case "가지치기":
            return {
              ...alarm,
              isActive: data.isPruningAlarm,
              value: data.pruning || 0,
            };
          default:
            return alarm;
        }
      });

      setAlarms(updatedAlarms);
    };
    fetchData();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  // 토글 변경
  const handleChangeToggle = (id) => {
    const newAlarms = alarms.map((alarm) =>
      alarm.id === id ? { ...alarm, isActive: !alarm.isActive } : alarm
    );
    setAlarms(newAlarms);
  };

  // 값 변경
  const handleChangeValue = (id, newValue) => {
    const newAlarms = alarms.map((alarm) =>
      alarm.id === id ? { ...alarm, value: newValue } : alarm
    );
    setAlarms(newAlarms);
  };

  // 알림 저장
  const handleSubmitAlarm = () => {
    const ret = {};

    alarms.forEach((alarm) => {
      switch (alarm.title) {
        case "물 주기":
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
    console.log("새 알람 = ", ret);

    const sendData = async () => {
      const response = await putCropNotification(Number(userCropId), ret);
    };
    sendData();

    setIsModalOpen(false);
    onSubmit();
  };

  return (
    <Modal
      isOpen={isModalOpen}
      onRequestClose={() => setIsModalOpen(false)}
      style={customModalStyles}
    >
      <div className="alarm">
        <h2>알림 수정</h2>
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
    </Modal>
  );
};
export default Alarm;
