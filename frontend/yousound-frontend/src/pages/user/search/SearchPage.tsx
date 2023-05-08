import { Box } from "@mui/material";
import React from "react";
import SearchBar from "./SearchBar";

export const SearchPage: React.FC = () => {
  return (
    <Box
      sx={{
        width: "100%",
        margin: "0 auto",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        mt: "1%",
      }}
    >
      <SearchBar />
    </Box>
  );
};
