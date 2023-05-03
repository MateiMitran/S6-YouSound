import axios from "axios";
import {
  SongEntity,
  AlbumEntity,
  PlaylistEntity,
  ContentEntity,
} from "../types";

const API_URL = "http://localhost:8081/api/music";

export abstract class MusicService {
  public static async getAllSongs(): Promise<SongEntity[]> {
    return new Promise((resolve) => {
      axios.get(API_URL + "/songs").then((response) => {
        resolve(response.data);
      });
    });
  }

  public static async getAllAlbums(): Promise<AlbumEntity[]> {
    return new Promise((resolve) => {
      axios.get(API_URL + "/albums").then((response) => {
        resolve(response.data);
      });
    });
  }

  public static async getAllPlaylists(): Promise<PlaylistEntity[]> {
    return new Promise((resolve) => {
      axios.get(API_URL + "/playlists").then((response) => {
        resolve(response.data);
      });
    });
  }

  public static async getContent(): Promise<ContentEntity> {
    return new Promise((resolve) => {
      axios.get("http://localhost:8081/api/all").then((response) => {
        resolve(response.data);
      });
    });
  }

  public static async deleteSongById(id: number): Promise<Boolean> {
    return new Promise((resolve) => {
      axios.delete(API_URL + "/songs/delete/" + id).then((response) => {
        resolve(true);
      });
    });
  }

  public static async deleteAlbumById(id: number): Promise<Boolean> {
    return new Promise((resolve) => {
      axios.delete(API_URL + "/albums/delete/" + id).then((response) => {
        resolve(true);
      });
    });
  }

  public static async deletePlaylistById(id: number): Promise<Boolean> {
    return new Promise((resolve) => {
      axios.delete(API_URL + "/playlists/delete/" + id).then((response) => {
        resolve(true);
      });
    });
  }
}
