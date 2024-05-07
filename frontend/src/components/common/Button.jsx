import React from "react";

import "./style/Button.css";

const Button = ({ title, type, onClick }) => {
  return (
    // <div className="button">
    <button className="button" onClick={onClick}>
      {title}
    </button>
    // </div>
  );
};

export default Button;
