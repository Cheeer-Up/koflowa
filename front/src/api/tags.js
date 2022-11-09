import api from "api/api"
import { allTagsData, singleTagData, registTag, watchTag, ignoreTag } from "api/urls"

export const getAllTagsData = () => {
  return api.get(allTagsData)
}

export const postRegistTag = (data) => {
  return api.post(registTag, data)
}

export const getSingleTagData = (tagSeq) => {
  return api.get(singleTagData(tagSeq))
}

export const putSingleTagData = (tagSeq, data) => {
  return api.put(singleTagData(tagSeq), data)
}

export const postWatchTag = (tagSeq, data) => {
  return api.post(watchTag(tagSeq), data)
}

export const deleteWatchTag = (tagSeq, data) => {
  return api.delete(watchTag(tagSeq), data)
}

export const postIgnoreTag = (tagSeq, data) => {
  return api.delete(ignoreTag(tagSeq), data)
}

export const deleteIgnoreTag = (tagSeq, data) => {
  return api.delete(ignoreTag(tagSeq), data)
}
