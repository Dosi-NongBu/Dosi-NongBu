import React from "react";
import CropBox from "./CropBox";

import "./style/CropBoxArea.css";

const CropBoxArea = ({ data }) => {
  return (
    <div className="cropBoxArea">
      {data.map(({ name, cropsImage, cropId }) => (
        <CropBox key={cropId} name={name} image={cropsImage} id={cropId} />
      ))}
    </div>
  );
};

export default CropBoxArea;
