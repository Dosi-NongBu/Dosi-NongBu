import React, { useEffect, useState } from "react";
import "./style/Board.css";
import Pagination from "../common/Pagination";
import Button from "../common/Button";
import { getCommunityList } from "../../util/api";
import { useNavigate } from "react-router-dom";
import { FaLongArrowAltRight } from "react-icons/fa";
import EachPost from "../common/EachPost";

const Board = ({ type, mode, posts }) => {
  const nav = useNavigate();

  const handleDirect = () => {
    if (type === "DEFAULT") {
      nav("/community");
    } else if (type === "QNA") {
      nav("/questionCommunity");
    }
  };

  return (
    <div className="community-board">
      {mode !== "READ" && (
        <Button
          title="새 글 등록"
          type={"negative"}
          onClick={() => {
            if (type === "DEFAULT") {
              nav("/community/register");
            } else {
              nav("/question/register");
            }
          }}
        />
      )}
      {mode === "READ" && (
        <div className="header-move">
          {type === "DEFAULT" && <h2>자유게시판</h2>}
          {type === "QNA" && <h2>질문게시판</h2>}

          <FaLongArrowAltRight size={30} onClick={handleDirect} />
        </div>
      )}

      <div className="community-list">
        {posts &&
          posts.map((post, index) => (
            <EachPost
              key={post.id}
              id={post.id}
              imageUrl={post.imageUrl}
              title={post.title}
              author={post.author}
              profileImage={post.profileImage}
              type={type}
            />
          ))}
      </div>
    </div>
  );
};

export default Board;
