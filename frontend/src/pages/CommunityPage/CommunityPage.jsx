import React from "react";
import Banner from "../../components/common/Banner";
import Board from "../../components/CommunityPage/Board";

const CommunityPage = () => {
  return (
    <div>
      <Banner title={"자유게시판"} subTitle={"자유롭게 의견을 나눠보세요"} />
      <Board type="DEFAULT" />
    </div>
  );
};

export default CommunityPage;
