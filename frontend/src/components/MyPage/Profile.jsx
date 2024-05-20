import React, { useState } from "react";
import { useDropzone } from "react-dropzone";

import "./style/Profile.css";
import Button from "../common/Button";

const Profile = () => {
  // API로 가져올 것
  const [user, setUser] = useState({
    name: "name",
    nickname: "nickname",
    email: "idkhm0728@naver.com",
    address: "경기도 용인시 기흥구",
    profileImage: "../../../public/vite.svg",
    provider: "DEFAULT",
  });
  const [onEdit, setOnEdit] = useState(false);

  const onChangeProfile = (e) => {
    const { name, value } = e.target;

    setUser((prevUser) => ({
      ...prevUser,
      [name]: value,
    }));
  };

  const { getRootProps, getInputProps } = useDropzone({
    onDrop: (acceptedFiles) => {
      console.log(acceptedFiles);
      // 파일 업로드 로직 추가
      setUser((prevUser) => ({
        ...prevUser,
        profileImage: acceptedFiles,
      }));
    },
  });

  return (
    <div className="profile">
      {!onEdit && (
        <div className="profile-container">
          <div className="profile-info">
            <div className="each-profile">
              <h2>내 프로필</h2>
              <Button
                title={"수정하기"}
                type={"negative"}
                onClick={() => {
                  setOnEdit(!onEdit);
                }}
              />
            </div>
            <div className="each-profile">
              <span>이름</span>
              <span>{user.name}</span>
            </div>

            <div className="each-profile">
              <span>닉네임</span>
              <span>{user.nickname}</span>
            </div>

            <div className="each-profile">
              <span>이메일</span>
              <span>{user.email}</span>
            </div>

            <div className="each-profile">
              <span>주소</span>
              <span>{user.address}</span>
            </div>
          </div>
          <div className="profile-image">
            <img src={user.profileImage} />
          </div>
        </div>
      )}{" "}
      {onEdit && (
        <div className="profile-container">
          <div className="profile-info">
            <div className="each-profile">
              <h2>내 프로필</h2>
              <Button
                title={"저장하기"}
                type={"negative"}
                onClick={() => {
                  setOnEdit(!onEdit);

                  // API 전송 코드 작성
                }}
              />
            </div>
            <div className="each-profile">
              <span>이름</span>
              <input name="name" value={user.name} onChange={onChangeProfile} />
            </div>

            <div className="each-profile">
              <span>닉네임</span>
              <input
                name="nickname"
                value={user.nickname}
                onChange={onChangeProfile}
              />
            </div>

            <div className="each-profile">
              <span>이메일</span>
              <input
                name="email"
                value={user.email}
                onChange={onChangeProfile}
              />
            </div>

            <div className="each-profile">
              <span>주소</span>
              <input
                name="address"
                value={user.address}
                onChange={onChangeProfile}
              />
            </div>
          </div>
          {/* <div className="profile-image">
            <Dropzone onDrop={(acceptFiles) => console.log(acceptFiles)}>
              <img src={user.profileImage} />
            </Dropzone>
          </div> */}
          <div className="profile-image">
            <div {...getRootProps()} className="dropzone">
              <input {...getInputProps()} />
              <div className="profile-container">
                {user.profileImage ? (
                  <>
                    <img
                      src={user.profileImage}
                      alt="Profile"
                      className="profile-image-container"
                    />
                    <div className="overlay">
                      <div className="text">+</div>
                    </div>
                  </>
                ) : (
                  <div className="placeholder">
                    프로필 이미지를 업로드하세요.
                  </div>
                )}
              </div>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default Profile;
