import React, { useEffect, useState } from "react";
import "./style/Board.css";
import Pagination from "../common/Pagination";
import Button from "../common/Button";
import { getCommunityList } from "../../util/api";
import { useNavigate } from "react-router-dom";

const Board = ({ type }) => {
  const nav = useNavigate();
  const [nowPage, setNowPage] = useState(0);
  const [posts, setPosts] = useState();

  useEffect(() => {
    const fetchData = async () => {
      const data = await getCommunityList(type, nowPage, 6);
      setPosts(data);
    };
    fetchData();
  }, [type, nowPage]);

  if (!posts) {
    return <>Loading...</>;
  }

  return (
    <div className="community-board">
      <Button
        title="새 글 등록"
        type={"negative"}
        onClick={() => {
          nav("/community/register");
        }}
      />
      <div className="community-list">
        {posts.map((post, index) => (
          <div
            key={index}
            className="each-community"
            onClick={() => {
              nav(`/community/${post.id}`);
            }}
          >
            <img src={post.imageUrl} />
            <div className="each-community-info">
              <h2>{post.title}</h2>
              <span>{post.author}</span>
              <img src={post.profileImage} />
            </div>
          </div>
        ))}
      </div>
      <Pagination totalPage={5} nowPage={nowPage} onClick={setNowPage} />
    </div>
  );
};

export default Board;
