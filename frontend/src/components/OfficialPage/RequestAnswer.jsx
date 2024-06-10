import React from "react";
import "./style/RequestAnswer.css";
import Status from "./Status";

const RequestAnswer = ({ data }) => {
  return (
    <div>
      <table className="request-table">
        <tbody>
          <tr>
            <th>답변현황</th>
            <td>
              <Status type={data.inquiryStatusType} />
            </td>
          </tr>
          <tr>
            <th>답변내용</th>
            <td>{data.inquiryAnswer}</td>
          </tr>
        </tbody>
      </table>
    </div>
  );
};

export default RequestAnswer;
