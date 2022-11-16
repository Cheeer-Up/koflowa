import api from "./api"
import { auth, slash } from "./urls"
import { getNickname } from "./urls"

export const signIn_Out = (accessToken) => {
  return api(accessToken).get(auth + slash)
}

export const setUserNickname = (accessToken, data) => {
  return api(accessToken).get(getNickname(data))
}
