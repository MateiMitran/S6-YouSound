import axios from "axios";

const API_URL = "http://localhost:8083/api/users";

export type UserEntity = {
  id: String;
  username: String;
  password: String;
  email: String;
  firstName: String;
  lastName: String;
  verification_token: String;
  verified: boolean;
  type: String;
};

export abstract class UserService {
  public static async login(
    username: String,
    password: String
  ): Promise<UserEntity> {
    return new Promise((resolve) => {
      axios
        .post(API_URL + "/login", {
          username,
          password,
        })
        .then((response) => {
          if (response.data) {
            localStorage.setItem("user", JSON.stringify(response.data));
          }
          resolve(response.data);
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
}
