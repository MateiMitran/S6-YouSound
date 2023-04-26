import React, { useState } from "react";
import { Card, CardMedia, CardContent, Typography } from "@mui/material";

interface ContentCardProps {
  image: string;
  name: string;
  artist: string;
}

export const ContentCard: React.FC<ContentCardProps> = ({
  image,
  name,
  artist,
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
      sx={
        isHovered
          ? {
              width: "100%",
              maxWidth: 500,
              padding: 2,
              bgcolor: "#373737",
              border: "1px solid black",
              boxShadow:
                "0px 4px 6px rgba(0, 0, 0, 0.1), 0px 1px 3px rgba(0, 0, 0, 0.2)",
              borderRadius: 3,
            }
          : {
              width: "100%",
              maxWidth: 500,
              padding: 2,
              bgcolor: "#232121",
              border: "1px solid black",
              boxShadow:
                "0px 4px 6px rgba(0, 0, 0, 0.1), 0px 1px 3px rgba(0, 0, 0, 0.2)",
              borderRadius: 3,
            }
      }
    >
      <CardMedia
        component="img"
        height="140"
        image={image}
        alt="image"
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
          {artist}
        </Typography>
      </CardContent>
    </Card>
  );
};
