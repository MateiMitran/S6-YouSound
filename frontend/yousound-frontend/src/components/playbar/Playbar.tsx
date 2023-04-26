import React, { useState } from "react";
import { Box, IconButton, Slider, Typography, Stack } from "@mui/material";
import {
  SkipPrevious,
  PlayArrow,
  Pause,
  SkipNext,
  FavoriteBorder,
  Shuffle,
  Repeat,
  QueueMusic,
  ShuffleTwoTone,
  FavoriteTwoTone,
  RepeatTwoTone,
  RepeatOneTwoTone,
} from "@mui/icons-material";
import VolumeSlider from "./VolumeSlider";
import Losingit from "../../assets/losingit.jpg";

// interface PlaybarProps {
//   songImageUrl: string;
//   artistName: string;
//   songTitle: string;
//   duration: number;
//   currentTime: number;
// }

const Playbar: React.FC = () => {
  const [isPlaying, setIsPlaying] = useState(false);
  const [isLiked, setIsLiked] = useState(false);
  const [isShuffle, setIsShuffle] = useState(false);
  const [repeatCount, setRepeatCount] = useState<number>(0);

  const incrementRepeat = () => {
    if (repeatCount === 2) {
      setRepeatCount(0);
    } else {
      setRepeatCount(repeatCount + 1);
    }
  };

  return (
    <Box
      sx={{
        display: "flex",
        justifyContent: "space-between",
        alignItems: "center",
        backgroundColor: "#2B2929",
        padding: "1rem",
        position: "fixed",
        left: "272px",
        bottom: 0,
        right: 0,
        zIndex: 1000,
      }}
    >
      <Stack
        direction="row"
        alignItems="center"
        spacing={1}
        sx={{ color: "#929292", margin: "0 auto" }}
      >
        <IconButton sx={{ color: "inherit" }}>
          <SkipPrevious />
        </IconButton>
        <IconButton
          onClick={() => setIsPlaying(!isPlaying)}
          sx={{ color: "inherit" }}
        >
          {isPlaying ? <Pause /> : <PlayArrow />}
        </IconButton>
        <IconButton sx={{ color: "inherit" }}>
          <SkipNext />
        </IconButton>
        <Typography>1:34</Typography>
        <Slider value={14} max={20} sx={{ color: "inherit", width: 500 }} />
        <Typography>2:34</Typography>
        <IconButton
          onClick={() => setIsLiked(!isLiked)}
          sx={{ color: "inherit" }}
        >
          {isLiked ? (
            <FavoriteTwoTone sx={{ color: "#4745A0" }} />
          ) : (
            <FavoriteBorder />
          )}
        </IconButton>
        <IconButton
          onClick={() => setIsShuffle(!isShuffle)}
          sx={{ color: "inherit" }}
        >
          {isShuffle ? (
            <ShuffleTwoTone sx={{ color: "#4745A0" }} />
          ) : (
            <Shuffle />
          )}
        </IconButton>
        <IconButton onClick={() => incrementRepeat()} sx={{ color: "inherit" }}>
          {repeatCount === 0 && <Repeat />}
          {repeatCount === 1 && <RepeatTwoTone sx={{ color: "#4745A0" }} />}
          {repeatCount === 2 && <RepeatOneTwoTone sx={{ color: "#4745A0" }} />}
        </IconButton>
        <IconButton sx={{ color: "inherit" }}>
          <QueueMusic />
        </IconButton>
        <VolumeSlider />
        <Stack direction="row" alignItems="center" spacing={1}>
          <img
            src={Losingit}
            alt="Song"
            style={{
              width: "50px",
              height: "50px",
              objectFit: "cover",
              borderRadius: "4px",
              marginLeft: "1rem",
            }}
          />
          <Box>
            <Typography sx={{ color: "#B8B7B7" }}>Fisher</Typography>
            <Typography variant="subtitle2">Losing it</Typography>
          </Box>
        </Stack>
      </Stack>
    </Box>
  );
};

export default Playbar;
