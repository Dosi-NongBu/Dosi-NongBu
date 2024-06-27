// NotFound.js
import React from "react";
import { Link } from "react-router-dom";
import Lottie from "lottie-react";
import Error404 from "../assets/lottie/Error404.json";

const NotFound = () => {
  return (
    <div style={styles.container}>
      <Lottie animationData={Error404} />
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
