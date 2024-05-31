import axios from "axios";
import { makeOriginalThumbnail, makeSendImage } from "./gallaryImage";

// 전체 작물 목록 조회
export const getCropList = async (page, size) => {
  console.log(page, ", ", size);
  try {
    const response = await axios.get(`/api/v1/crops?page=${page}&size=${size}`);
    if (response.status === 200) {
      console.log("api response = ", response);
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

//cropId 에 해당하는 메인 정보 조회
export const getCropMainInfo = async (cropId) => {
  try {
    const response = await axios.get(`/api/v1/crops/${cropId}/main`);

    if (response.status === 200 || response.status === 201) {
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
export const getExistingUserSpace = async (page, size) => {
  const jwt = localStorage.getItem("accessToken");
  try {
    const response = await axios.get(
      `/api/v1/userplaces?page=${page}&size=${size}`,
      {
        headers: {
          Authorization: jwt,
        },
      }
    );

    // console.log("existing space ", response);
    if (response.status === 200 || response.status === 201) {
      console.log(response.data, " is data");
      // const newSpaces = response.data.places.map((place, index) => ({
      //   id: index + 1,
      //   name: place.name,
      // }));
      // console.log("new spaces = ", newSpaces);

      // return newSpaces;
      return response.data;
    }
  } catch (error) {
    console.error(error);
  }
};

// 새 공간 추가 함수
export const postNewUserPlace = async (newPlace) => {
  const jwt = localStorage.getItem("accessToken");
  const aa = {
    name: newPlace.name,
    placeType: newPlace.placeType,
    directionType: newPlace.directionType,
    lightType: newPlace.lightType,
    quantityType: newPlace.quantityType,
  };
  console.log(aa, "is aa");

  try {
    const response = await axios.post(
      `/api/v1/userplaces`,
      {
        // name: newPlace.name,
        // placeType: newPlace.placeType,
        // direction: newPlace.directionType,
        // light: newPlace.lightType,
        // quantity: newPlace.quantityType,
        aa,
      },
      {
        headers: {
          Authorization: jwt,
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

// 사용자 공간 삭제 함수
export const deleteUserSpace = async (placeId) => {
  try {
    const response = await axios.delete(`/api/v1/userplaces/${placeId}`);

    if (response.status === 200 || response.status === 201) {
      console.log("사용자 공간 삭제됨");
    }
  } catch (error) {
    console.log(error);
  }
};

// 최종 사용자 작물 전송
export const postMyCrop = async (cropInfo, cropId) => {
  // try {
  //   const response = await axios.post(`/api/v1/crops/${cropInfo.cropId}/add`, {
  //     cropId: cropInfo.cropId,
  //     userPlaceId: Number(cropInfo.userPlaceId),
  //     name: cropInfo.name,
  //     nickname: cropInfo.nickname,
  //   });
  //   if (response.status === 200) {
  //     console.log("작물 추가 성공");
  //   }
  // } catch (error) {
  //   console.log(error);
  // }
  try {
    const response = await axios.post(`/api/v1/crops/${cropId}/grow`, cropInfo);
    if (response.status === 200 || response.status === 201) {
      console.log("작물 등록 성공");
    }
  } catch (error) {
    console.log(error);
  }
};

// 사용자 작물 관리 페이지

// 사용자 작물 전체 보기
export const getUserCropAll = async () => {
  const jwt = localStorage.getItem("accessToken");
  try {
    const response = await axios.get("/api/v1/usercrops", {
      headers: {
        Authorization: jwt,
      },
    });

    if (response.status === 201 || response.status === 200) {
      console.log("response = ", response);
      return response.data;
    }
  } catch (e) {
    console.log(e);
  }
};

// 사용자 작물 관리 상세 - 정보, 이미지들
export const getUserCropDetail = async (userCropId) => {
  const jwt = localStorage.getItem("accessToken");
  try {
    const response = await axios.get(`/api/v1/usercrops/${userCropId}`, {
      headers: {
        Authorization: jwt,
      },
    });

    if (response.status === 201 || response.status === 200) {
      return response.data;
    }
  } catch (e) {
    console.log(e);
  }
};

// 사용자 작물 타임라인 조회
export const getUserTimeline = async (userCropId, page, size) => {
  try {
    const response = await axios.get(
      `/api/v1/manages/${userCropId}?page=${page}&size=${size}`
    );

    if (response.status === 200 || response.status === 201) {
      console.log(response);
      return response.data;
    }
  } catch (error) {
    console.log(error);
  }
};

// 사용자 작물 타임라인 추가
export const postUserTimeline = async (userCropId, cropManageType) => {
  try {
    const response = await axios.post(
      `/api/v1/manages/${userCropId}?cropManageType=${cropManageType}`
    );
  } catch (error) {
    console.log(error);
  }
};

// 사용자 작물 타임라인 삭제
export const deleteUserTimeline = async (userCropId, cropLogId) => {
  try {
    const response = await axios.delete(
      `/api/v1/manages/${userCropId}/${cropLogId}`
    );
    if (response.status === 200 || response.data === 201) {
      console.log("삭제 잘 됨 ..");
    }
  } catch (error) {
    console.log(error);
  }
};

// 사용자 작물 이미지 추가
export const postUserCropImage = async (userCropId, cropImages) => {
  const jwt = localStorage.getItem("accessToken");
  const sendData = makeSendImage(cropImages);

  try {
    const response = await axios.post(
      `/api/v1/images/${userCropId}`,
      {
        imageUrl: sendData,
      },
      {
        headers: {
          Authorization: jwt,
        },
      }
    );
  } catch (e) {
    console.log(e);
  }
};

// 사용자 작물 알림 조회
export const getCropNotification = async (userCropId) => {
  const jwt = localStorage.getItem("accessToken");
  try {
    const response = await axios.get(`/api/v1/alarms/${userCropId}`, {
      headers: {
        Authorization: jwt,
      },
    });

    if (response.status === 200 || response.status === 201) {
      return response.data;
    }
  } catch (error) {
    console.log(error);
  }
};

// 사용자 작물 알림 수정
export const putCropNotification = async (userCropId, notification) => {
  const jwt = localStorage.getItem("accessToken");
  try {
    const response = await axios.put(
      `/api/v1/alarms/${userCropId}`,
      {
        notification,
      },
      {
        headers: {
          Authorization: jwt,
        },
      }
    );

    if (response.status === 200 || response.status === 201) {
      console.log("알림 수정 성공");
    }
  } catch (e) {
    console.log(e);
  }
};

// 마이 페이지

// 사용자 프로필
export const getUserProfile = async () => {
  const jwt = localStorage.getItem("accessToken");
  try {
    const response = await axios.get("/api/v1/users", {
      headers: {
        Authorization: jwt,
      },
    });
    console.log("status = ", response.status);
    if (response.status === 200) {
      console.log(response.data);
      return response.data;
    }
  } catch (e) {
    console.log(e);
  }
};

// 사용자 정보 수정
export const putUserProfile = async (data) => {
  const jwt = localStorage.getItem("accessToken");
  try {
    const response = await axios.put("/api/v1/users", data, {
      headers: {
        Authorization: jwt,
      },
    });

    console.log("profile chagned response ", response);
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
    bug: "상추는 심은 후 대략 4-6주 내에 수확할 준비가 됩니다.\n\n잎이 부드럽고 싱싱하며 적당한 크기(20-30cm 정도)에 도달했을 때가 수확하기 가장 좋은 시기입니다.상추는 심은 후 대략 4-6주 내에 수확할 준비가 됩니다.\n\n잎이 부드럽고 싱싱하며 적당한 크기(20-30cm 정도)에 도달했을 때가 수확하기 가장 좋은 시기입니다.",
    tip: "상추에서 여름에 흔히 발생하는 잎 끝이나, 생장점이 타들어가는 듯 갈변하는 현상을 엽소현상(팁번)이라 합니다.\n주로 여름철 고온과 장일에 의한 생리장애로 칼슘이 부족할 때 생장점 부근의 어린 세포가 기계적으로 파괴되어 생기는 증상입니다.\n적절한 칼슘제나 칼슘보충제를 공급해주어야 합니다.",
    manage:
      "상추가 마르지 않도록 주기적으로 확인하고 필요한 경우, 키친타월을 촉촉하게 해서 수분을 조절해주세요.",
  };
  return ret;
};

export const mockData5 = () => {
  const get = {
    name: "상추",
    nickname: "싱싱이",
    imageUrls: [
      {
        imageUrl: "../../../public/picture5.png",
      },
      {
        imageUrl: "../../../public/picture3.png",
      },
      {
        imageUrl: "../../../public/picture4.png",
      },
    ],
    startDate: "2024-05-02",
    period: 10,
    prePeriod: 20,
    maxTemperature: 50,
    minTemperature: 30,
    humidity: 15,
  };

  const ret = makeOriginalThumbnail(get);
  return ret;
};

export const mockSend1 = (imageUrls) => {
  // console.log(makeSendImage(imageUrls));
  console.log(imageUrls);
};

export const mockData6 = () => {
  const ret = [
    {
      id: 1,
      imageUrl: "../../../public/picture.png",
      nickname: "Golden Wheat",
    },
    {
      id: 2,
      imageUrl: "../../../public/picture2.png",
      nickname: "Red Tomato",
    },
    {
      id: 3,
      imageUrl: "../../../public/picture3.png",
      nickname: "Green Lettuce",
    },
    {
      id: 4,
      imageUrl: "../../../public/picture4.png",
      nickname: "Blueberry Bliss",
    },
    {
      id: 5,
      imageUrl: "../../../public/picture5.png",
      nickname: "Sunny Sunflower",
    },
  ];
  return ret;
};
