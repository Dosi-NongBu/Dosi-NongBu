import React from "react";
import Banner from "../../components/common/Banner";
import Board from "../../components/CommunityPage/Board";

const QuestionPage = () => {
  return (
    <div>
      <Banner title={"질문게시판"} subTitle={"모르는 것을 질문해보세요"} />
      <Board type="QNA" />
    </div>
  );
};

export default QuestionPage;
