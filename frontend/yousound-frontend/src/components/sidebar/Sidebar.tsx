import * as React from "react";
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
import { useDispatch } from "react-redux";
import { logout } from "../../services/AuthService";
import { useSelector } from "react-redux";
import { UserService } from "../../services/UserService";
// interface SidebarProps {
//   profilePic: string;
//   name: string;
//   playlists: string[];
// }

const Sidebar: React.FC = () => {
  const [anchorEl, setAnchorEl] = React.useState<null | HTMLElement>(null);
  const [firstName, setFirstName] = React.useState<string>("");
  const [lastName, setLastName] = React.useState<string>("");
  const open = Boolean(anchorEl);
  const userId = useSelector((state: any) => state.authentication.userId);
  const dispatch = useDispatch();
  const handleClick = (event: any) => {
    setAnchorEl(event.currentTarget);
  };
  const handleClose = () => {
    setAnchorEl(null);
  };

  const logOut = () => {
    dispatch(logout());
  }

  React.useEffect(() => {
    if (userId) {
      UserService.getUser(userId).then((response: any) => {
        setFirstName(response.firstName);
        setLastName(response.lastName);
      });
    }
  }, []);


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
          <Avatar>{extractLetters(firstName+" "+lastName)}</Avatar>
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
                {firstName + " " + lastName}
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
            sx={{
              width: "200px",
            }}
            onClick={() => {
              handleClose();
              window.location.href = "/profile";
            }}
          >
            Profile
          </MenuItem>
          <MenuItem
            onClick={() => {
              logOut();
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
        <ListItemButton href={"/search"}>
          <ListItemIcon>
            <Search sx={{ color: "#929292" }} />
          </ListItemIcon>
          <ListItemText primary="Search" />
        </ListItemButton>
        <Divider sx={{ backgroundColor: "#929292", margin: "0.5rem 0" }} />
        <ListItemButton href={"/library"}>
          <ListItemIcon>
            <LibraryMusic sx={{ color: "#929292" }} />
          </ListItemIcon>
          <ListItemText primary="Library" />
        </ListItemButton>
        <ListItemButton href={"/communities"}>
          <ListItemIcon>
            <Group sx={{ color: "#929292" }} />
          </ListItemIcon>
          <ListItemText primary="Communities" />
        </ListItemButton>
        <ListItemButton href={"/inbox"}>
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


function extractLetters(str: string): string {
  const trimmedStr = str.trim(); 

  const firstLetter = trimmedStr.charAt(0);

  const spaceIndex = trimmedStr.indexOf(' ');
  const letterAfterSpace = trimmedStr.charAt(spaceIndex + 1);

  return firstLetter + letterAfterSpace;
}
