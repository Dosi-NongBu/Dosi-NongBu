import React, { useEffect, useState } from "react";
import "./style/RequestAnswer.css";
import Status from "./Status";

/*
type = read
type= edit 
 */

const RequestAnswer = ({ data, type, onSubmit }) => {
  const [answer, setAnswer] = useState(
    data.inquiryAnswer ? data.inquiryAnswer : ""
  );
  const [inquiryStatusType, setInquiryStatusType] = useState(
    data.inquiryStatusType ? data.inquiryStatusType : "NO"
  );

  const handleAnswerChange = (event) => {
    setAnswer(event.target.value);
  };

  const handleTypeChange = (event) => {
    setInquiryStatusType(event.target.value);
  };

  useEffect(() => {
    setAnswer(data.inquiryAnswer ? data.inquiryAnswer : "");
    setInquiryStatusType(
      data.inquiryStatusType ? data.inquiryStatusType : "NO"
    );
  }, [data]);

  return (
    <div>
      <table className="request-table">
        <tbody>
          <tr>
            <th>답변현황</th>
            <td>
              {type === "read" && <Status type={data.inquiryStatusType} />}
              {type === "edit" && (
                <select value={inquiryStatusType} onChange={handleTypeChange}>
                  <option value="NO">NO</option>
                  <option value="ANSWERING">ANSWERING</option>
                  <option value="COMPLETE">COMPLETE</option>
                </select>
              )}
            </td>
          </tr>
          <tr>
            <th>답변내용</th>
            <td>
              {type === "read" ? (
                data.inquiryAnswer
              ) : (
                <textarea value={answer} onChange={handleAnswerChange} />
              )}
            </td>
          </tr>
        </tbody>
      </table>
      {type === "edit" && (
        <button
          onClick={() => {
            onSubmit({ inquiryStatusType, answer });
          }}
        >
          등록
        </button>
      )}
    </div>
  );
};

export default RequestAnswer;
