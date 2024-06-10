// NotFound.js
import React from "react";
import { Link } from "react-router-dom";

const NotFound = () => {
  return (
    <div style={styles.container}>
      <h1 style={styles.header}>404 - 페이지를 찾을 수 없습니다</h1>
      <p style={styles.paragraph}>
        요청하신 페이지를 찾을 수 없습니다. 주소를 확인해주세요.
      </p>
      <Link to="/" style={styles.link}>
        홈으로 돌아가기
      </Link>
    </div>
  );
};

const styles = {
  container: {
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
    justifyContent: "center",
    height: "100vh",
    textAlign: "center",
  },
  header: {
    fontSize: "2em",
    marginBottom: "20px",
  },
  paragraph: {
    fontSize: "1.2em",
    marginBottom: "20px",
  },
  link: {
    fontSize: "1em",
    color: "blue",
    textDecoration: "underline",
  },
};

export default NotFound;
