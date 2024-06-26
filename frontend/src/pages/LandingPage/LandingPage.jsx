import React, { useState, useEffect } from "react";
import MainBanner from "../../components/common/MainBanner";
import Board from "../../components/CommunityPage/Board";
import {
  getCommunityList,
  getCropList,
  getRecommendCropList,
} from "../../util/api";
import CropBoxArea from "../../components/common/CropBoxArea";

const LandingPage = () => {
  const [posts, setPosts] = useState();
  const [qnaPosts, setQnaPosts] = useState();
  const [recommendCrops, setRecommendCrosp] = useState();

  useEffect(() => {
    const fetchData = async () => {
      const data = await getCommunityList("DEFAULT", 0, 6);
      const qnaData = await getCommunityList("QNA", 0, 6);
      const recommend = await getRecommendCropList(0, 3);
      setPosts(data);
      setQnaPosts(qnaData);
      setRecommendCrosp(recommend);
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
      <div className="community-board community-board-grey">
        <h2>이런 작물을 키워보세요!</h2>
        <CropBoxArea data={recommendCrops} type={"CROP"} />
      </div>

      <Board type="DEFAULT" mode={"READ"} posts={posts} />
      <Board type="QNA" mode={"READ"} posts={qnaPosts} />
    </div>
  );
};

export default LandingPage;
