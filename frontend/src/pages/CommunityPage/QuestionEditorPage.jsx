import React from "react";
import Banner from "../../components/common/Banner";
import CommunityEditorContainer from "../../components/CommunityPage/CommunityEditor";

const QuestionEditorPage = () => {
  return (
    <div>
      <Banner title={"질문게시판"} subTitle={"모르는 것을 질문해보세요"} />
      <CommunityEditorContainer type="QNA" />
    </div>
  );
};

export default QuestionEditorPage;
