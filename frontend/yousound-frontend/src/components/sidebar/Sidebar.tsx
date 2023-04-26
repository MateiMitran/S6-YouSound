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
} from "@mui/material";
import {
  Home,
  Search,
  LibraryMusic,
  Group,
  Inbox,
  AddBox,
} from "@mui/icons-material";
import { playlists } from "./playlists";
import YouSound from "../../assets/yousound.png";
import Menu from "@mui/material/Menu";

// interface SidebarProps {
//   profilePic: string;
//   name: string;
//   playlists: string[];
// }

const Sidebar: React.FC = () => {
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
        minWidth: "240px",
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
          <Avatar>MM</Avatar>
          <ListItemText
            primary={
              <Typography
                sx={{
                  color: "white",
                  fontFamily: "Arial",
                  marginLeft: "1rem",
                  fontSize: "20px",
                }}
              >
                Matei Mitran
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
        >
          <MenuItem onClick={handleClose}>Profile</MenuItem>
          <MenuItem
            onClick={() => {
              handleClose();
              window.location.href = "/login";
            }}
          >
            Logout
          </MenuItem>
        </Menu>
        <ListItemButton href={"/"}>
          <ListItemIcon>
            <Home sx={{ color: "#929292" }} />
          </ListItemIcon>
          <ListItemText primary="Home" />
        </ListItemButton>
        <ListItemButton>
          <ListItemIcon>
            <Search sx={{ color: "#929292" }} />
          </ListItemIcon>
          <ListItemText primary="Search" />
        </ListItemButton>
        <Divider sx={{ backgroundColor: "#929292", margin: "0.5rem 0" }} />
        <ListItemButton>
          <ListItemIcon>
            <LibraryMusic sx={{ color: "#929292" }} />
          </ListItemIcon>
          <ListItemText primary="Library" />
        </ListItemButton>
        <ListItemButton>
          <ListItemIcon>
            <Group sx={{ color: "#929292" }} />
          </ListItemIcon>
          <ListItemText primary="Communities" />
        </ListItemButton>
        <ListItemButton>
          <ListItemIcon>
            <Inbox sx={{ color: "#929292" }} />
          </ListItemIcon>
          <ListItemText primary="Inbox" />
        </ListItemButton>
        <Divider sx={{ backgroundColor: "#929292", margin: "0.5rem 0" }} />
        <ListItemButton>
          <ListItemIcon>
            <AddBox sx={{ color: "#929292" }} />
          </ListItemIcon>
          <ListItemText
            primary={
              <span style={{ color: "#929292", fontWeight: 500 }}>
                Create Playlist
              </span>
            }
          />
        </ListItemButton>
        {playlists.map((playlist: String, index: number) => (
          <ListItemButton key={index}>
            <ListItemText primary={playlist} />
          </ListItemButton>
        ))}
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

export default Sidebar;
