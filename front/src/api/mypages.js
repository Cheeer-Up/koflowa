import { api, image_api } from "./api"
import {
  allProfile,
  userProfile,
  myProfile,
  myImage,
  userTags,
  userReputation,
  userQuestion,
  userAnswer,
} from "../api/urls"

export const getAuthUserProfile = (seq, accessToken) => {
  return api(accessToken).get(userProfile(seq))
}

export const getAllProfile = (accessToken, page, size, sort) => {
  return api(accessToken).get(allProfile(page, size, sort))
}

export const getUserProfile = (accessToken, userSeq) => {
  return api(accessToken).get(userProfile(userSeq))
}

export const putMyProfile = (accessToken, data) => {
  return api(accessToken).put(myProfile, data)
}

export const postMyImage = (accessToken, data) => {
  console.log(data)
  return image_api(accessToken).post(myImage, data)
}

export const getUserTags = (accessToken, userSeq) => {
  return api(accessToken).get(userTags(userSeq))
}

export const getUserReputation = (accessToken, page, size, sort, userSeq) => {
  return api(accessToken).get(userReputation(page, size, sort, userSeq))
}

export const getUserquestion = (accessToken, page, size, sort, userSeq) => {
  return api(accessToken).get(userQuestion(page, size, sort, userSeq))
}

export const getUserAnswer = (accessToken, page, size, sort, userSeq) => {
  return api(accessToken).get(userAnswer(page, size, sort, userSeq))
}
