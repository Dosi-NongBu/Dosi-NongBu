import { createSlice } from "@reduxjs/toolkit";
import { registerUser, loginUser, logoutUser } from "./thunkFunctions";
import { useNavigate } from "react-router-dom";

const initialState = {
  userData: {
    id: "",
    email: "",
    name: "",
    role: 0,
    image: "",
  },
  isAuth: false,
  isLoading: false,
  error: "",
};

const userSlice = createSlice({
  name: "user",
  initialState: initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(registerUser.pending, (state) => {
        state.isLoading = true;
      })
      .addCase(registerUser.fulfilled, (state) => {
        state.isLoading = false;
        console.log("회원가입 성공");
      })
      .addCase(registerUser.rejected, (state, action) => {
        state.isLoading = false;
        state.error = action.payload;
        console.log("회원가입 실패");
      })
      .addCase(loginUser.pending, (state) => {
        state.isLoading = true;
      })
      .addCase(loginUser.fulfilled, (state, action) => {
        state.isLoading = false;
        state.isAuth = true;
        state.userData = action.payload;
        localStorage.setItem("accessToken", action.payload.accessToken);
        console.log("로그인 성공");
        // location.reload(true);
      })
      .addCase(loginUser.rejected, (state, action) => {
        state.isLoading = false;
        console.log("로그인 실패");
      })
      .addCase(logoutUser.pending, (state) => {
        state.isLoading = true;
        console.log("로그아웃 중 ..");
      })
      .addCase(logoutUser.rejected, (state, action) => {
        state.isLoading = false;
        console.log("로그아웃 실패");
      })
      .addCase(logoutUser.fulfilled, (state, action) => {
        state.isLoading = false;
        state.userData = initialState.userData;
        state.isAuth = false;
        localStorage.removeItem("accessToken");
        console.log("로그아웃 성공");
        location.reload(true);
      });
  },
});

export default userSlice.reducer;
