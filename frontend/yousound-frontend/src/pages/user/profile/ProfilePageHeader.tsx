import React from "react";
import {
  Snackbar,
  Avatar,
  Divider,
  IconButton,
  Alert,
  Typography,
  Container,
  Button,
  Grid,
} from "@mui/material";
import { Box } from "@mui/system";
import ProfileImage from "../../../assets/avatar3.jpg";
import EditIcon from "@mui/icons-material/Edit";
import SendIcon from "@mui/icons-material/Send";
import { CssTextField } from "../login/LoginPage";

export const ProfilePageHeader: React.FC = () => {
  const [enabled, setEnabled] = React.useState<boolean>(false);
  const [username, setUsername] = React.useState<String>("");
  const [firstName, setFirstName] = React.useState<String>("");
  const [lastName, setLastName] = React.useState<String>("");
  const [email, setEmail] = React.useState<String>("");
  const [phone, setPhone] = React.useState<String>("");
  const [address, setAddress] = React.useState<String>("");
  const [open, setOpen] = React.useState<boolean>(false);

  //   React.useEffect(() => {
  //     const fetchData = async () => {
  //       UserService.getUser(userId).then((res) => {
  //         if (res) {
  //           setUsername(res.username);
  //           setFirstName(res.firstName);
  //           setLastName(res.lastName);
  //           setEmail(res.email);
  //           setPhone(res.phone);
  //           setAddress(res.address);
  //         }
  //       });
  //     };
  //     fetchData();
  //   }, []);

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

  const changePhone = (event: React.ChangeEvent<HTMLInputElement>) => {
    setPhone(event.target.value);
  };

  const changeAddress = (event: React.ChangeEvent<HTMLInputElement>) => {
    setAddress(event.target.value);
  };

  const changeEnabled = () => {
    setEnabled(!enabled);
  };

  //   const handleSubmit = (event: React.FormEvent<HTMLFormElement>): void => {
  //     event.preventDefault();
  //     console.log(username);
  //     UserService.updateUser(
  //       userId,
  //       username,
  //       firstName,
  //       lastName,
  //       email,
  //       phone,
  //       address
  //     ).then((res) => {
  //       if (res) {
  //         console.log(res);
  //         setOpen(true);
  //         changeEnabled();
  //       }
  //     });
  //   };

  return (
    <Container maxWidth="lg">
      <Box
        sx={{
          height: "100%",
          borderBottom: "1px solid #a9a9b0",
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
          justifyContent: "center",
          textAlign: "center",
          padding: "40px",
        }}
      >
        <Snackbar
          anchorOrigin={{ horizontal: "center", vertical: "top" }}
          open={open}
          onClose={handleClose}
          autoHideDuration={5000}
        >
          <Alert
            onClose={handleClose}
            severity="success"
            sx={{ width: "100%" }}
          >
            Profile updated successfully
          </Alert>
        </Snackbar>
        <Grid container spacing={4} alignItems="center" justifyContent="center">
          <Grid item xs={12} md={3}>
            <Avatar
              alt="Profile picture"
              src={ProfileImage}
              sx={{
                width: "100%",
                height: "30vh",
                border: "5px solid #a9a9b0",
              }}
            />
          </Grid>
          <Grid item xs={12} md={8}>
            <Box sx={{ ml: { md: 4 } }}>
              <Typography variant="h2" sx={{ mb: 1 }}>
                John Doe
              </Typography>
              
            </Box>
          </Grid>
        </Grid>
        <Divider sx={{ margin: "40px 0" }} />
        <form>
          <Grid
            container
            spacing={4}
            alignItems="center"
            justifyContent="center"
          >
            <Grid item xs={12} sm={6} md={4}>
              <CssTextField
                id="username"
                label="Username"
                value={username}
                onChange={changeUsername}
                focused
                InputProps={{
                  readOnly: !enabled,
                }}
                fullWidth
              />
            </Grid>
            <Grid item xs={12} sm={6} md={4}>
              <CssTextField
                id="firstName"
                label="First Name"
                value={firstName}
                onChange={changeFirstName}
                focused
                InputProps={{
                  readOnly: !enabled,
                }}
                fullWidth
              />
            </Grid>
            <Grid item xs={12} sm={6} md={4}>
              <CssTextField
                id="lastName"
                label="Last Name"
                value={lastName}
                onChange={changeLastName}
                focused
                InputProps={{
                  readOnly: !enabled,
                }}
                fullWidth
              />
            </Grid>
            <Grid item xs={12} sm={6} md={4}>
              <CssTextField
                id="email"
                label="Email"
                value={email}
                onChange={changeEmail}
                focused
                InputProps={{
                  readOnly: !enabled,
                }}
                fullWidth
              />
            </Grid>
            <Grid item xs={12} sm={6} md={4}>
              <CssTextField
                id="phone"
                label="Phone Number"
                value={phone}
                onChange={changePhone}
                focused
                InputProps={{
                  readOnly: !enabled,
                }}
                fullWidth
              />
            </Grid>
            <Grid item xs={12} sm={6} md={4}>
              <CssTextField
                id="address"
                label="Address"
                value={address}
                onChange={changeAddress}
                focused
                InputProps={{
                  readOnly: !enabled,
                }}
                fullWidth
              />
            </Grid>
            <Box
              sx={{
                display: "flex",
                flexDirection: "row",
                mt: "15px",
                gap: "20px",
              }}
            >
              <Grid item xs={12} sm={6} md={4}>
                <IconButton
                  aria-label="edit"
                  onClick={changeEnabled}
                  size="large"
                  disableRipple
                  sx={{
                    "&:hover": { backgroundColor: "transparent" },
                    color: enabled ? "#484D79" : "gray",
                  }}
                >
                  <EditIcon />
                </IconButton>
              </Grid>
              {enabled && (
                <Grid item xs={12}>
                  <Button
                    type="submit"
                    variant="contained"
                    endIcon={<SendIcon />}
                    sx={{
                      backgroundColor: "#484D79",
                      "&:hover": { backgroundColor: "#484D79" },
                      width: "125px",
                      height: "35px",
                    }}
                    onClick={() => {
                      //  handleSubmit;
                    }}
                  >
                    Update
                  </Button>
                </Grid>
              )}
            </Box>
          </Grid>
        </form>
      </Box>
    </Container>
  );
};
