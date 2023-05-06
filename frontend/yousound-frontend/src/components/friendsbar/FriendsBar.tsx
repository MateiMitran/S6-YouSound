import React from "react";
import { Box, List, ListItemText, Typography } from "@mui/material";

import { FriendComponent } from "./FriendComponent";
import { dataFriends } from "./data.js";

export const FriendsBar: React.FC = React.memo((props) => {
  return (
    <Box
      sx={{
        display: "flex",
        flexDirection: "column",
        alignItems: "stretch",
        backgroundColor: "#222020",
        color: "#929292",
        height: "100vh",
        minWidth: "240px",
        position: "fixed",
        padding: "1rem",
        top: 0,
        right: 0,
        maxWidth: "240px",
      }}
    >
      <List>
        <ListItemText
          sx={{
            width: "100%",
            margin: "0 auto",
            display: "flex",
            alignItems: "center",
            justifyContent: "center",
            mb: "1rem",
          }}
          primary={
            <Typography
              sx={{
                color: "#4745A0",
                fontFamily: "Arial",
                marginLeft: "1rem",
                fontSize: "20px",
                fontWeight: "bold",
                textDecoration: "underline",
              }}
            >
              Friends
            </Typography>
          }
        />
        {dataFriends.map((friend) => {
          return (
            <FriendComponent
              image={friend.image}
              key={`${friend.name}_id`}
              name={friend.name}
              song={friend.song}
              online={true}
            />
          );
        })}
      </List>
    </Box>
  );
});
