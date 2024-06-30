import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";

import { IoStar } from "react-icons/io5";

import {
  mockData2,
  getCropBasicInfo,
  getCropMainInfo,
} from "../../util/api.jsx";
import "./style/DetailSumContainer.css";

const DetailSumContainer = () => {
  const [cropData, setCropData] = useState({});
  const { cropId } = useParams();

  useEffect(() => {
    const fetchData = async () => {
      // const response = mockData2(cropId);
      const response = await getCropMainInfo(Number(cropId));
      if (response) {
        setCropData(response);
      }
    };
    fetchData();
  }, [cropId]);

  return (
    <section className="detailSumContainer">
      <div className="basic-info-container">
        <div className="basic-image">
          <img src={cropData.imageUrl} />
        </div>
        <div className="basic-info">
          <h2>{cropData.name}</h2>

          <div className="each-info">
            <div className="each-title">난이도</div>
            <div className="each-content">
              {Array.from({ length: cropData.difficulty }, (_, index) => (
                <IoStar key={index} size="25px" color="#ED988F" />
              ))}
              {Array.from({ length: 5 - cropData.difficulty }, (_, index) => (
                <IoStar key={index} size="25px" color="#d9d9d9" />
              ))}
            </div>
          </div>

          <div className="each-info">
            <div className="each-title">적정온도</div>
            <div className="each-content">
              {cropData.minTemperature} ~ {cropData.maxTemperature} 도
            </div>
          </div>

          <div className="each-info">
            <div className="each-title">적정습도</div>
            <div className="each-content">{cropData.humidity} %</div>
          </div>
        </div>
      </div>
    </section>
  );
};

export default DetailSumContainer;
