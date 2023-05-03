export type ContentEntity = {
  songs: SongEntity[];
  albums: AlbumEntity[];
  playlists: PlaylistEntity[];
};

export type SongEntity = {
  id: number;
  name: String;
  description: String;
  created_at: Date;
  picture: String;
  file: String;
  duration: number;
  genre: String;
  album_id?: number;
  artist_id: String;
  plays?: number;
};

export type AlbumEntity = {
  id: number;
  name: String;
  description: String;
  created_at: Date;
  picture: String;
  file: String;
  duration: number;
  nr_of_songs: number;
  artist_id: String;
};

export type PlaylistEntity = {
  id: number;
  name: String;
  description: String;
  created_at: Date;
  picture: String;
  file: String;
  duration: number;
  nr_of_songs?: number;
  creator_id: String;
};

export type UserEntity = {
  id: String;
  username: String;
  password: String;
  email: String;
  firstName: String;
  lastName: String;
  verification_token: String;
  verified: boolean;
  type: String;
};
