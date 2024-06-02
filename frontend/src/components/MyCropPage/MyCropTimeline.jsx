import React, { useEffect, useState } from "react";

import "./style/MyCropTimeline.css";
import Pagination from "../common/Pagination";
import Button from "../common/Button";

const MyCropTimeline = ({ timeline, onDeleteTimeline }) => {
  const [nowPage, setNowPage] = useState(1);

  useEffect(() => {}, [nowPage]);

  const handleNewPage = (newPage) => {
    setNowPage(newPage);
  };

  return (
    <div>
      <div className="timeline-container">
        <h2 className="timeline-title">작물 관리 타임라인</h2>
        <ul className="timeline-list">
          {timeline.map((item, index) => (
            <li key={index} className="timeline-item">
              <div className="timeline-item-content">
                <div className="timeline-item-time">
                  <i className="fas fa-calendar-alt"></i>
                  <span>{item.managedDate}</span>
                </div>
                <div className="timeline-item-type">
                  <span>{item.cropManageType}</span>
                </div>
              </div>
              <Button
                title={"X"}
                onClick={() => {
                  onDeleteTimeline(item.cropLogId);
                }}
                type={"negative"}
              />
            </li>
          ))}
        </ul>
        <Pagination totalPage={5} nowPage={nowPage} onClick={handleNewPage} />
      </div>
    </div>
  );
};

export default MyCropTimeline;
