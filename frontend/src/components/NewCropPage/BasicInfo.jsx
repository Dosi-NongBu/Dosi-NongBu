import React from "react";

import { BsJournalCheck } from "react-icons/bs";
import "./style/BasicInfo.css";

const EachInfo = ({ content }) => {
  return (
    <div className="eachInfo">
      <BsJournalCheck size={30} />
      <div className="eachInfo-content">{content}</div>
    </div>
  );
};

const BasicInfo = ({ data }) => {
  return (
    <section className="basicInfo">
      <EachInfo content={data.classification} />
      <EachInfo content={data.origin} />
      <EachInfo content={data.feature} />
    </section>
  );
};

export default BasicInfo;
