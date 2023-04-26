import Sidebar from "./components/sidebar/Sidebar";
import Playbar from "./components/playbar/Playbar";
import FriendsBar from "./components/friendsbar/FriendsBar";
import "./App.css";
import { dataSongs1, dataSongs2 } from "./components/friendsbar/data";
import { ContentCard } from "./components/cards/ContentCard";
import { Stack } from "@mui/material";

function App() {
  return (
    <div className="App">
      <Sidebar />
      <div className="content">
        <h2>Recently Played</h2>
        <Stack
          direction="row"
          spacing={4}
          sx={{ width: "100%", margin: "0 auto" }}
        >
          {dataSongs1.map((song) => {
            return (
              <ContentCard
                key={`${song.name}_id`}
                name={song.name}
                artist={song.artist}
                image={song.image}
              />
            );
          })}
        </Stack>
        <h2>Discover</h2>
        <Stack
          direction="row"
          spacing={4}
          sx={{ width: "100%", margin: "0 auto" }}
        >
          {dataSongs2.map((song) => {
            return (
              <ContentCard
                key={`${song.name}_id`}
                name={song.name}
                artist={song.artist}
                image={song.image}
              />
            );
          })}
        </Stack>
      </div>
      <Playbar />
      <FriendsBar />
    </div>
  );
}

export default App;
