import React, { useState, useEffect } from "react";
import "./style/MyEach.css";
import Board from "../CommunityPage/Board";
import { getUserPost } from "../../util/api";

const MyPost = () => {
  const [posts, setPosts] = useState();

  useEffect(() => {
    const fetchData = async () => {
      const data = await getUserPost(0, 12);
      setPosts(data);
      console.log("data=", data);
    };
    fetchData();
  }, []);

  return (
    <div className="myPage-each">
      <div className="myPage-each-container">
        <h2>내 글 모아보기</h2>
        {/* <Board type="DEFAULT" mode="READ" posts={posts} /> */}
      </div>
    </div>
  );
};

export default MyPost;
