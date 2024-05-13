import React, { useEffect, useState } from "react";

import "./style/CropBoxContainer.css";
import SearchBar from "../common/SearchBar";
import CropBoxArea from "../common/CropBoxArea";
import Pagination from "../common/Pagination";
import {
  getCropList,
  getRecommendCropList,
  getSearchCropList,
  mockData,
} from "../../util/api";

const CropBoxContainer = ({ type }) => {
  const [searchTerm, setSearchTerm] = useState("");
  const [cropList, setCropList] = useState([]);
  const [page, setpage] = useState(1);
  const [size, setSize] = useState(12);

  const fetchData = async () => {
    let response;

    if (type === "search") {
      response = await getCropList(page, size);
      // response = mockData();
    } else if (type === "recommend") {
      response = await getRecommendCropList(page, size);
      // response = mockData();
    }
    if (response) {
      setCropList(response);
    }
  };

  useEffect(() => {
    fetchData();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [page, size]);

  useEffect(() => {
    setpage(1);
    fetchData();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [type]);

  useEffect(() => {
    if (type !== "none" && cropList.length > 0) {
      // 스크롤 이동
      document
        .querySelector(".cropBoxContainer")
        .scrollIntoView({ behavior: "smooth" });
    }
  }, [type, cropList]);

  // 페이지네이션
  const handleClickPage = (newPage) => {
    setpage(newPage);
  };

  const handleSearchCrop = (e) => {
    setSearchTerm(e.target.value);
  };

  const handleSearchButton = () => {
    const fetchData = async () => {
      const response = await getSearchCropList(searchTerm, 1);
      // const response = mockData();
      if (response) {
        setCropList(response);
      }
    };
    fetchData();
  };

  return (
    <div className="cropBoxContainer">
      {type === "search" && (
        <>
          <h2>작물 검색</h2>
          <h3>원하는 작물을 검색해보세요.</h3>

          <SearchBar
            onSearchChange={handleSearchCrop}
            onClickButton={handleSearchButton}
          />
          <CropBoxArea data={cropList} />
        </>
      )}{" "}
      {type === "recommend" && (
        <>
          <h2>추천 작물</h2>
          <h3>도시농부가 추천하는 작물을 키워보세요.</h3>

          <CropBoxArea data={cropList} />
        </>
      )}
      {type !== "none" && (
        <Pagination totalPage={5} nowPage={page} onClick={handleClickPage} />
      )}
    </div>
  );
};

export default CropBoxContainer;
