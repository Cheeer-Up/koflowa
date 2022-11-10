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

/**
 * axios instance 생성
 *
 * oauth 시에는 axios import 해서 바로 사용하기
 * koflowa 백엔드 요청시에만 아래 정의한 api 사용
 */
const api = axios.create({
  baseURL: config.BASE_URL,
  headers,
})

export default api