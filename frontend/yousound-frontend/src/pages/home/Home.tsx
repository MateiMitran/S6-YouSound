import React, { useEffect } from "react";
import { Box, Stack } from "@mui/material";
import { dataSongs1, dataSongs2 } from "../../components/friendsbar/data";
import { ContentCard } from "../../components/cards/ContentCard";

export const Home: React.FC = () => {

  useEffect(() => {
    window.localStorage.setItem("token", "test");
  }, []);

  return (
    <Box sx={{mt: "3%"}}>
      <h2>Recently Played</h2>
      <Stack
        direction="row"
        spacing={5}
        sx={{
          width: "100%",
          margin: "0 auto",
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
        }}
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
    </Box>
  );
};
