import React from "react";
import Banner from "../../components/common/Banner";
import RequestEditor from "../../components/OfficialPage/RequestEditor";
import Gallery from "../../components/common/Gallery";

const RequestRegisterPage = () => {
  return (
    <div>
      <Banner title="1:1 게시판" subTitle={"모르는 것을 질문해보세요."} />
      <RequestEditor />
    </div>
  );
};

export default RequestRegisterPage;
