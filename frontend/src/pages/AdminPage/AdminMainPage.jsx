import React, { useState } from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";

import SideBar from "./SideBar";

import "./style/AdminPage.css";
import UserInfo from "./UserInfo";
import PlantManagement from "./PlantManagement";
import Inquiry from "./Inquiry";
import Notice from "./Notice";
import FaQ from "./FaQ";

const AdminMainPage = () => {
  const [menu, setMenu] = useState(1);

  const handleMenuButton = (number) => {
    setMenu(number);
  };

  return (
    <div className="admin-area">
      <SideBar onChange={handleMenuButton} />

      {menu === 1 && <UserInfo />}
      {menu === 2 && <PlantManagement />}
      {menu === 3 && <Inquiry />}
      {/* {menu === 4 && <Inquiry />} */}
      {menu === 4 && <Notice />}
      {/* {menu === 6 && <Notice />} */}
      {menu === 5 && <FaQ />}
      {/* {menu === 8 && <FaQ />} */}
    </div>
  );
};

export default AdminMainPage;
