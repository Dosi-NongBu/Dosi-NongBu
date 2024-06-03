export const getDataInEng = (kor) => {
  switch (kor) {
    case "베란다(창 안)":
      return "VERANDA";
    case "베란다(창 밖)":
      return "TERRACE";
    case "실내":
      return "INSIDE";
    case "남향":
      return "SOUTH";
    case "동향":
      return "EAST";
    case "서향":
      return "WEST";
    case "직사광":
      return "DIRECT";
    case "창문을 통한 빛":
      return "THROUGH";
    case "없음":
      return "NONE";
    case "많음":
      return "MANY";
    case "중간":
      return "MEDIUM";
    case "적음":
      return "LESS";
  }
};
