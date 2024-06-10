import React, { useEffect, useState } from "react";
import Banner from "../../components/common/Banner";
import OfficialContainer from "../../components/OfficialPage/OfficialContainer";
import RequestContent from "../../components/OfficialPage/RequestContent";
import RequestAnswer from "../../components/OfficialPage/RequestAnswer";
import Button from "../../components/common/Button";
import { useNavigate, useParams } from "react-router-dom";
import { deleteRequest, getRequestDetail } from "../../util/api";

const RequestDetailPage = () => {
  const nav = useNavigate();
  const [detail, setDetail] = useState();
  const { requestId } = useParams();

  useEffect(() => {
    const fetchData = async () => {
      const data = await getRequestDetail(Number(requestId));
      setDetail(data);
    };
    fetchData();
  }, [requestId]);

  const handleEditRequest = () => {
    nav(`/request/edit/${Number(requestId)}`);
  };

  const handleDeleteRequest = async () => {
    if (!window.confirm("삭제하시겠습니까?")) {
      return;
    }
    await deleteRequest(Number(requestId));
    nav("/request");
  };

  return (
    <div>
      <Banner title="1:1 게시판" subTitle={"모르는 것을 질문해보세요."} />
      <div className="edit-delete-container">
        <Button
          title={"삭제"}
          type={"negative"}
          onClick={handleDeleteRequest}
        />
        <Button title={"수정"} type={"negative"} onClick={handleEditRequest} />
      </div>
      {detail && <RequestContent data={detail} />}
      {detail && <RequestAnswer data={detail} />}
      <div className="tolist-container">
        <Button
          title={"목록으로"}
          type={"positive"}
          onClick={() => {
            nav("/request");
          }}
        />
      </div>
    </div>
  );
};

export default RequestDetailPage;
