import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import noPhoto from "../../../public/no-photos.png";

import "./style/EachPost.css";

const EachPost = ({ id, imageUrl, title, author, profileImage, type }) => {
  const nav = useNavigate();

  return (
    <div
      className="each-community"
      onClick={() => {
        if (type === "DEFAULT") {
          nav(`/community/${id}`);
        } else {
          nav(`/question/${id}`);
        }
      }}
    >
      <img src={imageUrl} />
      <div className="each-community-info">
        <h2>{title}</h2>
        <span>{author}</span>
        <img
          src={profileImage ? profileImage : noPhoto}
          className="each-community-profile"
        />
      </div>
    </div>
  );
};

export default EachPost;
