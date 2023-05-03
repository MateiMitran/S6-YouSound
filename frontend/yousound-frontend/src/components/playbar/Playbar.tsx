import React, { useState, useEffect } from "react";
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
import Losingit from "../../assets/losingit.jpg";
import VolumeDown from "@mui/icons-material/VolumeDown";
import VolumeUp from "@mui/icons-material/VolumeUp";
import losingit from "../../assets/losingitaudio.mp3";
import { useDispatch } from "react-redux";

// interface PlaybarProps {
//   songImageUrl: string;
//   artistName: string;
//   songTitle: string;
//   duration: number;
//   currentTime: number;
// }
const useInitializeAudio = () => {
  const dispatch = useDispatch();
  const newAudio = new Audio(Losingit);
  newAudio.addEventListener("timeupdate", () => {
    dispatch({ type: "SET_CURRENT_TIME", payload: newAudio.currentTime });
  });
  newAudio.addEventListener("loadedmetadata", () => {
    dispatch({ type: "SET_DURATION", payload: newAudio.duration });
  });
  dispatch({ type: "SET_AUDIO", payload: newAudio });
};

const Playbar: React.FC = () => {
  const [isLiked, setIsLiked] = useState(false);
  const [isShuffle, setIsShuffle] = useState(false);
  const [repeatCount, setRepeatCount] = useState<number>(0);
  const [audio, setAudio] = useState<HTMLAudioElement | null>(null);
  const [currentTime, setCurrentTime] = useState(0);
  const [duration, setDuration] = useState(30);
  const [volume, setVolume] = useState(0.5);

  // useEffect(() => {
  //   useInitializeAudio();
  // }, []);

  const incrementRepeat = () => {
    if (repeatCount === 2) {
      setRepeatCount(0);
    } else {
      setRepeatCount(repeatCount + 1);
    }
  };

  const formatTime = (seconds: number): string => {
    const hours = Math.floor(seconds / 3600);
    const minutes = Math.floor((seconds - hours * 3600) / 60);
    const remainingSeconds = Math.floor(seconds - hours * 3600 - minutes * 60);

    const formattedHours = hours.toString().padStart(2, "0");
    const formattedMinutes = minutes.toString().padStart(2, "0");
    const formattedSeconds = remainingSeconds.toString().padStart(2, "0");

    if (hours > 0) {
      return `${formattedHours}:${formattedMinutes}:${formattedSeconds}`;
    } else {
      return `${formattedMinutes}:${formattedSeconds}`;
    }
  };

  const handlePlay = () => {
    if (audio) {
      audio.volume = volume;
      if (audio.paused) {
        audio.play();
      } else {
        audio.pause();
      }
    } else {
      // useInitializeAudio();
    }
  };

  const handleVolumeChange = (event: any, newValue: number | number[]) => {
    const newVolume = typeof newValue === "number" ? newValue : newValue[0];
    setVolume(newVolume);
    if (audio) {
      audio.volume = newVolume;
    }
  };

  const handlePlaySliderChange = (event: any, newValue: number | number[]) => {
    const newTime = typeof newValue === "number" ? newValue : newValue[0];
    if (audio) {
      audio.currentTime = newTime;
    }
  };

  const handlePlaySliderChangeCommitted = (
    event: any,
    newValue: number | number[]
  ) => {
    const newTime = typeof newValue === "number" ? newValue : newValue[0];
    if (audio) {
      audio.currentTime = newTime;
      if (!audio.paused) {
        audio.play();
      }
    }
  };

  const handleAudioTimeUpdate = () => {
    if (audio) {
      const newTime = audio.currentTime;
      if (Math.abs(newTime - currentTime) >= 0.1) {
        setCurrentTime(newTime);
      }
    }
  };

  if (audio) {
    audio.addEventListener("timeupdate", handleAudioTimeUpdate);
  }
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
        <IconButton onClick={handlePlay} sx={{ color: "inherit" }}>
          {audio && !audio.paused ? <Pause /> : <PlayArrow />}
        </IconButton>
        <IconButton sx={{ color: "inherit" }}>
          <SkipNext />
        </IconButton>
        <Typography sx={{ marginRight: "1rem" }}>
          {formatTime(currentTime)}
        </Typography>
        <Slider
          value={currentTime}
          min={0}
          max={duration}
          step={0.01}
          onChange={handlePlaySliderChange}
          onChangeCommitted={handlePlaySliderChangeCommitted}
          aria-labelledby="playback-slider"
          sx={{ mr: "10px", color: "inherit", width: 500 }}
        />
        <Typography sx={{ ml: "1rem" }}>
          {formatTime(duration - currentTime)}
        </Typography>
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
        <Box sx={{ width: 200, mt: "100px" }}>
          <Stack spacing={2} direction="row" alignItems="center">
            <VolumeDown />
            <Slider
              aria-label="Volume"
              value={volume}
              min={0}
              max={1}
              step={0.01}
              onChange={handleVolumeChange}
              sx={{ color: "#4745A0" }}
              aria-labelledby="volume-slider"
            />
            <VolumeUp />
          </Stack>
        </Box>
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
