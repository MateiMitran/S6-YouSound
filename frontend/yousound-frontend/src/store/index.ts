import { configureStore } from '@reduxjs/toolkit';

const initialState = {
  audio: null,
  currentTime: 0,
  duration: 0,
  volume: 0.5,
};

const audioReducer = (state = initialState, action: any) => {
  switch (action.type) {
    case 'SET_AUDIO':
      return { ...state, audio: action.payload };
    case 'SET_CURRENT_TIME':
      return { ...state, currentTime: action.payload };
    case 'SET_DURATION':
      return { ...state, duration: action.payload };
    case 'SET_VOLUME':
      return { ...state, volume: action.payload };
    default:
      return state;
  }
};

export const store = configureStore({
  reducer: audioReducer,
});