import React from "react";
import Banner from "../../components/common/Banner";
import Board from "../../components/OfficialPage/Board";

const FAQPage = () => {
  return (
    <div>
      <Banner title={"FAQ 게시판"} subTitle={"FAQ를 확인할 수 있습니다."} />
      <Board type={"FAQ"} />
    </div>
  );
};

export default FAQPage;
