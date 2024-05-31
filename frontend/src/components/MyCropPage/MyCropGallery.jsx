import React, { useState } from "react";

import ImageGallery from "react-image-gallery";

import "./style/MyCropGallery.css";
import Button from "../common/Button";

const MyCropGallery = ({ cropData, onAddImage, onDeleteImage }) => {
  const customItem = (item) => {
    return (
      <div className="image-gallery-image">
        <img src={item.thumbnail} alt="" />
        <button
          className="delete-button"
          onClick={() => onDeleteImage(item.id)}
        >
          X
        </button>
      </div>
    );
  };

  return (
    <div>
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
              onChange={onAddImage}
              className="crop-file-input"
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
  );
};

export default MyCropGallery;
