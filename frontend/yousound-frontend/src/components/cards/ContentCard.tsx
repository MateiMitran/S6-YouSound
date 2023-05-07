import React, { useState } from "react";
import { Card, CardMedia, CardContent, Typography } from "@mui/material";
import { SongEntity } from "../../types";

interface ContentCardProps {
  name: string;
  picture: string;
  file: string;
  artist_id: string;
  onClick: () => void;
}

export const ContentCard: React.FC<ContentCardProps> = ({
  name,
  picture,
  file,
  artist_id,
  onClick,
}) => {
  const [isHovered, setIsHovered] = useState(false);

  const handleMouseOver = () => {
    setIsHovered(true);
  };

  const handleMouseOut = () => {
    setIsHovered(false);
  };

  return (
    <Card
      onMouseOver={handleMouseOver}
      onMouseOut={handleMouseOut}
      onClick={onClick}
      sx={
        isHovered
          ? {
              width: "100%",
              padding: 2,
              bgcolor: "#373737",
              border: "1px solid black",
              boxShadow:
                "0px 4px 6px rgba(0, 0, 0, 0.1), 0px 1px 3px rgba(0, 0, 0, 0.2)",
              borderRadius: 3,
              cursor: "pointer",
              userSelect: "none",
            }
          : {
              width: "100%",
              padding: 2,
              bgcolor: "#232121",
              border: "1px solid black",
              boxShadow:
                "0px 4px 6px rgba(0, 0, 0, 0.1), 0px 1px 3px rgba(0, 0, 0, 0.2)",
              borderRadius: 3,
              userSelect: "none",
            }
      }
    >
      <CardMedia
        component="img"
        height="140"
        src={picture}
        alt={name}
        sx={{
          borderRadius: 1.5,
          border: "2px solid black",
          objectFit: "cover",
        }}
      />
      <CardContent>
        <Typography
          gutterBottom
          variant="h6"
          component="div"
          textAlign="center"
          color="white"
          noWrap
        >
          {name}
        </Typography>
        <Typography
          variant="subtitle2"
          color="#484D79"
          component="p"
          textAlign="center"
          noWrap
          fontWeight="bold"
        >
          {artist_id}
        </Typography>
      </CardContent>
    </Card>
  );
};
