import React from "react";
import { useNavigate } from "react-router-dom";
import "./style/CropBox.css";

const CropBox = ({ name, image, id }) => {
  const navigate = useNavigate();

  const handleClickCrop = () => {
    navigate("/searchCrop/" + id);
  };

  return (
    <div className="cropbox" onClick={handleClickCrop}>
      <div className="cropbox-container">
        <img src={image} className="image" />
        <p className="name">{name}</p>
      </div>
    </div>
  );
};

export default CropBox;
