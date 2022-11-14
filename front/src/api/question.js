import api from "api/api"
import { createQuestion, getQuestions, getQuestion } from "api/urls"

export const postQuestion = (accessToken, data) => {
  return api(accessToken).post(createQuestion, data)
}

export const getQuestionsData = (accessToken, page, size) => {
  return api(accessToken).get(getQuestions(page, size))
}

export const getQuestionData = (questionSeq) => {
  return api.get(getQuestion(questionSeq))
}
