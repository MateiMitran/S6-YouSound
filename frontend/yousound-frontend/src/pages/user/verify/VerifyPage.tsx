import { Box, Link } from "@mui/material";
import React, { useEffect } from "react";
import blur from "../../../assets/blur.jpg";
import { useParams } from "react-router-dom";
import { UserService } from "../../../services/UserService";

export const VerifyPage: React.FC = () => {
  const { token } = useParams();

  useEffect(() => {
    const verifyEmail = async () => {
      console.log(await UserService.verify(token));
    };

    verifyEmail();
  }, [token]);

  return (
    <Box
      sx={{
        backgroundImage: `url(${blur})`,
        backgroundSize: "cover",
        backgroundPosition: "center",
        backgroundRepeat: "no-repeat",
        minHeight: "100vh",
        minWidth: "100vw",
        position: "relative",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
      }}
    >
      <Box
        sx={{
          bgcolor: "#232121",
          padding: "50px",
          maxHeight: "50vh",
          borderRadius: "15px",
          boxShadow:
            "0px 4px 6px rgba(0, 0, 0, 0.1), 0px 1px 3px rgba(0, 0, 0, 0.2)",
          display: "flex",
          justifyContent: "center",
        }}
      >
        <Link
          href="/login"
          variant="body2"
          sx={{
            textDecoration: "none",
            color: "#484D79",
            fontWeight: "bold",
            marginTop: "2%",
          }}
        >
          {"Verified! Press here to login!"}
        </Link>
      </Box>
    </Box>
  );
};
