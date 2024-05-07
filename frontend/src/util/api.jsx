import axios from "axios";

// 작물 목록 조회
export const getCropList = async (page, size) => {
  try {
    const response = await axios.get(`/api/v1/crops?page=${page}&size=${size}`);
    if (response.status === 200) {
      return response.data;
    }
  } catch (error) {
    console.error(error);
  }
};

// 작물 추천 목록 조회
export const getRecommendCropList = async (page, size) => {
  try {
    const response = await axios.get(
      `/api/v1/crops/recommend?page=${page}&size=${size}`
    );
    if (response.status === 200) {
      return response.data;
    }
  } catch (error) {
    console.error(error);
  }
};

// 작물 검색 목록 조회
export const getSearchCropList = async (keyword, lastIndex) => {
  try {
    const response = await axios.get(
      `/api/v1/crops/${keyword}?lastIndex=${lastIndex}`
    );
    if (response.status === 200) {
      return response.data;
    }
  } catch (error) {
    console.error(error);
  }
};

export const mockData = () => {
  const ret = [
    { name: "새싹", cropsImage: "../../../public/picture.png", cropId: 1 },
    { name: "상추", cropsImage: "../../../public/picture2.png", cropId: 2 },
    { name: "리액트", cropsImage: "../../../public/vite.svg", cropId: 3 },
    { name: "새싹", cropsImage: "../../../public/picture.png", cropId: 4 },
    { name: "상추", cropsImage: "../../../public/picture2.png", cropId: 5 },
    { name: "리액트", cropsImage: "../../../public/vite.svg", cropId: 6 },
    { name: "새싹", cropsImage: "../../../public/picture.png", cropId: 7 },
    { name: "상추", cropsImage: "../../../public/picture2.png", cropId: 8 },
    { name: "리액트", cropsImage: "../../../public/vite.svg", cropId: 9 },
  ];
  return ret;
};
