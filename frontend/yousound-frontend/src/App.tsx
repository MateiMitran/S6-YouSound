import Sidebar from "./components/sidebar/Sidebar";
import Playbar from "./components/playbar/Playbar";
import FriendsBar from "./components/friendsbar/FriendsBar";
import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { Home } from "./pages/home/Home";
import { LoginPage } from "./pages/login/LoginPage";
import { useState, useEffect } from "react";
import { SignupPage } from "./pages/signup/SignupPage";
import { VerifyPage } from "./pages/verify/VerifyPage";

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
          </Routes>
        </BrowserRouter>
      </div>
      {hasToken && (
        <>
          <Playbar />
          <FriendsBar />
        </>
      )}
    </div>
  );
}

export default App;
