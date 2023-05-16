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
import { CommunitiesPage } from "./pages/user/communities/CommunitiesPage";
import { CommunityPage } from "./pages/user/communities/CommunityPage";
import { ArtistHome } from "./pages/artist/artisthome/ArtistHome";
import SidebarArtist from "./components/sidebar/SidebarArtist";

function App() {
  const [hasToken, setHasToken] = useState(false);
  const [isArtist, setisArtist] = useState(false);

  useEffect(() => {
    const token = localStorage.getItem("token");
    if (token === "artist") {
      setisArtist(true);
    } else {
      if (token) {
        setHasToken(true);
      }
    }
  }, []);
  return (
    <>
      {!isArtist && <Playbar />}

      <div className="App">
        {hasToken && !isArtist && <Sidebar />}
        {isArtist && <SidebarArtist />}
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
      {!isArtist && <FriendsBar />}
    </>
  );
}

export default App;
