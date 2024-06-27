import React, { useEffect, useState } from "react";

import "./style/MyCropTimeline.css";
import Pagination from "../common/Pagination";
import Button from "../common/Button";

const MyCropTimeline = ({ timeline, onMovePage, onDeleteTimeline }) => {
  const [nowPage, setNowPage] = useState(0);

  useEffect(() => {
    onMovePage(nowPage);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [nowPage]);

  return (
    <div>
      <div className="timeline-container">
        <h2 className="timeline-title">작물 관리 타임라인</h2>
        <ul className="timeline-list">
          {timeline.length > 0 &&
            timeline.map((item, index) => (
              <li key={index} className="timeline-item">
                <div className="timeline-item-content">
                  <div className="timeline-item-time">
                    <i className="fas fa-calendar-alt"></i>
                    <span>{item.date.slice(0, 10)}</span>
                  </div>
                  <div className="timeline-item-type">
                    <span>{item.manage}</span>
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
        <Pagination totalPage={5} nowPage={nowPage} onClick={setNowPage} />
      </div>
    </div>
  );
};

export default MyCropTimeline;
