import React, { useEffect, useState } from "react";
import { Box, Grid } from "@mui/material";
import { ContentCard } from "../../../components/cards/ContentCard";
import { SongEntity } from "../../../types";
import { MusicService } from "../../../services/MusicService";
import { useDispatch, useSelector } from "react-redux";
import {
  setCurrentSong,
  toggleIsPlaying,
} from "../../../store/slices/playerSlice";
import { RootState } from "../../../store";

export const Home: React.FC = () => {
  const dispatch = useDispatch();
  const [songs, setSongs] = useState<SongEntity[]>([]);
  const { isPlaying } = useSelector((state: RootState) => state.player);
  useEffect(() => {
    window.localStorage.setItem("token", "test");
    const fetchSongs = async () => {
      const response = await MusicService.getAllSongs();
      setSongs(response);
    };
    fetchSongs();
  }, []);

  const handleSongClick = (song: SongEntity) => {
    console.log(isPlaying);
    if (isPlaying) {
      dispatch(setCurrentSong(song));
      dispatch(toggleIsPlaying());
    } else {
      dispatch(setCurrentSong(song));
      dispatch(toggleIsPlaying());
    }
  };

  return (
    <Box sx={{ mt: "3%", ml: "260px", mr: "280px" }}>
      <h2>Recently Played</h2>
      <Grid
        container
        spacing={2}
        sx={{
          display: "flex",
          justifyContent: "center",
          flexWrap: "wrap",
        }}
      >
        {songs.map((song) => {
          return (
            <Grid item xs={3} key={`${song.name}_id`}>
              <ContentCard
                name={song.name}
                artist_id={song.artist_id}
                picture={song.picture}
                file={song.file}
                onClick={() => handleSongClick(song)}
              />
            </Grid>
          );
        })}
      </Grid>
    </Box>
  );
};
