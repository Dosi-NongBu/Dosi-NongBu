import React, { useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import { useForm } from "react-hook-form";
import { registerUser } from "../../store/thunkFunctions";
import { useNavigate } from "react-router-dom";

import "./style/RegisterPage.css";

const RegisterPage = () => {
  const nav = useNavigate();
  const {
    register,
    handleSubmit,
    formState: { errors },
    getValues,
    reset,
  } = useForm({ mode: "onChange" });

  const dispatch = useDispatch();
  const onSubmit = ({ email, password, name }) => {
    const body = {
      email,
      password,
      name,
    };
    dispatch(registerUser(body));
    alert("회원가입 되었습니다.");
    nav("/login");
  };

  const userEmail = {
    required: "필수 필드입니다.",
  };

  const userPassword = {
    required: "필수 필드입니다.",
    minLength: {
      value: 6,
      message: "최소 6자입니다.",
    },
  };

  const userPasswordCheck = {
    required: "비밀번호를 확인해주세요",
    validate: {
      matchPassword: (value) => {
        const { password } = getValues();
        return password === value || "비밀번호가 일치하지 않습니다";
      },
    },
  };

  const userName = {
    required: "필수 필드입니다.",
  };

  return (
    <section className="signupForm">
      <h1 className="signupTitle">회원가입</h1>
      <h3 className="signupSemiTitle">도시농부와 함께하는 손쉬운 텃밭관리</h3>
      <div className="formContainer">
        <form className="signupForm" onSubmit={handleSubmit(onSubmit)}>
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
            <label htmlFor="name" className="formLabel">
              이름
            </label>
            <input
              type="name"
              id="name"
              className="formInput"
              {...register("name", userName)}
            />
            {errors?.name && (
              <div>
                <span className="errorText">{errors.name.message}</span>
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

          <div className="formGroup">
            <label htmlFor="passwordCheck" className="formLabel">
              비밀번호 확인
            </label>
            <input
              type="password"
              id="passwordCheck"
              className="formInput"
              {...register("passwordCheck", userPasswordCheck)}
            />
            {errors?.passwordCheck && (
              <div>
                <span className="errorText">
                  {errors.passwordCheck.message}
                </span>
              </div>
            )}
          </div>

          <div className="submitContainer">
            <button type="submit" className="submitButton">
              회원가입
            </button>

            <p className="loginPrompt">
              아이디가 있다면{" "}
              <a href="/login" className="loginLink">
                로그인
              </a>
            </p>
          </div>
        </form>
      </div>
    </section>
  );
};

export default RegisterPage;
