import React, { useState } from "react";

import { Link, useNavigate } from "react-router-dom";
import { AiOutlineBell } from "react-icons/ai";

import "./style/Navbar.css";

const common = [
  {
    to: "/myCrop",
    name: "텃밭 관리",
    isMore: true,
    lowerMenu: [
      { to: "/myCrop", name: "내 텃밭 관리" },
      { to: "/searchCrop", name: "새 텃밭 생성" },
    ],
  },
  {
    to: "/community",
    name: "커뮤니티",
    isMore: true,
    lowerMenu: [
      { to: "/community", name: "자유게시판" },
      { to: "/questionCommunity", name: "질문게시판" },
    ],
  },
  {
    to: "/notice",
    name: "문의",
    isMore: true,
    lowerMenu: [
      { to: "/faq", name: "FAQ" },
      { to: "/request", name: "1:1 문의" },
      { to: "/notice", name: "공지사항" },
    ],
  },
];

const notLoggedIn = [
  { to: "/login", name: "로그인", isMore: false },
  { to: "/login", name: "회원가입", isMore: false },
];

const loggedIn = [{ to: "/myPage", name: "마이페이지" }];

const Navbar = () => {
  const navigate = useNavigate();
  const [isAuth, setIsAuth] = useState(false);
  const [bell, setBell] = useState(5);

  const handleBellClick = () => {
    setBell(0);
    navigate("/myPage");
  };

  return (
    <section className="navbar">
      <div className="navbar-container">
        <div className="navItem">
          <div className="title">도시농부</div>
          <div className="each-navItem">
            {common.map(({ to, name, isMore, lowerMenu }) => (
              <div key={name} className="menu-container">
                <Link to={to} className="menu-name">
                  {name}
                </Link>
                <div className="lower-menu">
                  {isMore &&
                    lowerMenu.map(({ to, name }) => (
                      <div key={name} className="lower-menu-item">
                        <Link to={to} className="lower-menu-name">
                          {name}
                        </Link>
                      </div>
                    ))}
                </div>
              </div>
            ))}
          </div>

          {!isAuth && (
            <div className="each-navItem2">
              {notLoggedIn.map(({ to, name }) => (
                <div key={name}>
                  <Link to={to} className="menu-name">
                    {name}
                  </Link>
                </div>
              ))}{" "}
            </div>
          )}

          {isAuth && (
            <div className="each-navItem">
              {loggedIn.map(({ to, name }) => (
                <div key={name}>
                  <Link to={to} className="menu-name">
                    {name}
                  </Link>
                </div>
              ))}
              {
                <div className="alarm-bell" onClick={handleBellClick}>
                  <AiOutlineBell />
                  <span className="bell">{bell}</span>
                </div>
              }
            </div>
          )}
        </div>
      </div>
    </section>
  );
};

export default Navbar;
