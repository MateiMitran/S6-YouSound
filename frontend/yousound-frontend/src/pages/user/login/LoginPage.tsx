import { Box, TextField, Button, styled, Alert, Snackbar } from "@mui/material";
import React, { useEffect, useState } from "react";
import Link from "@mui/material/Link";
import blur from "../../../assets/blur.jpg";
import yousound1 from "../../../assets/yousound1.png";
import { authenticateUser, authenticationFailure, authenticationSuccess } from "../../../services/AuthService";
import { useNavigate } from "react-router-dom";
import { ThunkDispatch } from 'redux-thunk';
import { AnyAction } from 'redux';
import { useDispatch } from "react-redux";

export const CssTextField = styled(TextField)({
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

export const LoginPage: React.FC = (props: any) => {
  const [username, setUsername] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [open, setOpen] = React.useState<boolean>(false);

  const navigate = useNavigate();
  const dispatch: ThunkDispatch<{}, {}, AnyAction> = useDispatch();


  const handleClose = () => {
    setOpen(false);
  };
  const changeUsername = (event: React.ChangeEvent<HTMLInputElement>) => {
    setUsername(event.target.value);
  };

  const changePassword = (event: React.ChangeEvent<HTMLInputElement>) => {
    setPassword(event.target.value);
  };



  const handleSubmit = async (event: React.SyntheticEvent) => {
    event.preventDefault();
    if (username && password) {
      await dispatch(authenticateUser(username, password)).then((response: any) => {
        dispatch(
          authenticationSuccess(
              response.userId,
              response.accessToken,
              response.refreshToken
          )
      );
      navigate('/');
      }).catch((error: Error) => {
        dispatch(authenticationFailure(error.message));
    });
    }
    
  }

  useEffect(() => {
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
      <Snackbar
        anchorOrigin={{ horizontal: "center", vertical: "top" }}
        open={open}
        onClose={handleClose}
        autoHideDuration={5000}
      >
        <Alert onClose={handleClose} severity="warning" sx={{ width: "100%" }}>
          Verify your email before logging in!
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
            <Box
              sx={{
                display: "flex",
                justifyContent: "flex-end",
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
                {"Forgot password?"}
              </Link>
            </Box>
            <Button
              type="submit"
              variant="contained"
              sx={{
                background: "#484D79",
                textTransform: "none",
                fontSize: "15px",
                fontWeight: "bold",
                mt: "10%",
                mb: 1,
                ml: "15%",
              }}
              onClick={() => handleSubmit}
            >
              Login
            </Button>
            <Button
              variant="text"
              href="/signup"
              sx={{
                color: "#484D79",
                textTransform: "none",
                fontSize: "15px",
                fontWeight: "bold",
                mb: 1,
                ml: "15%",
              }}
            >
              Sign up
            </Button>
          </Box>
        </Box>
      </Box>
    </Box>
  );
};
