import React from "react";
import Banner from "../../components/common/Banner";
import OfficialContainer from "../../components/OfficialPage/OfficialContainer";

import { useParams } from "react-router-dom";

const NoticeDetailPage = () => {
  const params = useParams();

  return (
    <div>
      <Banner title={"공지사항"} subTitle={"공지사항을 확인해 보세요"} />
      <OfficialContainer type={"NOTICE"} id={params.noticeId} />
    </div>
  );
};

export default NoticeDetailPage;
