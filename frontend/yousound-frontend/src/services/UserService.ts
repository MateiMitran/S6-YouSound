import axios from "axios";
import { UserEntity } from "../types";

const API_URL = "http://"+ process.env.REACT_APP_HOST + ":80/api/users";


export abstract class UserService {

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

  public static async getUser(id: string): Promise<UserEntity> {
    return new Promise((resolve) => {
      axios.get(API_URL + "/" + id).then((response) => {
        resolve(response.data);
      });
    });
  }
}
