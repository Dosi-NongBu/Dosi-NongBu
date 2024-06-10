import React from "react";
import "./style/Detail.css";

const Detail = ({ onBack }) => {
  const post = {
    id: 1,
    title: "첫 번째 글",
    author: "작성자1",
    date: "2024-06-01",
  };

  return (
    <div className="detail">
      <button className="back-button" onClick={onBack}>
        뒤로가기
      </button>
      <h1>{post.title}</h1>
      <div className="post-info">
        <span>작성자: {post.author}</span>
        <span>작성일: {post.date}</span>
      </div>
      <div className="post-content">
        <p>여기에 글 내용을 넣으세요...</p>
      </div>
    </div>
  );
};

export default Detail;
