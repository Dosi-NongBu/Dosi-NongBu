import React from "react";
import { Link } from "react-router-dom";

const SideBar = ({ onChange }) => {
  return (
    <div>
      {" "}
      <div className="Sidebar">
        <h2>메뉴</h2>
        <ul>
          <li>
            <h3>사용자 정보</h3>
            <ul>
              <li>
                <button
                  onClick={() => {
                    onChange(1);
                  }}
                >
                  사용자 정보 목록 조회
                </button>
              </li>
            </ul>
          </li>
          <li>
            <h3>식물 관리</h3>
            <ul>
              <li>
                <button
                  onClick={() => {
                    onChange(2);
                  }}
                >
                  신규 식물 등록
                </button>
              </li>
            </ul>
          </li>
          <li>
            <h3>1:1 문의</h3>
            <ul>
              <li>
                <button
                  onClick={() => {
                    onChange(3);
                  }}
                >
                  1:1 문의
                </button>
              </li>
            </ul>
          </li>
          <li>
            <h3>공지사항</h3>
            <ul>
              <li>
                <button
                  onClick={() => {
                    onChange(4);
                  }}
                >
                  공지사항 등록
                </button>
              </li>
            </ul>
          </li>
          <li>
            <h3>FAQ</h3>
            <ul>
              <li>
                <button
                  onClick={() => {
                    onChange(5);
                  }}
                >
                  FAQ 등록
                </button>
              </li>
            </ul>
          </li>
        </ul>
      </div>
    </div>
  );
};

export default SideBar;
