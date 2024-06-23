import axios from "axios";
import React, { useEffect, useState } from "react";
import { getRequestDetail, getRequestList } from "../../util/api";
import RequestContent from "../../components/OfficialPage/RequestContent";
import RequestAnswer from "../../components/OfficialPage/RequestAnswer";
import Pagination from "../../components/common/Pagination";

const Inquiry = () => {
  const [nowPage, setNowPage] = useState(0);
  const [inquiryList, setInquiryList] = useState();
  const [selectInquiry, setSelectedInquiry] = useState();
  const [inquiryContent, setInquiryContent] = useState();

  useEffect(() => {
    const getInquiryList = async () => {
      const response = await getRequestList(nowPage, 10);
      setInquiryList(response);
    };
    getInquiryList();
  }, [nowPage]);

  if (!inquiryList) {
    return <div>Loading....</div>;
  }

  const handleSelectInquiry = async (id) => {
    setSelectedInquiry(id);
    const response = await getRequestDetail(id);
    setInquiryContent(response);
  };

  const handleSubmit = async (answer) => {
    console.log("select inquiry = ", selectInquiry);
    const response = await axios.put(
      `/api/v1/admins/inquiries/${selectInquiry}`,
      answer
    );
    if (response.status === 200 || response.status === 201) {
      console.log("수정 완료");
    }
  };

  return (
    <div className="admin-container">
      <h1 className="title">1:1 문의</h1>
      <table className="user-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>제목</th>
            <th>작성자</th>
            <th>수정일</th>
            <th>상태</th>
          </tr>
        </thead>
        <tbody>
          {inquiryList.map((item, index) => (
            <tr
              key={index}
              className={index % 2 === 0 ? "even-row" : "odd-row"}
              onClick={() => {
                handleSelectInquiry(item.id);
              }}
            >
              <td>{item.id}</td>
              <td>{item.title}</td>
              <td>{item.author || "-"}</td>
              <td>{item.modifideDate}</td>
              <td>{item.InquiryStatusType}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <Pagination totalPage={5} nowPage={nowPage} onClick={setNowPage} />

      {inquiryContent && <RequestContent data={inquiryContent} />}
      {inquiryContent && (
        <RequestAnswer
          data={inquiryContent}
          type={"edit"}
          onSubmit={handleSubmit}
        />
      )}
    </div>
  );
};

export default Inquiry;
