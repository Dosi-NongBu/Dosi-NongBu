import { useState, useEffect } from "react";

function useIsAuth() {
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  useEffect(() => {
    const accessToken = localStorage.getItem("accessToken");
    setIsAuthenticated(!!accessToken);
  }, []);

  return isAuthenticated;
}

export default useIsAuth;
