import React from "react";
import { useParams } from "react-router-dom";
import Banner from "../../components/common/Banner";
import OfficialContainer from "../../components/OfficialPage/OfficialContainer";

const FAQDetailPage = () => {
  const params = useParams();

  return (
    <div>
      <Banner title={"FAQ 게시판"} subTitle={"FAQ를 확인할 수 있습니다."} />
      <OfficialContainer type="FAQ" id={params.faqId} />
    </div>
  );
};

export default FAQDetailPage;
