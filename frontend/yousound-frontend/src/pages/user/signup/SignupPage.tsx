import { Box, TextField, Button, styled, Snackbar, Alert } from "@mui/material";
import React, { useState } from "react";
import blur from "../../../assets/blur.jpg";
import yousound1 from "../../../assets/yousound1.png";
import { registerUser } from "../../../services/AuthService";

const CssTextField = styled(TextField)({
  "& label.Mui-focused": {
    color: "white",
  },

  "& .MuiOutlinedInput-root": {
    "&.Mui-focused fieldset": {
      borderColor: "#484D79",
    },
    "& input": {
      color: "white",
    },
  },
});

export const SignupPage: React.FC = () => {
  const [username, setUsername] = useState<String>("");
  const [password, setPassword] = useState<String>("");
  const [email, setEmail] = useState<String>("");
  const [firstName, setFirstName] = useState<String>("");
  const [lastName, setLastName] = useState<String>("");
  const [open, setOpen] = React.useState<boolean>(false);

  const handleClose = () => {
    setOpen(false);
  };

  const changeUsername = (event: React.ChangeEvent<HTMLInputElement>) => {
    setUsername(event.target.value);
  };

  const changeFirstName = (event: React.ChangeEvent<HTMLInputElement>) => {
    setFirstName(event.target.value);
  };

  const changeLastName = (event: React.ChangeEvent<HTMLInputElement>) => {
    setLastName(event.target.value);
  };

  const changeEmail = (event: React.ChangeEvent<HTMLInputElement>) => {
    setEmail(event.target.value);
  };

  const changePassword = (event: React.ChangeEvent<HTMLInputElement>) => {
    setPassword(event.target.value);
  };

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>): void => {
    event.preventDefault();
    if (username && password && email && firstName && lastName) {
      registerUser(username, password, email, firstName, lastName)
        .then((response) => {
          setOpen(true);
        })
        .catch((error) => {
          console.log(error);
        });
    }
  };

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
      <Snackbar
        anchorOrigin={{ horizontal: "center", vertical: "top" }}
        open={open}
        onClose={handleClose}
        autoHideDuration={5000}
      >
        <Alert onClose={handleClose} severity="success" sx={{ width: "100%" }}>
          User registered.
        </Alert>
      </Snackbar>
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
        <Box
          sx={{
            userSelect: "none",
            margin: "0 auto",
            width: "100%",
            padding: "20px",
            objectFit: "cover",
            display: "flex",
            alignItems: "center",
            justifyContent: "center",
          }}
        >
          <img
            src={yousound1}
            draggable="false"
            alt="YouSound"
            width={"100%"}
          />
        </Box>
        <Box
          component="form"
          onSubmit={handleSubmit}
          sx={{ mt: 1, width: "80%" }}
        >
          <Box
            sx={{
              display: "flex",
              flexDirection: "column",
              justifyContent: "space-between",
            }}
          >
            <Box
              sx={{
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
                value={username}
                onChange={changeUsername}
                autoComplete="username"
                focused
                color={"secondary"}
              />
              <CssTextField
                margin="normal"
                id="email"
                label="Email"
                name="email"
                value={email}
                onChange={changeEmail}
                autoComplete="email"
                focused
                color={"secondary"}
              />
              <CssTextField
                margin="normal"
                id="firstName"
                label="First Name"
                name="firstName"
                value={firstName}
                onChange={changeFirstName}
                autoComplete="firstName"
                focused
                color={"secondary"}
              />
              <CssTextField
                margin="normal"
                id="lastName"
                label="Last Name"
                name="lastName"
                value={lastName}
                onChange={changeLastName}
                autoComplete="lastName"
                focused
                color={"secondary"}
              />
              <CssTextField
                margin="normal"
                name="password"
                label="Password"
                type="password"
                id="password"
                value={password}
                onChange={changePassword}
                autoComplete="current-password"
                color={"secondary"}
                focused
              />
            </Box>
            <Box sx={{ display: "flex", flexDirection: "row" }}>
              <Button
                type="submit"
                variant="contained"
                onClick={() => handleSubmit}
                sx={{
                  background: "#484D79",
                  textTransform: "none",
                  fontSize: "15px",
                  mt: "10%",
                  mb: 2,
                  ml: "15%",
                }}
              >
                Create Account
              </Button>
              <Button
                variant="text"
                href="/login"
                sx={{
                  color: "#484D79",
                  textTransform: "none",
                  fontSize: "15px",
                  fontWeight: "bold",
                  mt: "10%",
                  mb: 2,
                  ml: "15%",
                }}
              >
                Sign in instead
              </Button>
            </Box>
          </Box>
        </Box>
      </Box>
    </Box>
  );
};
