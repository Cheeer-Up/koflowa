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

export const putAnswer = (answerSeq, data) => {
  return api().put(editAnswerUrl(answerSeq), data)
}

export const deleteAnswer = (answerSeq) => {
  return api().delete(editAnswerUrl(answerSeq))
}

export const postAnswer = (data) => {
  return api().post(createAnswerUrl, data)
}

export const answerUpdown = (answerSeq, data) => {
  return api().post(answerUpDownUrl(answerSeq), data)
}

export const answerAccept = (answerSeq) => {
  return api().post(answerAcceptUrl(answerSeq))
}

export const getAnswerDetail = (answerSeq) => {
  return api().get(getAnswerDetailUrl(answerSeq))
}

export const getAnswerList = (questionSeq, page, size) => {
  return api().get(getAnswerListUrl(questionSeq, page, size))
}

export const getAnswerComment = (answerSeq) => {
  return api().get(getAnswerCommentUrl(answerSeq))
}

export const postAnswerComment = (data) => {
  return api().post(commentUrl, data)
}

export const putAnswerComment = (data) => {
  return api().put(commentUrl, data)
}

export const deleteAnswerComment = () => {
  return api().delete(commentUrl)
}
