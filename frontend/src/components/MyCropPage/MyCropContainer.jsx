import React from "react";

import "./style/MyCropContainer.css";
import Banner from "../common/Banner";
import MyCropBoxArea from "../common/MyCropBoxArea";

import { mockData6 } from "../../util/api";

const MyCropContainer = () => {
  return (
    <div className="myCropContainer">
      <Banner title={"내 작물 관리"} subTitle={"내 작물을 관리해보세요."} />

      <div className="myCrop-wrapper">
        <div className="myCropArea">
          <h2> 내 작물 목록</h2>
          {<MyCropBoxArea data={mockData6()} />}
        </div>
      </div>
    </div>
  );
};

export default MyCropContainer;
