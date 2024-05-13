import React, { useState } from "react";

import "./style/InputBox.css";

const SelectBox = ({ content, onClick, isSelected }) => {
  return (
    <div
      className={`each-select ${isSelected ? "each-select-selected" : ""}`}
      onClick={onClick}
    >
      {content}
    </div>
  );
};

const InputBox = ({ type, title, select, onChange, onSelect }) => {
  const [selected, setSelected] = useState(0);
  const handleSelectButtonClick = (id) => {
    setSelected(id);
    onSelect(select[id - 1]);
  };

  return (
    <div className="input-container">
      <div className="title">{title}</div>
      {type === "text" && (
        <>
          <input type="text" onChange={onChange} className="each-text" />
        </>
      )}

      {type === "select" && (
        <>
          <div className="select-container">
            {select.map((item, index) => (
              <SelectBox
                key={index}
                content={item}
                onClick={() => {
                  handleSelectButtonClick(index + 1);
                }}
                isSelected={index + 1 === selected}
              />
            ))}
          </div>
        </>
      )}
    </div>
  );
};

export default InputBox;
