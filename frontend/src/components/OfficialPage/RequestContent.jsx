import React, { useEffect } from "react";
import "./style/RequestContent.css";
import Gallery from "../common/Gallery";
import { getInquiryType } from "../../util/GetInquiryType";

const RequestContent = ({ data }) => {
  return (
    <div>
      <table className="request-table">
        <tbody>
          <tr>
            <th>문의유형</th>
            <td>{getInquiryType(data.inquiryType)}</td>
          </tr>
          <tr>
            <th>제목</th>
            <td>{data.title}</td>
          </tr>
          <tr>
            <th>내용</th>
            <td>{data.content}</td>
          </tr>
        </tbody>
      </table>
      <Gallery readImages={data.imageUrls} />
    </div>
  );
};

export default RequestContent;
