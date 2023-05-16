export type ContentEntity = {
  songs: SongEntity[];
  albums: AlbumEntity[];
  playlists: PlaylistEntity[];
};

export type SongEntity = {
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
};

export type AlbumEntity = {
  id: number;
  name: string;
  description: string;
  created_at: Date;
  picture: string;
  file: string;
  duration: number;
  nr_of_songs: number;
  artist_id: string;
};

export type PlaylistEntity = {
  id: number;
  name: string;
  description: string;
  created_at: Date;
  picture: string;
  file: string;
  duration: number;
  nr_of_songs?: number;
  creator_id: string;
};

export type UserEntity = {
  id: string;
  username: string;
  password: string;
  email: string;
  firstName: string;
  lastName: string;
  verification_token: string;
  verified: boolean;
  type: string;
};

export type CommunityEntity = {
  id: string;
  artist_id: string;
  description: string;
  user_ids: string[];
}
