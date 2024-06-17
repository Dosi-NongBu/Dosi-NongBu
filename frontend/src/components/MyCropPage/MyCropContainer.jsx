import React, { useEffect, useState } from "react";

import "./style/MyCropContainer.css";
import Banner from "../common/Banner";

import { getUserCropAll, mockData6 } from "../../util/api";
import CropBoxArea from "../common/CropBoxArea";

const MyCropContainer = () => {
  const [data, setData] = useState();

  useEffect(() => {
    const fetchData = async () => {
      const response = await getUserCropAll();
      setData(response);
    };
    fetchData();
  }, []);

  return (
    <div className="myCropContainer">
      <Banner title={"내 작물 관리"} subTitle={"내 작물을 관리해보세요."} />

      <div className="myCrop-wrapper">
        <div className="myCropArea">
          <h2> 내 작물 목록</h2>
          <CropBoxArea data={data} type={"MY"} />
        </div>
      </div>
    </div>
  );
};

export default MyCropContainer;
