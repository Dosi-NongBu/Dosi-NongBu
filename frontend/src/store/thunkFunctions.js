import { createAsyncThunk } from "@reduxjs/toolkit";
import axios from "axios";
import { useCookies } from "react-cookie"; // react-cookie에서 useCookies 불러오기

export const registerUser = createAsyncThunk(
  "user/registerUser",
  async (body, thunkAPI) => {
    try {
      console.log("register send ", body);
      const response = await axios.post("/api/v1/auth/join", body);
      if (response.status === 200 || response.data === 201) {
        return response.data;
      }
    } catch (error) {
      console.log(error);
      return thunkAPI.rejectWithValue(error.respone.data || error.message);
    }
  }
);

export const loginUser = createAsyncThunk(
  "user/loginUser",
  async (body, thunkAPI) => {
    try {
      console.log("login send, ", body);
      const response = await axios.post("/api/v1/auth/login", body);
      if (response.status === 200 || response.status === 201) {
        const accessToken = response.headers.authorization;
        // const refreshToken = response.headers["cookie-token"];
        // console.log("header = ", accessToken);

        // const [, setCookie] = useCookies(["refreshToken"]); // useCookies hook 사용

        // // accessToken 쿠키로 저장
        // setCookie("refreshToken", refreshToken, { path: "/" });

        return { ...response.data, accessToken };
      }
    } catch (error) {
      console.log(error);
      return thunkAPI.rejectWithValue(error.respone.data || error.message);
    }
  }
);

export const logoutUser = createAsyncThunk(
  "user/logoutUser",
  async (body, thunkAPI) => {
    try {
      const response = await axios.post(`/api/v1/auth/logout`, body);
      return response.data; // 백엔드에서 받아온 값
    } catch (error) {
      console.log(error);
      return thunkAPI.rejectWithValue(error.response.data || error.message);
    }
  }
);

export const loggedInUser = createAsyncThunk(
  "user/loggedInUser",
  async () => {}
);
