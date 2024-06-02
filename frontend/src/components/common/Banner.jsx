import React from "react";

import "./style/Banner.css";

const Banner = ({ title, subTitle }) => {
  return (
    <div className="header-container">
      <h1>{title}</h1>
      <h3>{subTitle}</h3>
    </div>
  );
};

export default Banner;
