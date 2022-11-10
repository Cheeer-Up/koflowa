import api from "api/api"
import { createQuestion, getQuestions } from "api/urls"

export const postQuestion = (data) => {
  return api.post(createQuestion, data)
}

export const getQuestionsData = (page, size) => {
  return api.get(getQuestions(page, size))
}
