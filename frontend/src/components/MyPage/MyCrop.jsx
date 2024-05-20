import React, { useEffect, useState } from "react";

import "./style/MyCrop.css";
import MyCropBoxArea from "../common/MyCropBoxArea";
import { mockData } from "../../util/api";

const MyCrop = () => {
  const [myCrop, setMyCrop] = useState();
  useEffect(() => {
    const response = mockData();
    console.log("data = ", response);
    setMyCrop(response);
  }, []);

  return (
    <div className="myCrop">
      <div className="myCrop-container">
        <h2> 내 작물 모아보기</h2>
        {myCrop && <MyCropBoxArea data={myCrop} />}
      </div>
    </div>
  );
};

export default MyCrop;
