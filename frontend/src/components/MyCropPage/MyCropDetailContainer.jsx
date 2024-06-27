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
import Gallery from "../common/Gallery";

const MyCropDetailContainer = ({ userCropId }) => {
  const [cropData, setCropData] = useState(mockData5());
  const [isModal, setIsModal] = useState(false);
  const [timeline, setTimeline] = useState([]);
  const [nowPage, setNowPage] = useState(0);

  // 타임라인
  async function fetchTimeline(page) {
    setNowPage(page);
    const data = await getUserTimeline(Number(userCropId), page, 5);
    setTimeline(data);
  }

  // 기본 정보 업데이트
  const fetchData = async () => {
    const data = await getUserCropDetail(Number(userCropId));
    setCropData(data);
  };

  // 로딩 시
  useEffect(() => {
    fetchTimeline(nowPage); // 주석처리 시 타임라인 임시 데이터
    fetchData();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  // 활동을 timeline에 추가하는 함수
  const handleAddTimeline = async (activity) => {
    // 새로운 작물 관리 추가
    await postUserTimeline(userCropId, activity);

    // 타임라인
    fetchTimeline(nowPage);
  };

  // 활동 timeline에서 삭제하는 함수
  const handleDeleteTimeline = async (removeIndex) => {
    // 활동 삭제
    await deleteUserTimeline(userCropId, removeIndex);
    // 타임라인
    fetchTimeline();
  };

  // // 사진 추가
  // const handleAddImage = async (event) => {
  //   const file = event.target.files[0];
  //   if (file) {
  //     const filePath = URL.createObjectURL(file);
  //     const newImage = {
  //       id: cropData.imageUrls.length + 1,
  //       original: file.name,
  //       thumbnail: file.name,
  //     };

  //     const newImages = [...cropData.imageUrls, newImage];
  //     setCropData({ ...cropData, imageUrls: newImages });

  //     await postUserCropImage(Number(userCropId), newImages);
  //   }
  // };

  // 여기서 새 배열 생성
  const handleAddImage = async (event) => {
    if (event) {
      const newImages = [...cropData.imageUrls, event];
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
          onMovePage={fetchTimeline}
          onDeleteTimeline={handleDeleteTimeline}
        />

        {/* <MyCropGallery
          cropData={cropData}
          onAddImage={handleAddImage}
          onDeleteImage={handleDeleteImage}
        /> */}

        {/* <div className="myCrop-elements myCrop-gallery">
          <div className="flex-row tb-margin-20">
            <h2 className="no-margin">갤러리</h2>

            <Gallery
              type="WRITE"
              setGalleryImages={handleAddImage}
              readImages={cropData.imageUrls}
            />
          </div>
        </div> */}

        <div className="myCrop-elements myCrop-gallery">
          <div className="flex-row tb-margin-20">
            <h2 className="no-margin">갤러리</h2>
          </div>

          {cropData && cropData.imageUrls.length === 0 && (
            <>
              <h3>이미지가 없습니다. 이미지를 추가해주세요</h3>
            </>
          )}

          <Gallery
            type="WRITE"
            setGalleryImages={handleAddImage}
            readImages={cropData.imageUrls}
          />
        </div>
      </div>
    </div>
  );
};

export default MyCropDetailContainer;
