import axios from "axios";

// const axiosInstance = axios.create({
//   baseURL: import.meta.env.PROD ? " " : "http://localhost:8080",
// });

const axiosInstance = axios.create({
  withCredentials: false,
  baseURL: import.meta.env.PROD ? " " : "http://localhost:8080",
});

// axiosInstance.interceptors.request.use(
//   function (config) {
//     // 요청 보내기 전에 어떤 일을 하고싶을 때.
//     config.withCredentials = true; // withCredentials 옵션 추가
//   },
//   function (error) {
//     return Promise.reject(error);
//   }
// );

// // jwt 토큰 만료됐을 때.
// axiosInstance.interceptors.response.use(
//   function (response) {
//     return response;
//   },
//   function (error) {
//     if (error.response.data === "jwt expired") {
//       window.location.reload();
//     }
//     return Promise.reject(error);
//   }
// );
export default axiosInstance;
