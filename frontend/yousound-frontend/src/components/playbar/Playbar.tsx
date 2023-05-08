import React, { ChangeEvent, useState, useEffect, useRef } from "react";
import { useSelector, useDispatch } from "react-redux";
import { RootState } from "../../store";
import {
  toggleIsPlaying,
  setVolume,
  setDuration,
  setCurrentTime,
} from "../../store/slices/playerSlice";
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

// interface PlaybarProps {
//   songImageUrl: string;
//   artistName: string;
//   songTitle: string;
//   duration: number;
//   currentTime: number;
// }

const Playbar: React.FC = () => {
  const [isLiked, setIsLiked] = useState(false);
  const [isShuffle, setIsShuffle] = useState(false);
  const [repeatCount, setRepeatCount] = useState<number>(0);
  const [sliderTime, setSliderTime] = useState<number>(0);

  const { currentSong, isPlaying, volume, duration, currentTime } = useSelector(
    (state: RootState) => state.player
  );
  const dispatch = useDispatch();
  const audioRef = useRef<HTMLAudioElement>(null);

  const incrementRepeat = () => {
    if (repeatCount === 2) {
      setRepeatCount(0);
    } else {
      setRepeatCount(repeatCount + 1);
    }
  };

  useEffect(() => {
    if (audioRef.current) {
      if (isPlaying) {
        audioRef.current.play();
      } else {
        audioRef.current.pause();
      }
    }
  }, [isPlaying]);

  useEffect(() => {
    setSliderTime(currentTime);
  }, [currentTime]);

  useEffect(() => {
    if (audioRef.current) {
      audioRef.current.volume = volume / 100;
    }
  }, [volume]);

  const handlePlayPause = () => {
    dispatch(toggleIsPlaying());
  };

  const handleVolumeChange = (
    event: React.SyntheticEvent | Event,
    newVolume: number | number[]
  ) => {
    if (typeof newVolume === "number") {
      dispatch(setVolume(newVolume));
      if (audioRef.current) {
        audioRef.current.volume = newVolume / 100;
      }
    }
  };

  const handleAudioPlay = () => {
    if (!isPlaying) {
      dispatch(toggleIsPlaying());
    }
  };

  const handleAudioPause = () => {
    if (isPlaying) {
      dispatch(toggleIsPlaying());
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

  const handleProgressChange = (
    event: React.SyntheticEvent | Event,
    newValue: number | number[]
  ) => {
    if (typeof newValue === "number") {
      setSliderTime(newValue);
    }
  };

  const handleProgressCommit = (
    event: React.SyntheticEvent | Event,
    newValue: number | number[]
  ) => {
    if (audioRef.current && typeof newValue === "number") {
      audioRef.current.currentTime = newValue;
      dispatch(setCurrentTime(newValue));
    }
  };

  const updateTime = () => {
    if (audioRef.current) {
      dispatch(setCurrentTime(audioRef.current.currentTime));
    }
  };

  const updateDuration = () => {
    if (audioRef.current) {
      dispatch(setDuration(audioRef.current.duration));
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
        left: "240px",
        bottom: 0,
        right: 0,
        zIndex: 1000,

      }}
    >
      {currentSong && (
        <audio
          src={currentSong.file}
          ref={audioRef}
          onPlay={handleAudioPlay}
          onPause={handleAudioPause}
          onEnded={() => dispatch(toggleIsPlaying())}
          onTimeUpdate={updateTime}
          onLoadedMetadata={updateDuration}
        ></audio>
      )}
      <Stack
        direction="row"
        alignItems="center"
        spacing={1}
        sx={{ color: "#929292", margin: "0 auto" }}
      >
        <IconButton sx={{ color: "inherit" }}>
          <SkipPrevious />
        </IconButton>
        <IconButton onClick={handlePlayPause} sx={{ color: "inherit" }}>
          {isPlaying ? <Pause /> : <PlayArrow />}
        </IconButton>
        <IconButton sx={{ color: "inherit" }}>
          <SkipNext />
        </IconButton>
        <Typography sx={{ mr: "1rem" }}>{formatTime(currentTime)}</Typography>
        <Slider
          value={sliderTime}
          min={0}
          max={duration}
          step={0.01}
          onChange={(event, newValue) => handleProgressChange(event, newValue)}
          onChangeCommitted={(event, newValue) =>
            handleProgressCommit(event, newValue)
          }
          aria-labelledby="playback-slider"
          sx={{ ml: "10px", mr: "10px", color: "inherit", width: 500 }}
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
              aria-label="Volume slider"
              value={volume}
              min={0}
              step={0.01}
              onChange={(event, newValue) => handleVolumeChange(event, newValue)}
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
