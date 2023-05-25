import { Box, Tab, Tabs, Typography } from "@mui/material";
import * as React from "react";
import { useParams } from "react-router-dom";
import vibe from "../../../assets/vibe.png";
import { Feed } from "./feed/Feed";

export const CommunityPage: React.FC = () => {
  const { communityid } = useParams();
  const [activeTab, setActiveTab] = React.useState(0);

  const handleTabChange = (event: any, newValue: number) => {
    setActiveTab(newValue);
  };
  //fetch community from id
  React.useEffect(() => {
    console.log(communityid);
  }, [communityid]);

  return (
    <Box sx={{ ml: "240px", mr: "240px" }}>
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
          Community 1
        </Typography>
      </Box>
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
          label="Feed"
          sx={{ flexGrow: 1, fontFamily: "Arial", fontWeight: "bold" }}
        />
        <Tab
          label="Reels"
          sx={{ flexGrow: 1, fontFamily: "Arial", fontWeight: "bold" }}
        />
        <Tab
          label="Media"
          sx={{ flexGrow: 1, fontFamily: "Arial", fontWeight: "bold" }}
        />
        <Tab  
          label="Settings"
          sx={{ flexGrow: 1, fontFamily: "Arial", fontWeight: "bold" }}
        />
      </Tabs>
      <Box sx={{ p: 3 }}>
        {activeTab === 0 && <Feed />} 
        {activeTab === 1 && <h1>Tab 2</h1>}
        {activeTab === 2 && <h1>Tab 3</h1>}
        {activeTab === 3 && <h1>Tab 4</h1>}
      </Box>
    </Box>
  );
};
