import React, { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";

import toast, { Toaster } from "react-hot-toast";

import { loginUser } from "../../store/thunkFunctions";
import "./style/LoginPage.css";
import NaverLoginButton from "../../assets/naver-login-button.png";
import GoogleLoginButton from "../../assets/google-login-button2.png";
import axios from "axios";

const LoginPage = () => {
  const nav = useNavigate();
  const {
    register,
    handleSubmit,
    formState: { errors },
    reset,
  } = useForm({ mode: "onChange" });

  const dispatch = useDispatch();
  const onSubmit = async ({ email, password }) => {
    const body = {
      email,
      password,
    };
    try {
      await dispatch(loginUser(body)).unwrap();
      nav("/");
      location.reload(true);
    } catch (error) {
      toast.error(error.message);
    }
  };

  const userEmail = {
    required: "필수 필드입니다.",
  };

  const userPassword = {
    // required: "필수 필드입니다.",
    // minLength: {
    //   value: 6,
    //   message: "최소 6자입니다.",
    // },
  };

  /*
  추후 소셜 로그인 구현할 때 사용
  */
  // const handleGoogleLogin = async () => {
  //   window.location.href = "http://localhost:8080/oauth2/authorization/google";
  // };

  // const handleNaverLogin = () => {
  //   window.location.href = "http://localhost:8080/oauth2/authorization/naver";
  // };

  return (
    <section className="loginForm">
      <div>
        <Toaster />
      </div>
      <h1 className="loginTitle">로그인</h1>
      <div className="formContainer">
        <form className="loginForm" onSubmit={handleSubmit(onSubmit)}>
          <div className="formGroup">
            <label htmlFor="email" className="formLabel">
              이메일
            </label>
            <input
              type="email"
              id="email"
              className="formInput"
              {...register("email", userEmail)}
            />
            {errors?.email && (
              <div>
                <span className="errorText">{errors.email.message}</span>
              </div>
            )}
          </div>

          <div className="formGroup">
            <label htmlFor="password" className="formLabel">
              비밀번호
            </label>
            <input
              type="password"
              id="password"
              className="formInput"
              {...register("password", userPassword)}
            />
            {errors?.password && (
              <div>
                <span className="errorText">{errors.password.message}</span>
              </div>
            )}
          </div>
          <button type="submit" className="submitButton">
            로그인
          </button>

          <p className="signupPrompt">
            아이디가 없다면{" "}
            <a href="/register" className="signupLink">
              회원가입
            </a>
          </p>
        </form>
      </div>

      {/* <div className="social-login">
        <h2>간편 로그인</h2>
        <div className="each-login-container">
          <img
            src={NaverLoginButton}
            className="each-login naver-login"
            // onClick={handleNaverLogin}
          />
          <img
            src={GoogleLoginButton}
            className="each-login google-login"
            // onClick={handleGoogleLogin}
          />
        </div>
      </div> */}
    </section>
  );
};

export default LoginPage;
