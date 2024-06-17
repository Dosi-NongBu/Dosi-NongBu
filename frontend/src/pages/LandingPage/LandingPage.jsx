import React, { useState, useEffect } from "react";
import MainBanner from "../../components/common/MainBanner";
import Board from "../../components/CommunityPage/Board";
import { getCommunityList, getCropList } from "../../util/api";
import CropBoxArea from "../../components/common/CropBoxArea";

const LandingPage = () => {
  const [posts, setPosts] = useState();
  const [qnaPosts, setQnaPosts] = useState();

  useEffect(() => {
    const fetchData = async () => {
      const data = await getCommunityList("DEFAULT", 0, 6);
      const qnaData = await getCommunityList("QNA", 0, 6);
      setPosts(data);
      setQnaPosts(qnaData);
    };
    fetchData();
  }, []);

  if (!posts) {
    return (
      <>
        <h2>Loading...</h2>
      </>
    );
  }

  return (
    <div>
      <MainBanner />
      <Board type="DEFAULT" mode={"READ"} posts={posts} />
      <Board type="QNA" mode={"READ"} posts={qnaPosts} />
    </div>
  );
};

export default LandingPage;
