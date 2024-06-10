import React from "react";
import { useParams } from "react-router-dom";
import Detail from "../../components/OfficialPage/Detail";
import Banner from "../../components/common/Banner";
import OfficialContainer from "../../components/OfficialPage/OfficialContainer";
const FAQDetailPage = () => {
  return (
    <div>
      <Banner title={"FAQ 게시판"} subTitle={"FAQ를 확인할 수 있습니다."} />
      <OfficialContainer type="FAQ" />
    </div>
  );
};

export default FAQDetailPage;
