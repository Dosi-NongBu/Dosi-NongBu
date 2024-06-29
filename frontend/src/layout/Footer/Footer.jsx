import React from "react";
import { useNavigate } from "react-router-dom";
const Footer = () => {
  const nav = useNavigate();
  return (
    <div style={{ width: "100%", textAlign: "center" }}>
      CopyRight © Min All rights Reserved.
      <button
        style={{ backgroundColor: "white", border: "none" }}
        onClick={() => {
          nav("/admin");
        }}
      >
        ad
      </button>
    </div>
  );
};

export default Footer;
