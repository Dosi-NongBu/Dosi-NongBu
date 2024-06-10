import React, { useState } from "react";
import "./style/Select.css";

const Select = ({ onSelectChange }) => {
  // 선택된 항목을 저장할 상태
  const [selected, setSelected] = useState("");

  // 드롭다운에서 항목이 선택될 때 호출될 함수
  const handleChange = (event) => {
    setSelected(event.target.value);
    onSelectChange(event.target.value);
  };

  return (
    <div className="select-container">
      <select value={selected} onChange={handleChange} className="select-box">
        <option value="">선택해주세요</option>
        <option value="GENERAL_INQUIRY">일반 문의</option>
        <option value="ACCOUNT_ISSUE">계정 문의</option>
        <option value="TECHNICAL_SUPPORT">기술 지원</option>
        <option value="BILLING_ISSUE">결제 문의 </option>
        <option value="FEEDBACK_SUGGESTION">피드백 및 제안</option>
      </select>
    </div>
  );
};

export default Select;
