import api, { elastic_api } from "api/api"
import {
  allTagsData,
  singleTagData,
  registTag,
  watchTag,
  ignoreTag,
  watchTagList,
  ignoreTagList,
  allTagsStringList,
  rankingTag,
} from "api/urls"

const rankingTagsCondition = {
  _source: ["tag_seq", "tag_name", "tag_count"],
  size: 10,
  collapse: {
    field: "tag_seq",
  },
  sort: [
    {
      tag_count: {
        order: "desc",
      },
    },
  ],
}

const allTagsCondition = {
  _source: ["tag_seq", "tag_name", "tag_count"],
  collapse: {
    field: "tag_seq",
  },
}

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

export const getWatchTagList = (accessToken) => {
  return api(accessToken).get(watchTagList)
}

export const postWatchTag = (accessToken, tagName) => {
  return api(accessToken).post(watchTag(tagName))
}

export const deleteWatchTag = (accessToken, tagName) => {
  return api(accessToken).delete(watchTag(tagName))
}

export const getIgnoreTagList = (accessToken) => {
  return api(accessToken).get(ignoreTagList)
}

export const postIgnoreTag = (accessToken, tagName) => {
  return api(accessToken).post(ignoreTag(tagName))
}

export const deleteIgnoreTag = (accessToken, tagName) => {
  return api(accessToken).delete(ignoreTag(tagName))
}

export const getAllTagsStringList = () => {
  return api().get(allTagsStringList)
}

export const getRankingTags = () => {
  return elastic_api().get(rankingTag(), {
    params: {
      source: JSON.stringify(rankingTagsCondition),
      source_content_type: "application/json",
    },
  })
}

export const getAllTags = () => {
  return elastic_api().get(rankingTag(), {
    params: {
      source: JSON.stringify(allTagsCondition),
      source_content_type: "application/json",
    },
  })
}
