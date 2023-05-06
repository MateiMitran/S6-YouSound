import Sidebar from "./components/sidebar/Sidebar";
import Playbar from "./components/playbar/Playbar";
import { FriendsBar } from "./components/friendsbar/FriendsBar";
import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { Home } from "./pages/user/home/Home";
import { LoginPage } from "./pages/user/login/LoginPage";
import { useState, useEffect } from "react";
import { SignupPage } from "./pages/user/signup/SignupPage";
import { VerifyPage } from "./pages/user/verify/VerifyPage";
import { SearchPage } from "./pages/user/search/SearchPage";
import { DashboardPage } from "./pages/admin/DashboardPage";
import { ProfilePage } from "./pages/user/profile/ProfilePage";
import { InboxPage } from "./pages/user/inbox/InboxPage";

function App() {
  const [hasToken, setHasToken] = useState(false);

  useEffect(() => {
    const token = localStorage.getItem("token");
    if (token) {
      setHasToken(true);
    }
  }, []);
  return (
    <div className="App">
      {hasToken && <Sidebar />}
      <div className="content">
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/login" element={<LoginPage />} />
            <Route path="/signup" element={<SignupPage />} />
            <Route path="/verify/:token" element={<VerifyPage />} />
            <Route path="/search" element={<SearchPage />} />
            <Route path="/dashboard" element={<DashboardPage />} />
            <Route path="/profile" element={<ProfilePage />} />
            <Route path="/inbox" element={<InboxPage />} />
          </Routes>
        </BrowserRouter>
      </div>
      {hasToken && window.location.pathname !== "/profile" && (
        <>
          <Playbar />
          <FriendsBar />
        </>
      )}
    </div>
  );
}

export default App;
