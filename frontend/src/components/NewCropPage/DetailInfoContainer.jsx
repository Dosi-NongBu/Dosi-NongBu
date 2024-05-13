import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

import Button from "../common/Button";
import "./style/DetailInfoContainer.css";
import CareGuide from "./CareGuide";
import { getCropDetailInfo, mockData3 } from "../../util/api";
import BasicInfo from "./BasicInfo";
import InMyGarden from "./InMyGarden";

const DetailInfoContainer = () => {
  const { cropId } = useParams();
  const [tab, setTab] = useState("");
  const [data, setData] = useState({});

  const handleButtonClick = (tabName) => {
    setTab(tabName);
  };

  useEffect(() => {
    const fetchData = async () => {
      const response = mockData3();
      // const response = await getCropDetailInfo(Number(cropId));
      if (response) {
        setData(response);
      }
    };
    fetchData();
  }, [cropId]);

  return (
    <section className="detailInfoContainer">
      <div className="button-content-container">
        <div className="button-container">
          <Button
            title="상세 정보"
            type="large green"
            onClick={() => {
              handleButtonClick("상세 정보");
            }}
          />
          <Button
            title="관리법"
            type="large green"
            onClick={() => {
              handleButtonClick("관리법");
            }}
          />
          <Button
            title="내 텃밭에 키우기"
            type="large green"
            onClick={() => {
              handleButtonClick("내 텃밭에 키우기");
            }}
          />
        </div>
      </div>
      <div className="detail-info">
        <div className="detail-title">
          <h2>{tab}</h2>
        </div>
        <div className="detail-content-container">
          {(tab === "상세 정보" || tab === "") && <BasicInfo data={data} />}
          {tab === "관리법" && <CareGuide data={data} />}
          {tab === "내 텃밭에 키우기" && <InMyGarden cropId={cropId} />}
        </div>
      </div>
    </section>
  );
};

export default DetailInfoContainer;
