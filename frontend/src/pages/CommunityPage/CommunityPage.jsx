import React, { useEffect, useState } from "react";
import Banner from "../../components/common/Banner";
import Board from "../../components/CommunityPage/Board";
import Pagination from "../../components/common/Pagination";
import { getCommunityList } from "../../util/api";

const CommunityPage = () => {
  const [posts, setPosts] = useState();
  const [nowPage, setNowPage] = useState(0);

  useEffect(() => {
    const fetchData = async () => {
      const data = await getCommunityList("DEFAULT", nowPage, 6);
      setPosts(data);
    };
    fetchData();
  }, [nowPage]);

  if (!posts) {
    return (
      <>
        <h2>Loading...</h2>
      </>
    );
  }

  return (
    <div>
      <Banner title={"자유게시판"} subTitle={"자유롭게 의견을 나눠보세요"} />
      <Board type="DEFAULT" posts={posts} />
      <Pagination totalPage={5} nowPage={nowPage} onClick={setNowPage} />
    </div>
  );
};

export default CommunityPage;
