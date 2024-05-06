import axios from "axios";

// 전체 작물 목록 조회
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

// 추천 작물 목록 조회
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

// 검색어를 통한 작물 목록 조회
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

// cropId 에 해당하는 기본정보 조회
export const getCropBasicInfo = async (cropId) => {
  try {
    const response = await axios.get(`/api/v1/crops/${cropId}`);
    if (response.status === 200) {
      return response.data;
    }
  } catch (error) {
    console.log(error);
  }
};

// cropId에 해당하는 상세정보 조회
export const getCropDetailInfo = async (cropId) => {
  try {
    const response = await axios.get(`/api/v1/crops/${cropId}/info`);
    if (response.status === 200) {
      return response.data;
    }
  } catch (error) {
    console.log(error);
  }
};

// 현재 존재하는 사용자 공간 조회
export const getExistingUserSpace = async (token, page, size) => {
  try {
    const response = await axios.get(
      `/api/v1/userplaces?page=${page}&size=${size}`,
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
        params: {
          page, // 요청할 페이지 번호
          size, // 페이지 당 아이템 수
        },
      }
    );

    if (response.data === 200) {
      const newSpaces = response.data.places.map((place, index) => ({
        id: index + 1,
        name: place.name,
      }));

      return newSpaces;
    }
  } catch (error) {
    console.error(error);
  }
};

// 새 공간 추가 함수
export const postNewUserPlace = async (newPlace, JWT) => {
  try {
    const response = await axios.post(
      `/api/v1/userplaces`,
      {
        name: newPlace.name,
        placeType: newPlace.placeType,
        directionType: newPlace.directionType,
        lightType: newPlace.lightType,
        quantityType: newPlace.quantityType,
      },
      {
        headers: {
          Authorization: `Bearer ${JWT}`,
        },
      }
    );
    if (response.status === 200 || response.status === 201) {
      console.log("공간 추가 성공");
      return response.data;
    }
  } catch (error) {
    console.log(error);
  }
};

export const postMyCrop = async (cropInfo, cropId) => {
  try {
    const response = await axios.post(`/api/v1/crops/${cropId}/add`, {
      cropId: Number(cropId),
      userPlaceId: Number(cropInfo.userPlaceId),
      name: cropInfo.name,
      nickname: cropInfo.nickname,
    });
    if (response.status === 200) {
      console.log("작물 추가 성공");
    }
  } catch (error) {
    console.log(error);
  }
};

/*
Mock Data Area
*/

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

export const mockData2 = (cropId) => {
  const ret = {
    cropImage: "../../../public/vite.svg",
    name: "당근",
    difficultyType: 4,
    maxTemperature: 20,
    minTemperature: 15,
    humidity: 50,
  };
  return ret;
};

export const mockData3 = () => {
  const ret = {
    classification: "속씨식물문",
    origin: "한국",
    feature: "특징",
    plant: "3,5,7",
    grow: " 본잎이 5-6장 정도 자란 모종을 심습니다. 잎이 병해충 피해나 상처가 없고, 뿌리는 하얗고 굵게 잘 자란 모종을 고릅니다.\n20x20cm 간격으로 모종의 뿌리부분이 들어갈 정도의 구멍을 만들고 모종을 넣습니다. 뿌리와 토양이 밀착되도록 심어줍니다.\n뿌리가 충분히 젖을 정도로 물을 줍니다. 물이 충분히 스며든 후에는 뿌리의 위쪽 표면이 살짝 보일 정도로 흙을 덮어줍니다.",
    bug: "상추는 심은 후 대략 4-6주 내에 수확할 준비가 됩니다.\n\n잎이 부드럽고 싱싱하며 적당한 크기(20-30cm 정도)에 도달했을 때가 수확하기 가장 좋은 시기입니다.",
    tip: "상추에서 여름에 흔히 발생하는 잎 끝이나, 생장점이 타들어가는 듯 갈변하는 현상을 엽소현상(팁번)이라 합니다.\n주로 여름철 고온과 장일에 의한 생리장애로 칼슘이 부족할 때 생장점 부근의 어린 세포가 기계적으로 파괴되어 생기는 증상입니다.\n적절한 칼슘제나 칼슘보충제를 공급해주어야 합니다.",
    manage:
      "상추가 마르지 않도록 주기적으로 확인하고 필요한 경우, 키친타월을 촉촉하게 해서 수분을 조절해주세요.",
  };
  return ret;
};
