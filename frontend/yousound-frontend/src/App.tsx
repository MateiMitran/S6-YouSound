import Sidebar from "./components/sidebar/Sidebar";
import Playbar from "./components/playbar/Playbar";
import FriendsBar from "./components/friendsbar/FriendsBar";
import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { Home } from "./pages/home/Home";
import { LoginPage } from "./pages/login/LoginPage";
import { useState, useEffect } from "react";

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
