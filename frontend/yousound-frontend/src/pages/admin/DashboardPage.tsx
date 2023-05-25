import React, { useState } from "react";
import { Tabs, Tab, Box } from "@mui/material";
import { Users } from "./users/Users";
import { Music } from "./content/Music";
import { Settings } from "./settings/Settings";
import { Charts } from "./charts/Charts";

export const DashboardPage: React.FC = () => {
  const [activeTab, setActiveTab] = useState(0);

  const handleTabChange = (event: any, newValue: number) => {
    setActiveTab(newValue);
  };

  return (
    <Box height="100vh" display="flex" flexDirection="column">
      <Tabs
        value={activeTab}
        onChange={handleTabChange}
        variant="fullWidth"
        sx={{
          flexShrink: 0,
          backgroundColor: "background.paper",
          borderBottom: 1,
          borderColor: "divider",
          justifyContent: "flex-start",
        }}
      >
        <Tab
          label="Users"
          sx={{ flexGrow: 1, fontFamily: "Arial", fontWeight: "bold" }}
        />
        <Tab
          label="Music"
          sx={{ flexGrow: 1, fontFamily: "Arial", fontWeight: "bold" }}
        />
        <Tab
          label="Charts"
          sx={{ flexGrow: 1, fontFamily: "Arial", fontWeight: "bold" }}
        />
        <Tab
          label="Settings"
          sx={{ flexGrow: 1, fontFamily: "Arial", fontWeight: "bold" }}
        />
      </Tabs>
      <Box sx={{ p: 3 }}>
        {activeTab === 0 && <Users />}
        {activeTab === 1 && <Music />}
        {activeTab === 2 && <Charts />}
        {activeTab === 3 && <Settings />}
      </Box>
    </Box>
  );
};
