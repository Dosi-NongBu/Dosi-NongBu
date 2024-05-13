import React from "react";

import "./style/Button.css";

const Button = ({ title, type, onClick }) => {
  const padding = Math.max(10, title.length) + "px";

  const style = {
    padding: `0 ${padding}`,
  };

  return (
    <button className={`button ${type}`} onClick={onClick} style={style}>
      {title}
    </button>
  );
};

export default Button;
