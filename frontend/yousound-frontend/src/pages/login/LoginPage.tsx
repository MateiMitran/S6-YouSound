import { Box, Typography, TextField, Button, styled } from "@mui/material";
import React, { useEffect } from "react";
import Link from "@mui/material/Link";
import blur from "../../assets/blur.jpg";
import yousound1 from "../../assets/yousound1.png";

const CssTextField = styled(TextField)({
  "& label.Mui-focused": {
    color: "white",
  },

  "& .MuiOutlinedInput-root": {
    "&.Mui-focused fieldset": {
      borderColor: "#484D79",
    },
  },
});

export const LoginPage: React.FC = () => {
  useEffect(() => {
    window.localStorage.removeItem("token");
  }, []);

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
        <Box>
          <Box
            sx={{
              userSelect: "none",
              margin: "0 auto",
              width: "100%",
              padding: "20px",
              objectFit: "cover",
            }}
          >
            <img
              src={yousound1}
              draggable="false"
              alt="YouSound"
              width={"100%"}
            />
          </Box>
        </Box>
        <Box component="form" sx={{ mt: 1, width: "80%" }}>
          <Box
            style={{
              display: "flex",
              flexDirection: "column",
              justifyContent: "space-between",
            }}
          >
            <Box
              style={{
                display: "flex",
                flexDirection: "column",
                marginLeft: "15%",
              }}
            >
              <CssTextField
                margin="normal"
                id="username"
                label="Username"
                name="username"
                autoComplete="username"
                focused
                color={"secondary"}
              />
              <CssTextField
                margin="normal"
                name="password"
                label="Password"
                type="password"
                id="password"
                autoComplete="current-password"
                color={"secondary"}
                focused
              />
            </Box>
            <Box
              style={{
                display: "flex",
                justifyContent: "flex-end",
              }}
            >
              <Link
                href="/login"
                variant="body2"
                style={{
                  textDecoration: "none",
                  color: "#484D79",
                  fontWeight: "bold",
                  marginTop: "2%",
                }}
              >
                {"Forgot password?"}
              </Link>
            </Box>
            <Button
              type="submit"
              variant="contained"
              style={{
                background: "#484D79",
                textTransform: "none",
                fontSize: "15px",
              }}
              sx={{ mt: "10%", mb: 2, ml: "15%" }}
            >
              Log In
            </Button>
          </Box>
        </Box>
      </Box>
    </Box>
  );
};
