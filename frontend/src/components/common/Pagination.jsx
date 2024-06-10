import React, { useEffect, useState } from "react";

import "./style/Pagination.css";
const Pagination = ({ totalPage, nowPage, onClick }) => {
  const [page, setPage] = useState(nowPage);

  return (
    <div className="pagination">
      <div className="number-conatiner">
        <div className="number-button">
          <button
            className="pagination-button"
            onClick={() => {
              if (page > 1) {
                onClick(page - 1);
                setPage(page - 1);
              }
            }}
          >
            {"<"}
          </button>
          {Array.from({ length: totalPage }, (_, index) => (
            <button
              className={`pagination-button ${index === page ? "active" : ""}`}
              key={index}
              onClick={() => {
                onClick(index);
                setPage(index);
              }}
            >
              {index + 1}
            </button>
          ))}
          <button
            className="pagination-button"
            onClick={() => {
              if (page < totalPage) {
                onClick(page + 1);
                setPage(page + 1);
              }
            }}
          >
            {">"}
          </button>
        </div>
      </div>
    </div>
  );
};

export default Pagination;
