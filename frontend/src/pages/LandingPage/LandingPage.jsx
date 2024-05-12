import React, { useEffect } from "react";
import axios from "axios";

const LandingPage = () => {
  useEffect(() => {
    fetch("http://localhost:8080/login/oauth2/code/google")
      .then((response) => {
        const value = response.headers;
        console.log(value);
      })
      .catch((error) => console.log("Error:", error));
  }, []);
  return <div>LandingPage</div>;
};

export default LandingPage;
