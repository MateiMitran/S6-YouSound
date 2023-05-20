import Sidebar from "./components/sidebar/Sidebar";
import Playbar from "./components/playbar/Playbar";
import { FriendsBar } from "./components/friendsbar/FriendsBar";
import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { Home } from "./pages/user/home/Home";
import { LoginPage } from "./pages/user/login/LoginPage";
import { useState, useEffect } from "react";
import { SignupPage } from "./pages/user/signup/SignupPage";
import { SearchPage } from "./pages/user/search/SearchPage";
import { DashboardPage } from "./pages/admin/DashboardPage";
import { ProfilePage } from "./pages/user/profile/ProfilePage";
import { InboxPage } from "./pages/user/inbox/InboxPage";
import { CommunitiesPage } from "./pages/user/communities/CommunitiesPage";
import { CommunityPage } from "./pages/user/communities/CommunityPage";
import { ArtistHome } from "./pages/artist/artisthome/ArtistHome";
import SidebarArtist from "./components/sidebar/SidebarArtist";

function App() {
  const [role, setRole] = useState<string | null>(localStorage.getItem("role"));
  const [isAuthenticated, setIsAuthenticated] = useState<boolean>(!!role);

  useEffect(() => {
    const updatedRole = localStorage.getItem("role");
    setRole(updatedRole);
    setIsAuthenticated(!!updatedRole);
  }, []);

  useEffect(() => {
    const timer = setInterval(() => {
      const updatedRole = localStorage.getItem("role");
      if (role !== updatedRole) {
        setRole(updatedRole);
        setIsAuthenticated(!!updatedRole);
      }
    }, 1000); // Check every second

    return () => clearInterval(timer); // Cleanup on component unmount
  }, [role]);

  return (
    <>
      {isAuthenticated && role && role === "ROLE_USER" && <Playbar />}
      <div className="App">
        {role && role === "ROLE_USER" && <Sidebar />}
        {isAuthenticated && role && role === "ROLE_ARTIST" && <SidebarArtist />}
        <div className="content">
          <BrowserRouter>
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/login" element={<LoginPage />} />
              <Route path="/signup" element={<SignupPage />} />
              <Route path="/search" element={<SearchPage />} />
              <Route path="/dashboard" element={<DashboardPage />} />
              <Route path="/profile" element={<ProfilePage />} />
              <Route path="/inbox" element={<InboxPage />} />
              <Route path="/communities" element={<CommunitiesPage />} />
              <Route
                path="/communities/:communityid"
                element={<CommunityPage />}
              />
              <Route path="/artisthome" element={<ArtistHome />} />
            </Routes>
          </BrowserRouter>
        </div>
      </div>
      {role && role === "ROLE_USER" && <FriendsBar />}
    </>
  );
}

export default App;
