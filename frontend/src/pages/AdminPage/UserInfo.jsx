import axios from "axios";
import React, { useEffect, useState } from "react";

const UserDetail = ({ userId }) => {
  const [detail, setDetail] = useState();
  const [isEditing, setIsEditing] = useState(false);
  const [editedDetail, setEditedDetail] = useState({});

  const getUserDetail = async (userId) => {
    const response = await axios.get(`/api/v1/admins/users/${userId}`);
    if (response.status === 200 || response.status === 201) {
      setDetail(response.data);
      setEditedDetail(response.data);
    }
  };

  const handleEdit = () => {
    setIsEditing(true);
  };

  const handleSave = async () => {
    const formData = new FormData();
    Object.keys(editedDetail).forEach((key) => {
      formData.append(key, editedDetail[key]);
    });

    console.log("edit ", editedDetail);
    const response = await axios.put(
      `/api/v1/admins/users/${userId}`,
      formData
    );
    if (response.status === 200 || response.status === 201) {
      setDetail(response.data);
      setIsEditing(false);
    }
  };

  const handleInputChange = (e) => {
    setEditedDetail({
      ...editedDetail,
      [e.target.name]: e.target.value,
    });

    if (e.target.name === "profileImage") {
      setEditedDetail({
        ...editedDetail,
        [e.target.name]: e.target.files[0] || editedDetail.profileImage,
      });
    }
  };

  useEffect(() => {
    getUserDetail(Number(userId));
  }, [userId]);

  if (!detail) {
    return <div>Loading...</div>;
  }

  return (
    <div>
      <h2>사용자 상세</h2>
      {isEditing ? (
        <>
          <p>아이디 : {detail.id}</p>
          <p>
            이름 :{" "}
            <input
              type="text"
              name="name"
              defaultValue={detail.name}
              onChange={handleInputChange}
            />
          </p>
          <p>
            프로필 이미지 :{" "}
            <input
              type="file"
              name="profileImage"
              onChange={handleInputChange}
            />
          </p>
          <p>
            닉네임 :{" "}
            <input
              type="text"
              name="nickname"
              defaultValue={detail.nickname}
              onChange={handleInputChange}
            />
          </p>
          <p>
            현 주소 :{" "}
            <input
              type="text"
              name="currentAddress"
              defaultValue={detail.currentAddress}
              onChange={handleInputChange}
            />
          </p>
          <p>
            이메일 :{" "}
            <input
              type="email"
              name="email"
              defaultValue={detail.email}
              onChange={handleInputChange}
            />
          </p>
          <p>가입일자 : {detail.createdDate}</p>
          <p>
            분류 :
            <select
              name="roleType"
              defaultValue={detail.roleType}
              onChange={handleInputChange}
            >
              <option value="USER">USER</option>
              <option value="ADMIN">ADMIN</option>
            </select>
          </p>
          <button onClick={handleSave}>완료</button>
        </>
      ) : (
        <>
          <p>아이디 : {detail.id}</p>
          <p>이름 : {detail.name}</p>
          <p>프로필 이미지 : {detail.profileImage}</p>
          <p>닉네임 : {detail.nickname}</p>
          <p>현 주소 : {detail.currentAddress}</p>
          <p>이메일 : {detail.email}</p>
          <p>가입일자 : {detail.createdDate}</p>
          <p>분류 : {detail.roleType}</p>
          <button onClick={handleEdit}>수정</button>
        </>
      )}
    </div>
  );
};

const UserInfo = () => {
  const [users, setUsers] = useState();
  const [selectUser, setSelectUser] = useState(null);

  const getUserList = async (page, size) => {
    const response = await axios.get(
      `/api/v1/admins/users?page=${page}&size=${size}`
    );
    if (response.status === 200 || response.status === 201) {
      setUsers(response.data);
    }
  };

  useEffect(() => {
    getUserList(0, 10);
  }, []);

  if (!users) {
    return <div>Loading...</div>;
  }

  return (
    <div className="admin-container">
      <h1 className="title">회원 정보 리스트</h1>
      <table className="user-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>이름</th>
            <th>닉네임</th>
            <th>이메일</th>
            <th>생성일</th>
          </tr>
        </thead>
        <tbody>
          {users.map((user, index) => (
            <tr
              key={index}
              className={index % 2 === 0 ? "even-row" : "odd-row"}
              onClick={() => {
                setSelectUser(user.id);
              }}
            >
              <td>{user.id}</td>
              <td>{user.name}</td>
              <td>{user.nickname || "-"}</td>
              <td>{user.email}</td>
              <td>{user.createdDate}</td>
            </tr>
          ))}
        </tbody>
      </table>

      {selectUser && <UserDetail userId={selectUser} />}
    </div>
  );
};

export default UserInfo;
