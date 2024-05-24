import React, { useState, useEffect } from "react";
import { AiOutlineBell } from "react-icons/ai";
import { GiWateringCan } from "react-icons/gi";
import { PiPottedPlantDuotone } from "react-icons/pi";
import { FaWind } from "react-icons/fa6";
import { IoIosGitBranch } from "react-icons/io";
import ImageGallery from "react-image-gallery";
import Modal from "react-modal";
import "react-image-gallery/styles/css/image-gallery.css";
import "./style/MyCropDetailContainer.css";

import {
  deleteUserTimeline,
  getUserCropDetail,
  getUserTimeline,
  mockData5,
  mockSend1,
  postUserCropImage,
  postUserTimeline,
} from "../../util/api";
import Button from "../common/Button";
import Alarm from "../common/Alarm";

const MyCropDetailContainer = ({ userCropId }) => {
  const [cropData, setCropData] = useState(mockData5());
  const [isModal, setIsModal] = useState(false);
  const [timeline, setTimeline] = useState([
    { cropLogId: 1, managedDate: "2024-05-12", cropManageType: "분갈이" },
    { cropLogId: 2, managedDate: "2024-05-14", cropManageType: "물 주기" },
    { cropLogId: 3, managedDate: "2024-05-26", cropManageType: "물 주기" },
    { cropLogId: 4, managedDate: "2024-05-30", cropManageType: "가지치기" },
  ]);

  useEffect(() => {
    // 변경된 이미지 전송하는 API 코드
    mockSend1(cropData.imageUrls);
  }, [cropData]);

  const getTimeline = async () => {
    const data = await getUserTimeline(userCropId, 1, 5);
    setTimeline(data);
  };

  useEffect(() => {
    // const getCropData = async () => {
    //   const data = await getUserCropDetail(userCropId);
    //   setCropData(data);
    // };
    const getTimeline = async () => {
      const data = await getUserTimeline(userCropId, 1, 5);
      setTimeline(data);
    };

    // getCropData(); // 기본 정보 get
    getTimeline(); // 타임라인 get
  }, [userCropId]);

  // 활동을 timeline에 추가하는 함수
  const handleAddTimeline = async (activity) => {
    // 새로운 작물 관리 추가
    await postUserTimeline(userCropId, activity);

    // 타임라인
    getTimeline();
  };

  // 활동 timeline에서 삭제하는 함수
  const handleDeleteTimeline = async (removeIndex) => {
    // 활동 삭제
    await deleteUserTimeline(userCropId, removeIndex);

    // 타임라인
    getTimeline();
  };

  /*
  갤러리
   */

  const customItem = (item) => {
    return (
      <div className="image-gallery-image">
        <img src={item.thumbnail} alt="" />
        <button
          className="delete-button"
          onClick={() => handleDeleteImage(item.id)}
        >
          X
        </button>
      </div>
    );
  };

  const handleDeleteImage = async (itemId) => {
    alert("사진을 삭제하시겠습니까?");

    const filteredData = cropData.imageUrls.filter(
      (image) => image.id !== itemId
    );
    setCropData({ ...cropData, imageUrls: filteredData });

    await postUserCropImage(userCropId, cropData.imageUrls);
  };

  const handleAddImage = async (event) => {
    const file = event.target.files[0];
    if (file) {
      const filePath = URL.createObjectURL(file);
      const newImage = {
        id: cropData.imageUrls.length + 1,
        original: filePath,
        thumbnail: filePath,
      };
      const newImages = [...cropData.imageUrls, newImage];
      setCropData({ ...cropData, imageUrls: newImages });
    }

    await postUserCropImage(userCropId, cropData.imageUrls);
  };

  return (
    <div className="myCropDetailContainer">
      <div className="modal-area">
        <Modal
          isOpen={isModal}
          onRequestClose={() => {
            setIsModal(false);
          }}
        >
          <Alarm
            onSubmitButton={() => {
              console.log("submitted", userCropId);
            }}
            userCropId={userCropId}
          />
        </Modal>
      </div>

      <div className="myCropDetailBox">
        {cropData && (
          <>
            <div className="myCrop-elements myCrop-header">
              <h1>{cropData.nickname} 일지</h1>
              <div className="myCrop-header-noti">
                <span className="flex-row">
                  알림설정
                  <AiOutlineBell
                    size={30}
                    onClick={() => {
                      setIsModal(true);
                    }}
                  />
                </span>
              </div>
            </div>

            <div className="myCrop-elements myCrop-info">
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
          </>
        )}
        <div className="myCrop-elements myCrop-activity">
          <h3>오늘은 어떤 활동을 하셨나요?</h3>

          <div className="myCrop-activity-button">
            <div
              className="myCrop-activity-each"
              onClick={() => handleAddTimeline("물 주기")}
            >
              <GiWateringCan size={40} />
              <span>물 주기</span>
            </div>
            <div
              className="myCrop-activity-each"
              onClick={() => handleAddTimeline("분갈이")}
            >
              <PiPottedPlantDuotone size={40} />
              <span>분갈이</span>
            </div>
            <div
              className="myCrop-activity-each"
              onClick={() => handleAddTimeline("환기하기")}
            >
              <FaWind size={40} />
              <span>환기하기</span>
            </div>
            <div
              className="myCrop-activity-each"
              onClick={() => handleAddTimeline("가지치기")}
            >
              <IoIosGitBranch size={40} />
              <span>가지치기</span>
            </div>
          </div>
        </div>

        <div className="myCrop-elements myCrop-timeline">
          <h2>타임라인</h2>
          <h3>2024</h3>

          {timeline &&
            timeline.map((item, index) => (
              <div key={item.cropLogId} className="myCrop-timeline-each">
                <div className="myCrop-timeline-each-date">
                  {item.managedDate}
                </div>
                <div className="myCrop-timeline-each-activity">
                  {item.cropManageType}
                </div>
                <button
                  onClick={() => {
                    handleDeleteTimeline(item.cropLogId);
                  }}
                >
                  X
                </button>
              </div>
            ))}
        </div>

        <div className="myCrop-elements myCrop-gallery">
          <div className="flex-row tb-margin-20">
            <h2 className="no-margin">갤러리</h2>

            <label
              htmlFor="fileInput"
              style={{
                position: "relative",
                overflow: "hidden",
                display: "inline-block",
              }}
            >
              <Button title="사진 추가" type="positive" />
              <input
                type="file"
                id="fileInput"
                onChange={handleAddImage}
                className="file-input"
              />
            </label>
          </div>

          {cropData && cropData.imageUrls.length === 0 && (
            <>
              <h3>이미지가 없습니다. 이미지를 추가해주세요</h3>
            </>
          )}
          {cropData && cropData.imageUrls.length > 0 && (
            <>
              <ImageGallery
                items={cropData.imageUrls}
                autoPlay={false}
                renderItem={customItem}
              />
            </>
          )}
        </div>
      </div>
    </div>
  );
};

export default MyCropDetailContainer;
