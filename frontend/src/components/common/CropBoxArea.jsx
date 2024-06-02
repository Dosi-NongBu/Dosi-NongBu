import React from "react";
import { useNavigate } from "react-router-dom";
import "./style/CropBoxArea.css";

const CropBox = ({ name, image, id, type }) => {
  const navigate = useNavigate();

  const handleClickCrop = () => {
    navigate("/searchCrop/" + id);
  };

  return (
    <div className="CropBox" onClick={handleClickCrop}>
      <div className="cropBox-container">
        <img src={image} className="image" />
        <p className="name">{name}</p>
      </div>
    </div>
  );
};

const MyCropBox = ({ nickname, image, id }) => {
  const navigate = useNavigate();

  const handleClickCrop = () => {
    navigate("/myCrop/" + id);
  };

  return (
    <div className="CropBox" onClick={handleClickCrop}>
      <div className="cropBox-container">
        <img src={image} className="image" />
        <p className="name">{nickname}</p>
      </div>
    </div>
  );
};

const CropBoxArea = ({ data, type }) => {
  return (
    <div className="CropBoxArea-wrapper">
      <div className="CropBoxArea">
        {type === "CROP" &&
          data.map(({ name, imageUrl, id }) => (
            <CropBox key={id} name={name} image={imageUrl} id={id} />
          ))}
        {type === "MY" &&
          data.map(({ nickname, imageUrl, id }) => (
            <MyCropBox key={id} nickname={nickname} image={imageUrl} id={id} />
          ))}
      </div>
    </div>
  );
};

export default CropBoxArea;
