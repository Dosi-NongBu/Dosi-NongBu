import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

import Lottie from "lottie-react";
import Success from "../../assets/lottie/Success.json";
import "./style/InMyGarden.css";
import Button from "../common/Button";
import Alarm from "../common/Alarm";
import Progress from "../common/Progress";
import SpaceBoxArea from "../common/SpaceBoxArea";

import { postMyCrop } from "../../util/api";

const NewSpace = ({ cropId }) => {
  const nav = useNavigate();
  const [curStep, setCurStep] = useState(2); // 현재 단계
  const [cropInfo, setCropInfo] = useState({ cropId: cropId });
  const handleFinishSpaceButton = () => {
    if (!cropInfo["userPlaceId"]) {
      alert("공간을 선택해주세요.");
      return;
    }
    setCurStep(curStep + 1);
  };

  // 닉네임 입력
  const handleNickName = (e) => {
    setCropInfo({ ...cropInfo, nickname: e.target.value });
  };

  // 선택한 공간 번호
  const handleSelectSpace = (userSpaceId) => {
    setCropInfo({ ...cropInfo, userPlaceId: userSpaceId });
  };

  // 최종 제출 버튼
  const handleSubmitButton = async (alarms) => {
    setCurStep(curStep + 1);
    setCropInfo({ ...cropInfo, alarms });
    // api 호출해서 전송

    const response = await postMyCrop(cropInfo, Number(cropId));
  };

  // useEffect(() => {
  //   console.log("changed , ", cropInfo);
  // }, [cropInfo]);

  return (
    <div className="newSpace">
      <Progress curStep={curStep} />
      {curStep === 2 && (
        <>
          <h3> 닉네임을 지어주세요. </h3>
          <input
            type="text"
            className="crop-nickname"
            onChange={handleNickName}
          />
          <h3>작물을 키울 공간을 선택해주세요.</h3>
          <SpaceBoxArea onSelectSpace={handleSelectSpace} />
        </>
      )}{" "}
      {cropInfo["userPlaceId"] && curStep === 2 && (
        <Button title="다음으로" onClick={handleFinishSpaceButton} />
      )}
      {curStep === 3 && (
        <>
          <h2>알림설정</h2>
          <h3>알림을 설정하고 식물 관리를 놓치지 마세요!</h3>

          <Alarm onSubmitButton={handleSubmitButton} />
        </>
      )}
      {curStep === 4 && (
        <>
          <h2>작물 등록을 완료하였습니다!</h2>
          <Lottie animationData={Success} />
          <Button
            title={"내 텃밭 바로가기"}
            type={"positive"}
            onClick={() => {
              nav("/myCrop");
            }}
          />
        </>
      )}
    </div>
  );
};

export default NewSpace;
