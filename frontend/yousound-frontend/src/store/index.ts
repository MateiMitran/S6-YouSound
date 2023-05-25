import authenticationReducer from "./slices/authenticationSlice";
import playerReducer from "./slices/playerSlice";
import { createStore, combineReducers, applyMiddleware } from "redux";
import thunkMiddleware from "redux-thunk";
import { persistStore, persistReducer } from "redux-persist";
import storage from "redux-persist/lib/storage";

// const store = configureStore({
//   reducer: {
//     player: playerReducer,
//   },
// });

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
// export default store;

const persistConfig = {
  key: "root",
  storage,
};

const rootReducer = combineReducers({
  authentication: authenticationReducer,
  player: playerReducer
});

const persistedReducer = persistReducer(persistConfig, rootReducer);

const store = createStore(persistedReducer, applyMiddleware(thunkMiddleware));

const persistor = persistStore(store);

export { store, persistor };