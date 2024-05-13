import React, { useEffect, useState } from "react";

import "./style/Pagination.css";
const Pagination = ({ totalPage, nowPage, onClick }) => {
  const [page, setPage] = useState(nowPage);

  return (
    <div className="pagination">
      <div className="number-conatiner">
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
            className={`pagination-button ${
              index + 1 === nowPage ? "active" : ""
            }`}
            key={index}
            onClick={() => {
              onClick(index + 1);
              setPage(index + 1);
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
  );
};

export default Pagination;
