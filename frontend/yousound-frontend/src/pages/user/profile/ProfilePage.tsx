import { Box } from "@mui/material";
import * as React from "react";
import { ProfilePageHeader } from "./ProfilePageHeader";

export const ProfilePage: React.FC = () => {
  React.useEffect(() => {
    console.log(window.location);
  }, []);

  return (
    <Box sx={{ mb: "1rem" }}>
      <ProfilePageHeader />
    </Box>
  );
};
