import React from "react";
import {
  Avatar,
  Box,
  Divider,
  List,
  ListItemButton,
  ListItemIcon,
  ListItemText,
  MenuItem,
  Typography,
  Menu,
} from "@mui/material";
import { Home, Settings, Group, Inbox, Headphones } from "@mui/icons-material";
import YouSound from "../../assets/yousound.png";

// interface SidebarProps {
//   profilePic: string;
//   name: string;
//   playlists: string[];
// }

const SidebarArtist: React.FC = () => {
  const [anchorEl, setAnchorEl] = React.useState<null | HTMLElement>(null);
  const open = Boolean(anchorEl);
  const handleClick = (event: any) => {
    setAnchorEl(event.currentTarget);
  };
  const handleClose = () => {
    setAnchorEl(null);
  };

  return (
    <Box
      sx={{
        display: "flex",
        flexDirection: "column",
        alignItems: "stretch",
        backgroundColor: "#1E0D19",
        color: "#929292",
        height: "100vh",
        width: "230px",
        position: "fixed",
        padding: "1rem",
        top: 0,
        left: 0,
        maxWidth: "240px",
      }}
    >
      <List>
        <ListItemButton
          id="basic-button"
          onClick={handleClick}
          aria-controls={open ? "basic-menu" : undefined}
          aria-haspopup="true"
          aria-expanded={open ? "true" : undefined}
          sx={{ marginBottom: "1rem" }}
        >
          <Avatar>TU</Avatar>
          <ListItemText
            primary={
              <Typography
                sx={{
                  color: "white",
                  fontFamily: "Arial",
                  marginLeft: "1rem",
                  fontSize: "20px",
                  fontWrap: "nowrap",
                }}
              >
                Uraganul
              </Typography>
            }
          />
        </ListItemButton>
        <Menu
          id="basic-menu"
          anchorEl={anchorEl}
          open={open}
          onClose={handleClose}
          MenuListProps={{
            "aria-labelledby": "basic-button",
          }}
          sx={{
            ml: "10px",
          }}
        >
          <MenuItem
            onClick={() => {
              handleClose();
              window.location.href = "/login";
            }}
          >
            Logout
          </MenuItem>
        </Menu>
        <List>
          <ListItemButton href={"/artisthome"}>
            <ListItemIcon>
              <Home sx={{ color: "#929292" }} />
            </ListItemIcon>
            <ListItemText primary="Home" />
          </ListItemButton>
          <ListItemButton href={"/music"}>
            <ListItemIcon>
              <Headphones sx={{ color: "#929292" }} />
            </ListItemIcon>
            <ListItemText primary="Music" />
          </ListItemButton>
          <Divider sx={{ backgroundColor: "#929292", margin: "0.5rem 0" }} />

          <ListItemButton href={"/community/"}>
            <ListItemIcon>
              <Group sx={{ color: "#929292" }} />
            </ListItemIcon>
            <ListItemText primary="Community" />
          </ListItemButton>
          <ListItemButton href={"/inbox"}>
            <ListItemIcon>
              <Inbox sx={{ color: "#929292" }} />
            </ListItemIcon>
            <ListItemText primary="Inbox" />
          </ListItemButton>
          <ListItemButton href={"/artistsettings"}>
            <ListItemIcon>
              <Settings sx={{ color: "#929292" }} />
            </ListItemIcon>
            <ListItemText primary="Settings" />
          </ListItemButton>
          <Divider sx={{ backgroundColor: "#929292", margin: "0.5rem 0" }} />
        </List>
      </List>
      <Box
        sx={{
          position: "absolute",
          bottom: "0",
          mr: "25px",
          userSelect: "none",
        }}
      >
        <img src={YouSound} draggable="false" alt="YouSound" width={"100%"} />
      </Box>
    </Box>
  );
};

export default SidebarArtist;
