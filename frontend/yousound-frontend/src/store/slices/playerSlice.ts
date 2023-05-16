import { createSlice, PayloadAction } from "@reduxjs/toolkit";

interface PlayerState {
  currentSong: {
    id?: number;
    name: string;
    description: string;
    created_at: string;
    picture: string;
    file: string;
    duration?: number;
    genre: string;
    album_id?: number;
    artist_id: string;
    plays?: number;
  } | null;
  isPlaying: boolean;
  volume: number;
  duration: number;
  currentTime: number;
}

const initialState: PlayerState = {
  currentSong: null,
  isPlaying: false,
  volume: 100,
  duration: 0,
  currentTime: 0,
};

const playerSlice = createSlice({
  name: "player",
  initialState,
  reducers: {
    setCurrentSong: (
      state,
      action: PayloadAction<{
        id?: number;
        name: string;
        description: string;
        created_at: string;
        picture: string;
        file: string;
        duration?: number;
        genre: string;
        album_id?: number;
        artist_id: string;
        plays?: number;
      }>
    ) => {
      state.currentSong = action.payload;
    },
    toggleIsPlaying: (state) => {
      state.isPlaying = !state.isPlaying;
    },
    setVolume: (state, action: PayloadAction<number>) => {
      state.volume = action.payload;
    },
    setDuration: (state, action: PayloadAction<number>) => {
      state.duration = action.payload;
    },
    setCurrentTime: (state, action: PayloadAction<number>) => {
      state.currentTime = action.payload;
    },
  },
});

export const {
  setCurrentSong,
  toggleIsPlaying,
  setVolume,
  setDuration,
  setCurrentTime,
} = playerSlice.actions;
export default playerSlice.reducer;
