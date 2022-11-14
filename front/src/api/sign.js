import api from "./api"
import { auth, slash } from "./urls"

export const signIn_Out = (accessToken) => {
  return api(accessToken).get(auth + slash)
}
