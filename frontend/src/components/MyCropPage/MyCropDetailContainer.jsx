import React, { useState, useEffect } from "react";
import { AiOutlineBell } from "react-icons/ai";
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
import Alarm from "../common/Alarm";
import MyCropInfo from "./MyCropInfo";
import MyCropActivity from "./MyCropActivity";
import MyCropTimeline from "./MyCropTimeline";
import MyCropGallery from "./MyCropGallery";

const MyCropDetailContainer = ({ userCropId }) => {
  const [cropData, setCropData] = useState(mockData5());
  const [isModal, setIsModal] = useState(false);
  const [timeline, setTimeline] = useState([
    { cropLogId: 1, managedDate: "2024-05-12", cropManageType: "분갈이" },
    { cropLogId: 2, managedDate: "2024-05-14", cropManageType: "물 주기" },
    { cropLogId: 3, managedDate: "2024-05-26", cropManageType: "물 주기" },
    { cropLogId: 4, managedDate: "2024-05-30", cropManageType: "가지치기" },
  ]);

  // 타임라인
  async function fetchTimeline() {
    const data = await getUserTimeline(Number(userCropId), 0, 5);
    setTimeline(data);
  }

  // 기본 정보 업데이트
  const fetchData = async () => {
    const data = await getUserCropDetail(Number(userCropId));
    setCropData(data);
  };

  // 로딩 시
  useEffect(() => {
    // fetchTimeline();
    // fetchData();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  // 활동을 timeline에 추가하는 함수
  const handleAddTimeline = async (activity) => {
    const newAc = {
      cropLogId: timeline.length + 1,
      managedDate: new Date().toISOString().slice(0, 10),
      cropManageType: activity,
    };
    // setTimeline([newAc, ...timeline]);

    // 새로운 작물 관리 추가
    await postUserTimeline(userCropId, activity);

    // 타임라인
    fetchTimeline();
  };

  // 활동 timeline에서 삭제하는 함수
  const handleDeleteTimeline = async (removeIndex) => {
    // 활동 삭제
    await deleteUserTimeline(userCropId, removeIndex);
    // 타임라인
    fetchTimeline();
  };

  // 사진 추가
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
      console.log("전송 이미지 = ", newImages);
      setCropData({ ...cropData, imageUrls: newImages });

      await postUserCropImage(Number(userCropId), newImages);
    }
  };

  const handleDeleteImage = async (itemId) => {
    alert("사진을 삭제하시겠습니까?");

    const filteredData = cropData.imageUrls.filter(
      (image) => image.id !== itemId
    );
    setCropData({ ...cropData, imageUrls: filteredData });

    await postUserCropImage(userCropId, cropData.imageUrls);
  };

  return (
    <div className="myCropDetailContainer">
      <div className="modal-area">
        <Alarm
          userCropId={userCropId}
          isOpen={isModal}
          onSubmit={() => {
            setIsModal(false);
          }}
        />
      </div>

      <div className="myCropDetailBox">
        {cropData && (
          <>
            <div className="myCrop-header">
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
            <MyCropInfo cropData={cropData} />

            <MyCropActivity onAddTimeline={handleAddTimeline} />
          </>
        )}

        <MyCropTimeline
          timeline={timeline}
          onDeleteTimeline={handleDeleteTimeline}
        />

        <MyCropGallery
          cropData={cropData}
          onAddImage={handleAddImage}
          onDeleteImage={handleDeleteImage}
        />
      </div>
    </div>
  );
};

export default MyCropDetailContainer;
