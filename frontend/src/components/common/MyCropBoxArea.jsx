import React from "react";
import { useNavigate } from "react-router-dom";

import "./style/MyCropBoxArea.css";

const MyCropBox = ({ nickname, image, id }) => {
  const navigate = useNavigate();

  const handleClickCrop = () => {
    navigate("/myCrop/" + id);
  };

  return (
    <div className="myCropBox" onClick={handleClickCrop}>
      <div className="myCropBox-container">
        <img src={image} className="image" />
        <p className="name">{nickname}</p>
      </div>
    </div>
  );
};

const MyCropBoxArea = ({ data }) => {
  return (
    <div className="myCropBoxArea-wrapper">
      <div className="myCropBoxArea">
        {data.map(({ nickname, imageUrl, id }) => (
          <MyCropBox key={id} nickname={nickname} image={imageUrl} id={id} />
        ))}
      </div>
    </div>
  );
};

export default MyCropBoxArea;
