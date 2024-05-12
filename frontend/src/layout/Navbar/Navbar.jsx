import React, { useEffect, useState } from "react";

import { Link, useNavigate } from "react-router-dom";
import { AiOutlineBell } from "react-icons/ai";
import { CiMenuBurger } from "react-icons/ci";
import { useDispatch, useSelector } from "react-redux";

import { logoutUser } from "../../store/thunkFunctions";

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
  { to: "/register", name: "회원가입", isMore: false },
];

const loggedIn = [{ to: "/myPage", name: "마이페이지" }];

const Navbar = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const isAuth = useSelector((state) => state.user?.isAuth);
  const [bell, setBell] = useState(5);
  const [menu, setMenu] = useState(false);

  const handleToggleMenu = () => {
    setMenu(!menu);
  };

  const handleBellClick = () => {
    setBell(0);
    navigate("/myPage");
  };

  const handleLogout = () => {
    const body = {
      title: "none",
      content: "none",
    };
    alert("로그아웃 되었습니다.");
    dispatch(logoutUser(body));
  };

  return (
    <section className="navbar">
      <div className="navbar-container">
        <div className="navItem">
          <div className="title">도시농부</div>

          <button className="mobile-menu-button" onClick={handleToggleMenu}>
            <CiMenuBurger />
          </button>

          <div className={`navItem-container ${menu ? "mobile-menu-on" : ""}`}>
            <div className="mobile">
              <>
                <Link to={"/myCrop"}>내 텃밭 관리</Link>
                <Link to={"/searchCrop"}>새 텃밭 생성</Link>
                <Link to={"/community"}>자유게시판</Link>
                <Link to={"/questionCommunity"}>질문게시판</Link>
                <Link to={"/faq"}>FAQ</Link>
                <Link to={"/request"}>1:1 문의</Link>
                <Link to={"/notine"}>공지사항</Link>
              </>
              {isAuth && (
                <>
                  <Link to={"/myPage"}>마이페이지</Link>
                  <Link to={"/"} onClick={handleLogout}>
                    로그아웃
                  </Link>
                  <div className="alarm-bell" onClick={handleBellClick}>
                    <AiOutlineBell />
                    <span className="bell">{bell}</span>
                  </div>
                </>
              )}
              {!isAuth && (
                <>
                  <Link to={"/login"}>로그인</Link>
                  <Link to={"/register"}>회원가입</Link>
                </>
              )}
            </div>
          </div>

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
                <div className="each-navItem">
                  <Link to={"/"} className="menu-name" onClick={handleLogout}>
                    {"로그아웃"}
                  </Link>
                </div>
              }
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
