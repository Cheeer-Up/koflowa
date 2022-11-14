import api from "api/api"
import {
  createAnswer,
  editAnswer,
  getAnswerDetail,
  comment,
  answerUpDown,
  answerAccept,
  getAnswerComment,
  getAnswerList,
} from "api/urls"

export const putAnwer = (answerSeq, data) => {
  return api.put(editAnswer(answerSeq), data)
}

export const deleteAnwer = (answerSeq) => {
  return api.delete(editAnswer(answerSeq))
}

export const postAnwer = (data) => {
  return api.post(createAnswer, data)
}

export const answerUpDown = (answerSeq, data) => {
  return api.post(answerUpDown(answerSeq), data)
}

export const answerAccept = (answerSeq) => {
  return api.post(answerAccept(answerSeq))
}

export const getAnswerDetail = (answerSeq) => {
  return api.get(getAnswerDetail(answerSeq))
}

export const getAnswerList = (questionSeq, page, size) => {
  return api.get(getAnswerList(questionSeq, page, size))
}

export const getAnswerComment = (answerSeq) => {
  return api.get(getAnswerComment(answerSeq))
}

export const postAnswerComment = (data) => {
  return api.post(comment, data)
}

export const putAnswerComment = (data) => {
  return api.put(comment, data)
}

export const deleteAnswerComment = () => {
  return api.delete(comment)
}
