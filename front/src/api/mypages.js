import api, { auth_api } from "./api"
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
  return auth_api(accessToken).get(userProfile(seq))
}

export const getAllProfile = (page, size, sort) => {
  return api.get(allProfile(page, size, sort))
}

export const getUserProfile = (userSeq) => {
  return api.get(userProfile(userSeq))
}

export const putMyProfile = () => {
  return api.get(myProfile)
}

export const putMyImage = (data) => {
  return api.get(myImage, data)
}

export const getUserTags = (userSeq) => {
  return api.get(userTags(userSeq))
}

export const getUserReputation = (page, size, sort, userSeq) => {
  return api.get(userReputation(page, size, sort, userSeq))
}

export const getUserquestion = (page, size, sort, userSeq) => {
  return api.get(userQuestion(page, size, sort, userSeq))
}

export const getUserAnswer = (page, size, sort, userSeq) => {
  return api.get(userAnswer(page, size, sort, userSeq))
}
