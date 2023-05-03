import { Box } from "@mui/material";
import React from "react";
import SearchBar from "./SearchBar";

export const SearchPage: React.FC = () => {
  return (
    <Box sx={{ position: "absolute", top: 250 }}>
      <SearchBar />
    </Box>
  );
};
