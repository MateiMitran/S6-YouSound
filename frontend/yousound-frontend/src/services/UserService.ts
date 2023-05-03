import axios from "axios";
import { UserEntity } from "../types";

const API_URL = "http://localhost:8083/api/users";


export abstract class UserService {
  public static async login(
    username: String,
    password: String
  ): Promise<UserEntity | undefined> {
    return new Promise((resolve) => {
      axios
        .post(API_URL + "/login", {
          username,
          password,
        })
        .then((response) => {
          localStorage.setItem("user", JSON.stringify(response.data));
          resolve(response.data);
        })
        .catch((error) => {
          console.log("User not verified.");
          return undefined;
        });
    });
  }

  public static async register(
    username: String,
    email: String,
    firstName: String,
    lastName: String,
    password: String
  ): Promise<UserEntity> {
    return new Promise((resolve) => {
      axios
        .post(API_URL + "/register", {
          username,
          email,
          firstName,
          lastName,
          password,
        })
        .then((response) => {
          if (response.data) {
            console.log("Registered successfully.");
          }
          resolve(response.data);
        });
    });
  }

  public static async verify(token: String | undefined): Promise<UserEntity> {
    return new Promise((resolve) => {
      axios.post(API_URL + "/verify/" + token).then((response) => {
        if (response.data) {
          console.log("Verified successfully.");
        }
        resolve(response.data);
      });
    });
  }

  public static async getAllUsers(): Promise<UserEntity[]> {
    return new Promise((resolve) => {
      axios.get(API_URL).then((response) => {
        resolve(response.data);
      });
    });
  }

  public static async deleteById(id: String): Promise<Boolean> {
    return new Promise((resolve) => {
      axios
        .delete(API_URL + "/delete-by-id/" + id)
        .then((response) => {
          resolve(true);
        })
        .catch((error) => {
          resolve(false);
        });
    });
  }
}
