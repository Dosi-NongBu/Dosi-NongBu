import "./App.css";
import { Routes, Route, Outlet } from "react-router-dom";
import { useSelector } from "react-redux";

import Navbar from "./layout/Navbar/Navbar";
import Footer from "./layout/Footer/Footer";

import LandingPage from "./pages/LandingPage/LandingPage";
import LoginPage from "./pages/LandingPage/LoginPage";
import RegisterPage from "./pages/LandingPage/RegisterPage";
import NotAuthRoutes from "./layout/NotAuthRoutes";
import ProtectedRoutes from "./layout/ProtectedRoutes";
import SearchCropPage from "./pages/NewCropPage/SearchCropPage";
import CropDetailPage from "./pages/NewCropPage/CropDetailPage";
import MyPage from "./pages/MyPage/MyPage";
import MyCropPage from "./pages/MyCropPage/MyCropPage";
import MyCropDetailPage from "./pages/MyCropPage/MyCropDetailPage";
// import CommunityPage from "./pages/CommunityPage/CommunityPage";
// import QuestionCommunityPage from "./pages/CommunityPage/QuestionCommunityPage";
// import FAQPage from "./pages/OfficialPage/FAQPage";
// import NoticePage from "./pages/OfficialPage/NoticePage";
// import RequestPage from "./pages/OfficialPage/RequestPage";

function Layout() {
  return (
    <div className="layout">
      <Navbar />
      <main className="main">
        <Outlet />
      </main>
      <Footer />
    </div>
  );
}

function App() {
  const isAuth = useSelector((state) => state.user?.isAuth);
  // const isAuth = true;
  return (
    <>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<LandingPage />} />

          {/* 공통 */}
          <Route path="/searchCrop" element={<SearchCropPage />} />
          <Route path="/searchCrop/:cropId" element={<CropDetailPage />} />
          {/* <Route path="/community" element={<CommunityPage />} />
          <Route
            path="/questionCommunity"
            element={<QuestionCommunityPage />}
          /> */}
          {/* <Route path="/faq" element={<FAQPage />} />
          <Route path="/notice" element={<NoticePage />} />
          <Route path="/request" element={<RequestPage />} /> */}

          {/* 로그인한 사람은 갈 수 없는 경로 */}
          <Route element={<NotAuthRoutes isAuth={isAuth} />}>
            <Route path="/login" element={<LoginPage />} />
            <Route path="/register" element={<RegisterPage />} />
          </Route>

          {/* 로그인한 사람만 갈 수 있는 경로 */}
          <Route element={<ProtectedRoutes isAuth={isAuth} />}>
            <Route path="/myPage" element={<MyPage />} />
            <Route path="/myCrop" element={<MyCropPage />} />
            <Route path="/myCrop/:cropId" element={<MyCropDetailPage />} />
          </Route>
        </Route>
      </Routes>
    </>
  );
}

export default App;
