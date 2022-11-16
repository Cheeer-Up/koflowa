import api from "api/api"
import { question, questionTag, singleQuestionData } from "api/urls"

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
  return api.get(singleQuestionData(questionSeq))
}
