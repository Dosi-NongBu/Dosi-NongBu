import React, { useEffect, useState, useRef } from "react";
import { CgProfile } from "react-icons/cg";
import "./style/Profile.css";
import Button from "../common/Button";
import { getUserProfile, putUserProfile } from "../../util/api";

const Profile = () => {
  const fileInputRef = useRef(null);

  // API로 가져올 것
  const [user, setUser] = useState({});

  const [changed, setChanged] = useState({
    name: false,
    nickname: false,
    email: false,
    address: false,
    profileImage: false,
  });
  const [onEdit, setOnEdit] = useState(false);

  // 프로필 fetch
  useEffect(() => {
    const fetchData = async () => {
      const data = await getUserProfile();
      setUser(data);
    };
    fetchData();
  }, []);

  // 수정
  const sendEdittedData = async () => {
    setOnEdit(!onEdit);

    putUserProfile(user);
    console.log("새 사용자 = ", user);
  };

  const onChangeProfile = (e) => {
    const { name, value } = e.target;
    setUser((prevUser) => ({
      ...prevUser,
      [name]: value, // 입력 필드의 name 속성에 따라 user 객체 업데이트
    }));
  };

  // 이미지 클릭 시 파일 입력 요소를 클릭하는 함수
  const handleImageClick = () => {
    if (fileInputRef.current) {
      fileInputRef.current.click();
    }
  };

  const handleAddImage = (event) => {
    const file = event.target.files[0];
    if (file) {
      setUser((prevUser) => ({
        ...prevUser,
        profileImage: file.name,
      }));
    }
  };

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
            {user.profileImage ? (
              <img src={user.profileImage} alt="프로필 이미지" />
            ) : (
              <CgProfile size={150} />
            )}
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
                onClick={sendEdittedData}
              />
            </div>
            <div className="each-profile">
              <span>이름</span>
              <input name="name" value={user.name || ""} disabled />
            </div>

            <div className="each-profile">
              <span>닉네임</span>
              <input
                name="nickname"
                value={user.nickname || ""}
                onChange={onChangeProfile}
              />
            </div>

            <div className="each-profile">
              <span>이메일</span>
              <input name="email" value={user.email || ""} disabled />
            </div>

            <div className="each-profile">
              <span>주소</span>
              <input
                name="address"
                value={user.address || ""}
                onChange={onChangeProfile}
              />
            </div>
          </div>

          <div className="profile-image">
            <input
              type="file"
              id="fileInput"
              ref={fileInputRef}
              onChange={handleAddImage}
              className="file-input"
              style={{ display: "none" }} // 파일 입력 요소를 숨김
            />
            <div className="image-container" onClick={handleImageClick}>
              <img src={user.profileImage} />
              <div className="overlay">
                <span className="plus-icon">+</span>
              </div>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default Profile;
