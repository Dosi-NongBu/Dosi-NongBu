import React from "react";

import { GoSearch } from "react-icons/go";
import { RiLightbulbFlashLine } from "react-icons/ri";

import "./style/SelectContainer.css";
import Banner from "../common/Banner";

const SelectContainer = ({ onChangeSelect }) => {
  return (
    <div className="SelectContainer">
      <Banner
        title={"새 텃밭 만들기"}
        subTitle={"도시농부와 함께 텃밭을 만들어보세요."}
      />
      <div className="select-container">
        <div className="button-container">
          <button
            className="select"
            onClick={() => {
              onChangeSelect("search");
            }}
          >
            <GoSearch size={150} />
          </button>
          <p className="button-container-text"> 검색하기 </p>
        </div>
        <div className="button-container">
          <button className="select">
            <RiLightbulbFlashLine
              size={150}
              onClick={() => {
                onChangeSelect("recommend");
              }}
            />
          </button>
          <p className="button-container-text"> 추천받기 </p>
        </div>
      </div>
    </div>
  );
};

export default SelectContainer;
