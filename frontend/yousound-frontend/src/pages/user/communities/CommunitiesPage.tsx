import {
  Avatar,
  Box,
  IconButton,
  List,
  ListItem,
  ListItemButton,
  ListItemAvatar,
  ListItemText,
  Typography,
} from "@mui/material";
import { Delete, Folder } from "@mui/icons-material";
import * as React from "react";
import vibe from "../../../assets/vibe.png";

export const CommunitiesPage: React.FC = () => {
  return (
    <Box sx={{ ml: "240px", mr: "260px" }}>
      <Box
        sx={{
          backgroundImage: `url(${vibe})`,
          backgroundSize: "cover",
          backgroundPosition: "center",
          backgroundRepeat: "no-repeat",
          height: "35vh",
          position: "relative",
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
          borderBottomLeftRadius: "15px",
          borderBottomRightRadius: "15px",
        }}
      >
        <Typography variant="h3" sx={{ color: "white" }}>
          Communities
        </Typography>
      </Box>
      <Box>
        <List dense>
          <ListItem>
            <IconButton
              sx={{ color: "#484D79", position: "absolute", top: 0, right: 0 }}
              aria-label="delete"
            >
              <Delete />
            </IconButton>
            <ListItemButton>
              <ListItemAvatar>
                <Avatar>
                  <Folder />
                </Avatar>
              </ListItemAvatar>
              <ListItemText primary="Community 1" />
              <ListItemText primary="123 Users" />
            </ListItemButton>
          </ListItem>
          <ListItem>
            <IconButton
              sx={{ color: "#484D79", position: "absolute", top: 0, right: 0 }}
              aria-label="delete"
            >
              <Delete />
            </IconButton>
            <ListItemButton>
              <ListItemAvatar>
                <Avatar>
                  <Folder />
                </Avatar>
              </ListItemAvatar>
              <ListItemText primary="Community 2" />
              <ListItemText primary="123 Users" />
            </ListItemButton>
          </ListItem>
          <ListItem>
            <IconButton
              sx={{ color: "#484D79", position: "absolute", top: 0, right: 0 }}
              aria-label="delete"
            >
              <Delete />
            </IconButton>
            <ListItemButton>
              <ListItemAvatar>
                <Avatar>
                  <Folder />
                </Avatar>
              </ListItemAvatar>
              <ListItemText primary="Community 3" />
              <ListItemText primary="123 Users" />
            </ListItemButton>
          </ListItem>
          <ListItem>
            <IconButton
              sx={{ color: "#484D79", position: "absolute", top: 0, right: 0 }}
              aria-label="delete"
            >
              <Delete />
            </IconButton>
            <ListItemButton>
              <ListItemAvatar>
                <Avatar>
                  <Folder />
                </Avatar>
              </ListItemAvatar>
              <ListItemText primary="Community 4" />
              <ListItemText primary="123 Users" />
            </ListItemButton>
          </ListItem>
          <ListItem>
            <IconButton
              sx={{ color: "#484D79", position: "absolute", top: 0, right: 0 }}
              aria-label="delete"
            >
              <Delete />
            </IconButton>
            <ListItemButton>
              <ListItemAvatar>
                <Avatar>
                  <Folder />
                </Avatar>
              </ListItemAvatar>
              <ListItemText primary="Community 5" />
              <ListItemText primary="123 Users" />
            </ListItemButton>
          </ListItem>
          <ListItem>
            <IconButton
              sx={{ color: "#484D79", position: "absolute", top: 0, right: 0 }}
              aria-label="delete"
            >
              <Delete />
            </IconButton>
            <ListItemButton>
              <ListItemAvatar>
                <Avatar>
                  <Folder />
                </Avatar>
              </ListItemAvatar>
              <ListItemText primary="Community 6" />
              <ListItemText primary="123 Users" />
            </ListItemButton>
          </ListItem>
          <ListItem>
            <IconButton
              sx={{ color: "#484D79", position: "absolute", top: 0, right: 0 }}
              aria-label="delete"
            >
              <Delete />
            </IconButton>
            <ListItemButton>
              <ListItemAvatar>
                <Avatar>
                  <Folder />
                </Avatar>
              </ListItemAvatar>
              <ListItemText primary="Community 7" />
              <ListItemText primary="123 Users" />
            </ListItemButton>
          </ListItem>
          <ListItem>
            <IconButton
              sx={{ color: "#484D79", position: "absolute", top: 0, right: 0 }}
              aria-label="delete"
            >
              <Delete />
            </IconButton>
            <ListItemButton>
              <ListItemAvatar>
                <Avatar>
                  <Folder />
                </Avatar>
              </ListItemAvatar>
              <ListItemText primary="Community 1" />
              <ListItemText primary="123 Users" />
            </ListItemButton>
          </ListItem>
          <ListItem>
            <IconButton
              sx={{ color: "#484D79", position: "absolute", top: 0, right: 0 }}
              aria-label="delete"
            >
              <Delete />
            </IconButton>
            <ListItemButton>
              <ListItemAvatar>
                <Avatar>
                  <Folder />
                </Avatar>
              </ListItemAvatar>
              <ListItemText primary="Community 1" />
              <ListItemText primary="123 Users" />
            </ListItemButton>
          </ListItem>
          <ListItem>
            <IconButton
              sx={{ color: "#484D79", position: "absolute", top: 0, right: 0 }}
              aria-label="delete"
            >
              <Delete />
            </IconButton>
            <ListItemButton>
              <ListItemAvatar>
                <Avatar>
                  <Folder />
                </Avatar>
              </ListItemAvatar>
              <ListItemText primary="Community 1" />
              <ListItemText primary="123 Users" />
            </ListItemButton>
          </ListItem>
          <ListItem>
            <IconButton
              sx={{ position: "absolute", top: 0, right: 0 }}
              aria-label="delete"
            >
              <Delete />
            </IconButton>
            <ListItemButton>
              <ListItemAvatar>
                <Avatar>
                  <Folder />
                </Avatar>
              </ListItemAvatar>
              <ListItemText primary="Community 1" />
              <ListItemText primary="123 Users" />
            </ListItemButton>
          </ListItem>
          <ListItem>
            <IconButton
              sx={{ position: "absolute", top: 0, right: 0 }}
              aria-label="delete"
            >
              <Delete />
            </IconButton>
            <ListItemButton>
              <ListItemAvatar>
                <Avatar>
                  <Folder />
                </Avatar>
              </ListItemAvatar>
              <ListItemText primary="Community 1" />
              <ListItemText primary="123 Users" />
            </ListItemButton>
          </ListItem>
          <ListItem>
            <IconButton
              sx={{ position: "absolute", top: 0, right: 0 }}
              aria-label="delete"
            >
              <Delete />
            </IconButton>
            <ListItemButton>
              <ListItemAvatar>
                <Avatar>
                  <Folder />
                </Avatar>
              </ListItemAvatar>
              <ListItemText primary="Community 1" />
              <ListItemText primary="123 Users" />
            </ListItemButton>
          </ListItem>
          <ListItem>
            <IconButton
              sx={{ position: "absolute", top: 0, right: 0 }}
              aria-label="delete"
            >
              <Delete />
            </IconButton>
            <ListItemButton>
              <ListItemAvatar>
                <Avatar>
                  <Folder />
                </Avatar>
              </ListItemAvatar>
              <ListItemText primary="Community 1" />
              <ListItemText primary="123 Users" />
            </ListItemButton>
          </ListItem>
          <ListItem>
            <IconButton
              sx={{ position: "absolute", top: 0, right: 0 }}
              aria-label="delete"
            >
              <Delete />
            </IconButton>
            <ListItemButton>
              <ListItemAvatar>
                <Avatar>
                  <Folder />
                </Avatar>
              </ListItemAvatar>
              <ListItemText primary="Community 1" />
              <ListItemText primary="123 Users" />
            </ListItemButton>
          </ListItem>
        </List>
      </Box>
    </Box>
  );
};
