import React from "react";
import { useNavigate } from "react-router-dom";

import "./style/MyCropBoxArea.css";

const MyCropBox = ({ name, image, id }) => {
  const navigate = useNavigate();

  const handleClickCrop = () => {
    navigate("/myCrop/" + id);
  };

  return (
    <div className="myCropBox" onClick={handleClickCrop}>
      <div className="myCropBox-container">
        <img src={image} className="image" />
        <p className="name">{name}</p>
      </div>
    </div>
  );
};

const MyCropBoxArea = ({ data }) => {
  return (
    <div className="myCropBoxArea-wrapper">
      <div className="myCropBoxArea">
        {data.map(({ name, cropsImage, cropId }) => (
          <MyCropBox key={cropId} name={name} image={cropsImage} id={cropId} />
        ))}
      </div>
    </div>
  );
};

export default MyCropBoxArea;
