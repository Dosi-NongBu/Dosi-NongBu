import React from "react";
import Banner from "../../components/common/Banner";
import CommunityEditorContainer from "../../components/CommunityPage/CommunityEditor";

const CommunityEditorPage = () => {
  return (
    <div>
      <Banner title={"자유게시판"} subTitle={"자유롭게 의견을 나눠보세요"} />
      <CommunityEditorContainer type="DEFAULT" />
    </div>
  );
};

export default CommunityEditorPage;
