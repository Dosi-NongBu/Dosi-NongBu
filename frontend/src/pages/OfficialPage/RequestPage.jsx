import React from "react";
import Banner from "../../components/common/Banner";
import Board from "../../components/OfficialPage/Board";

const RequestPage = () => {
  return (
    <div>
      <Banner title="1:1 게시판" subTitle={"모르는 것을 질문해보세요."} />
      <Board type="REQUEST" />
    </div>
  );
};

export default RequestPage;
