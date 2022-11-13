import { auth_api } from "./api"
import { auth, slash } from "./urls"

export const signIn_Out = (accessToken) => {
  return auth_api(accessToken).get(auth + slash)
}
