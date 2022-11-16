const USERS = "/users"
const MYPAGES = "/my-pages"
const QUESTION = "/question"
const ANSWER = "/answer"
const TAGS = "/tags"
const MEETING = "/meeting"
const TALK = "/talk"
const AUTH = "/auth"
const GOOGLE = "google"
const NICKNAME = "/nickname"
// USERS
export const usersData = "/users"
export const profileData = "/users/{id}"
export const slash = "/"

export const GOOGLE_AUTH_URL =
  process.env.REACT_APP_API_URL +
  "/oauth2/authorize/" +
  GOOGLE +
  "?redirect_uri=" +
  process.env.REACT_APP_PUBLIC_URL +
  "/nickname"

export const GOOGLE_REGISTER_SET_NICKNAME =
  process.env.REACT_APP_API_URL + 
  "/nickname/"

// MYPAGES
export const allProfile = (page, size, sort) => MYPAGES + `/profile?page=${page}&size=${size}&sort=${sort}`
export const userProfile = (userSeq) => MYPAGES + `/profile/${userSeq}`
export const myProfile = MYPAGES + "/profile"
export const myImage = MYPAGES + "/profile/image"
export const userTags = (userSeq) => MYPAGES + `/tags/${userSeq}`
export const userReputation = (page, size, sort, userSeq) =>
  MYPAGES + `/reputation/${userSeq}?page=${page}&size=${size}&sort=${sort}`
export const userQuestion = (page, size, sort, userSeq) =>
  MYPAGES + `/question/${userSeq}?page=${page}&size=${size}&sort=${sort}`
export const userAnswer = (page, size, sort, userSeq) =>
  MYPAGES + `/answer/${userSeq}?page=${page}&size=${size}&sort=${sort}`

// QUESTION
export const createQuestion = QUESTION // 질문 생성 POST 요청
export const getQuestions = (page, size) => QUESTION + `/${page}/${size}` // 해당 페이지의 질문 조회 GET 요청
export const getQuestion = (questionSeq) => QUESTION + `/${questionSeq}`

// ANSWER
export const createAnswer = (questionSeq) => ANSWER + `/${questionSeq}`
export const editAnswer = (answerSeq) => ANSWER + `/${answerSeq}`
export const getAnswerDetail = (answerSeq) => ANSWER + `/detail/${answerSeq}`
export const comment = ANSWER + `/comment`
export const answerUpDown = (answerSeq) => ANSWER + `/updown/${answerSeq}`
export const answerAccept = (answerSeq) => ANSWER + `/accept/${answerSeq}`
export const getAnswerComment = (answerSeq) => ANSWER + `/comment/${answerSeq}`
export const getAnswerList = (questionSeq, page, size) => ANSWER + `/${questionSeq}/${page}/${size}`

// TAGS
export const allTagsData = TAGS
export const singleTagData = (tagName) => TAGS + `/${tagName}`
export const registTag = TAGS + "/regist"
export const watchTag = (tagSeq) => TAGS + `/watch/${tagSeq}`
export const ignoreTag = (tagSeq) => TAGS + `/ignore/${tagSeq}`

// MEETING

// TALK

//SIGN
export const auth = AUTH
export const getNickname = (nickname) => NICKNAME + `/${nickname}`
