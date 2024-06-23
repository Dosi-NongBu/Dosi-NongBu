import React, { useState, useRef, useEffect } from "react";
import Button from "./Button";

import "./style/Gallery.css";

const Gallery = ({ type, setGalleryImages, readImages }) => {
  const fileInputRef = useRef(null);
  const [images, setImages] = useState([]);
  const [mainImage, setMainImage] = useState(null);

  // 읽기 전용인 경우 이미지 저장
  useEffect(() => {
    setImages(readImages || []);
  }, [readImages]);

  const handleFileChange = (event) => {
    const file = event.target.files[0];
    if (file && images.length < 5) {
      const newImage = {
        name: file.name,
        url: URL.createObjectURL(file),
      };
      const updatedImages = Array.isArray(images)
        ? [...images, newImage]
        : [newImage];
      setGalleryImages(updatedImages);
      setImages(updatedImages);
      if (!mainImage) {
        setMainImage(newImage);
      }
    }
  };

  const handleThumbnailClick = (image) => {
    setMainImage(image);
  };

  const handleDelete = (image) => {
    const newImages = images.filter((img) => img.url !== image.url);
    setImages(newImages);
    setGalleryImages(newImages);
    if (mainImage && mainImage.url === image.url) {
      setMainImage(newImages.length > 0 ? newImages[0] : null);
    }
  };

  const handleClick = () => {
    fileInputRef.current.click(); // 숨겨진 파일 입력 요소를 클릭
  };

  return (
    <div className="gallery">
      <div>
        {mainImage && (
          <div className="main-image">
            <img src={mainImage.url} alt={mainImage.name} />
            {type === "WRITE" && (
              <button
                className="delete-image"
                onClick={() => handleDelete(mainImage)}
              >
                X
              </button>
            )}
          </div>
        )}
      </div>
      <div className="thumbnail-container">
        {images &&
          images.map((image, index) => (
            <div className="each-thumbnail" key={index}>
              <img
                src={image.url}
                alt={image.name}
                onClick={() => handleThumbnailClick(image)}
              />
            </div>
          ))}
      </div>
      {type === "WRITE" && (
        <div className="file-input-container">
          <Button
            title={"사진 추가"}
            className="file-input-button"
            type={"negative"}
            onClick={handleClick}
          />
          <input
            type="file"
            className="file-input"
            ref={fileInputRef}
            onChange={handleFileChange}
          />
        </div>
      )}
    </div>
  );
};

export default Gallery;
