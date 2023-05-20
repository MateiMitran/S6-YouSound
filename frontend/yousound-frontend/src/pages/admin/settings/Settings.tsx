import { Button } from "@mui/material";
import { useDispatch } from "react-redux";
import { logout } from "../../../services/AuthService";

export const Settings: React.FC = () => {
  const dispatch = useDispatch();
  const logOut = () => {
    dispatch(logout());
  }

  return <Button variant="contained" onClick={() => {
    logOut();
    window.location.href = "/login";
  }}>LogOut</Button>;
};
