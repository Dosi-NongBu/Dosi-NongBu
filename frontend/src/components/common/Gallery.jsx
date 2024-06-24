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
    console.log("read images ", readImages);
  }, [readImages]);

  const handleFileChange = (event) => {
    const file = event.target.files[0];

    if (file && images.length < 5) {
      const newImageName = file.name;
      setGalleryImages(newImageName);
      if (!mainImage) {
        setMainImage(newImageName);
      }
    }
  };

  const handleThumbnailClick = (image) => {
    setMainImage(image);
  };

  const handleDelete = (image) => {
    // const newImages = images.filter((img) => img.url !== image.url);
    const newImages = images.filter((img) => img !== image);
    console.log(newImages, "is now");
    setImages(newImages);
    setGalleryImages(newImages);
    if (mainImage && mainImage.url === image.url) {
      setMainImage(newImages.length > 0 ? newImages[0] : null);
    }
  };

  const handleClick = () => {
    fileInputRef.current.click();
  };

  if (type === "READ" && !readImages) {
    return <div>Loading...</div>;
  }

  return (
    <div className="gallery">
      {mainImage && (
        <div className="main-image">
          <img src={`/${mainImage}`} />
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

      {/* {type === "READ" && mainImage && (
        <div className="main-image">
          <img src={`/${mainImage}`} />
          {type === "WRITE" && (
            <button
              className="delete-image"
              onClick={() => handleDelete(mainImage)}
            >
              X
            </button>
          )}
        </div>
      )} */}
      <div className="thumbnail-container">
        {images &&
          images.map((image, index) => (
            <div className="each-thumbnail" key={index}>
              <img
                src={`/${image}`}
                onClick={() => handleThumbnailClick(image)}
              />
            </div>
          ))}

        {/* {type === "READ" &&
          readImages &&
          readImages.map((image, index) => (
            <div className="each-thumbnail" key={index}>
              <img
                src={`/${image}`}
                onClick={() => handleThumbnailClick(image)}
              />
              <h2>{image}</h2>
            </div>
          ))} */}
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
