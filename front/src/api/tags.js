import api from "api/api"
import { allTagsData, singleTagData, registTag, watchTag, ignoreTag } from "api/urls"

export const getAllTagsData = (params) => {
  return api().get(allTagsData, params)
}

export const postRegistTag = (accessToken, data) => {
  return api(accessToken).post(registTag, data)
}

export const getSingleTagData = (tagName) => {
  return api().get(singleTagData(tagName))
}

export const putSingleTagData = (tagName, data) => {
  return api().put(singleTagData(tagName), data)
}

export const postWatchTag = (tagSeq, data) => {
  return api().post(watchTag(tagSeq), data)
}

export const deleteWatchTag = (tagSeq, data) => {
  return api().delete(watchTag(tagSeq), data)
}

export const postIgnoreTag = (tagSeq, data) => {
  return api().delete(ignoreTag(tagSeq), data)
}

export const deleteIgnoreTag = (tagSeq, data) => {
  return api().delete(ignoreTag(tagSeq), data)
}
