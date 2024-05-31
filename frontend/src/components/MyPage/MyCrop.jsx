import React, { useEffect, useState } from "react";

import "./style/MyCrop.css";
import { getUserCropAll, mockData6 } from "../../util/api";
import CropBoxArea from "../common/CropBoxArea";

const MyCrop = () => {
  const [myCrop, setMyCrop] = useState();
  useEffect(() => {
    const fetchData = async () => {
      const data = await getUserCropAll();
      setMyCrop(data);
    };

    fetchData();
  }, []);

  return (
    <div className="myCrop">
      <div className="myCrop-container">
        <h2> 내 작물 모아보기</h2>
        {!myCrop && <h3>키우는 작물이 없습니다.</h3>}
        {myCrop && <CropBoxArea data={myCrop} type="MY" />}
      </div>
    </div>
  );
};

export default MyCrop;
