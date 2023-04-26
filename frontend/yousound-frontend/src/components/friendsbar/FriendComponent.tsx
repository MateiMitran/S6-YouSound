import { ListItem, Avatar, Box, Typography } from "@mui/material";
import { styled } from "@mui/material/styles";
import Badge from "@mui/material/Badge";

interface FriendComponentProps {
  image: string;
  name: string;
  song: string;
  online: boolean;
}

const StyledBadgeGreen = styled(Badge)(({ theme }) => ({
  "& .MuiBadge-badge": {
    backgroundColor: "#44b700",
    color: "#44b700",
    boxShadow: `0 0 0 2px ${theme.palette.background.paper}`,
    "&::after": {
      position: "absolute",
      top: 0,
      left: 0,
      width: "100%",
      height: "100%",
      borderRadius: "50%",
      animation: "ripple 1.2s infinite ease-in-out",
      border: "1px solid currentColor",
      content: '""',
    },
  },
  "@keyframes ripple": {
    "0%": {
      transform: "scale(.8)",
      opacity: 1,
    },
    "100%": {
      transform: "scale(2.4)",
      opacity: 0,
    },
  },
}));

const StyledBadgeRed = styled(Badge)(({ theme }) => ({
  "& .MuiBadge-badge": {
    backgroundColor: "#FF0000",
    color: "#FF0000",
    boxShadow: `0 0 0 2px ${theme.palette.background.paper}`,
    "&::after": {
      position: "absolute",
      top: 0,
      left: 0,
      width: "100%",
      height: "100%",
      borderRadius: "50%",
    },
  },
}));

export const FriendComponent: React.FC<FriendComponentProps> = ({
  image,
  name,
  song,
  online,
}) => {
  return (
    <ListItem>
      {online ? (
        <StyledBadgeGreen
          overlap="circular"
          anchorOrigin={{ vertical: "bottom", horizontal: "right" }}
          variant="dot"
        >
          <Avatar alt="avatar" src={image} />
        </StyledBadgeGreen>
      ) : (
        <StyledBadgeRed
          overlap="circular"
          anchorOrigin={{ vertical: "bottom", horizontal: "right" }}
          variant="dot"
        >
          <Avatar alt="avatar" src={image} />
        </StyledBadgeRed>
      )}
      <Box sx={{ marginLeft: "1rem" }}>
        <Typography sx={{ color: "#B8B7B7" }}>{name}</Typography>
        <Typography variant="subtitle2">{song}</Typography>
      </Box>
    </ListItem>
  );
};
