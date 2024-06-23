import React, { useState, useEffect } from "react";
import Banner from "../../components/common/Banner";
import Board from "../../components/CommunityPage/Board";
import { getCommunityList } from "../../util/api";
import Pagination from "../../components/common/Pagination";

const QuestionPage = () => {
  const [posts, setPosts] = useState();
  const [nowPage, setNowPage] = useState(0);

  useEffect(() => {
    const fetchData = async () => {
      const data = await getCommunityList("QNA", nowPage, 6);
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
      <Banner title={"질문게시판"} subTitle={"모르는 것을 질문해보세요"} />
      <Board type="QNA" posts={posts} />
      <Pagination totalPage={5} nowPage={nowPage} onClick={setNowPage} />
    </div>
  );
};

export default QuestionPage;
