import api from "api/api"
import {
  createAnswerUrl,
  editAnswerUrl,
  getAnswerDetailUrl,
  commentUrl,
  answerUpDownUrl,
  answerAcceptUrl,
  getAnswerCommentUrl,
  getAnswerListUrl,
} from "api/urls"

export const putAnswer = (accessToken, answerSeq, data) => {
  return api(accessToken).put(editAnswerUrl(answerSeq), data)
}

export const deleteAnswer = (accessToken, answerSeq) => {
  return api(accessToken).delete(editAnswerUrl(answerSeq))
}

export const postAnswer = (accessToken, questionSeq, data) => {
  return api(accessToken).post(createAnswerUrl(questionSeq), data)
}

export const answerUpdown = (accessToken, answerSeq, data) => {
  return api(accessToken).post(answerUpDownUrl(answerSeq), data)
}

export const answerAccept = (accessToken, answerSeq) => {
  return api(accessToken).post(answerAcceptUrl(answerSeq))
}

export const getAnswerDetail = (answerSeq) => {
  return api().get(getAnswerDetailUrl(answerSeq))
}

export const getAnswerList = (questionSeq) => {
  return api().get(getAnswerListUrl(questionSeq))
}

export const getAnswerComment = (answerSeq) => {
  return api().get(getAnswerCommentUrl(answerSeq))
}

export const postAnswerComment = (accessToken, data) => {
  return api(accessToken).post(commentUrl, data)
}

export const putAnswerComment = (accessToken, data) => {
  return api(accessToken).put(commentUrl, data)
}

export const deleteAnswerComment = (accessToken) => {
  return api(accessToken).delete(commentUrl)
}

export const getAnswerUpDown = (accessToken, answerSeq) => {
  return api(accessToken).get(answerUpDownUrl(answerSeq))
}
