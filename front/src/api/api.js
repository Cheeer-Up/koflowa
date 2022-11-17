import axios from "axios"

// BASE_URL 설정
const config = {
  BASE_URL: "https://k7d202.p.ssafy.io/api",
}

if (!process.env.NODE_ENV || process.env.NODE_ENV === "development") {
  config.BASE_URL = process.env.REACT_APP_API_URL
}

// headers 설정
const headers = {
  "Content-Type": "application/json;charset=UTF-8",
  Accept: "*/*",
  "Access-Control-Allow-Origin": "*",
  crossDomain: true,
  credentials: "include",
  withCredentials: true,
}
console.log("headers: ", headers)
/**
 * axios instance 생성
 *
 * oauth 시에는 axios import 해서 바로 사용하기
 * koflowa 백엔드 요청시에만 아래 정의한 api 사용
 */

export const api = (accessToken) => {
  if (accessToken) {
    return axios.create({
      baseURL: process.env.REACT_APP_API_URL,
      headers: {
        ...headers,
        Authorization: "Bearer " + accessToken,
      },
    })
  } else {
    return axios.create({
      baseURL: process.env.REACT_APP_API_URL,
      headers: {
        ...headers,
      },
    })
  }
}

export const image_api = (accessToken) =>
  axios.create({
    baseURL: process.env.REACT_APP_API_URL,
    headers: {
      Accept: "*/*",
      "Access-Control-Allow-Origin": "*",
      crossDomain: true,
      credentials: "include",
      withCredentials: true,
      Authorization: "Bearer " + accessToken,
      // "Content-Type": "multipart/form-data",
    },
  })

export const elastic_api = () => {
  return axios.create({
    baseURL: "http://k7d202.p.ssafy.io:9200/koflowa_tag_ranking/_search",
    headers: {
      "Content-Type": "application/json;charset=UTF-8",
    },
    data: {
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
    },
  })
}

export default api
