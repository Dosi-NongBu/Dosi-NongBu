import React from "react";
import "./style/Status.css";

const Status = ({ type }) => {
  return (
    <div>
      {type === "NO" && <div className="status status-no">답변 전</div>}
      {type === "ANSWERING" && (
        <div className="status status-answering">답변 중</div>
      )}
      {type === "COMPLETE" && (
        <div className="status status-complete">답변 완료</div>
      )}
    </div>
  );
};

export default Status;
