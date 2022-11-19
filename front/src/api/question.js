import api from "api/api"
import { question, questionTag, singleQuestionData, questionUpdown, getQuestionUpdownUrl } from "api/urls"

export const postQuestion = (accessToken, data) => {
  return api(accessToken).post(question, data)
}

export const getQuestionsData = (accessToken, data) => {
  return api(accessToken).get(question, data)
}

export const getQuestionDatabyTagName = (data) => {
  return api().get(questionTag(data))
}

export const getQuestionData = (questionSeq) => {
  return api().get(singleQuestionData(questionSeq))
}

export const postQuestionUpdown = (accessToken, data) => {
  return api(accessToken).post(questionUpdown, data)
}

export const getQuestionUpdown = (accessToken, questionSeq) => {
  return api(accessToken).get(getQuestionUpdownUrl(questionSeq))
}
