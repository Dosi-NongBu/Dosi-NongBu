import React from "react";
import Banner from "../../components/common/Banner";
import Board from "../../components/OfficialPage/Board";

const NoticePage = () => {
  return (
    <div>
      <Banner title={"공지사항"} subTitle={"공지사항을 확인해 보세요"} />
      <Board type={"NOTICE"} />
    </div>
  );
};

export default NoticePage;
