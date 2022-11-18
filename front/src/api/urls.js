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

export const GOOGLE_REGISTER_SET_NICKNAME = process.env.REACT_APP_API_URL + "/nickname/"

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
export const question = QUESTION
export const questionTag = (tagName) => QUESTION + `/tag/${tagName}`
export const singleQuestionData = (questionSeq) => QUESTION + `/${questionSeq}`
export const questionUpdown = QUESTION + `/updown`
export const getQuestionUpdownUrl = (questionSeq) => QUESTION + `/updown/${questionSeq}`

// ANSWER
export const createAnswerUrl = (questionSeq) => ANSWER + `/${questionSeq}`
export const editAnswerUrl = (answerSeq) => ANSWER + `/${answerSeq}`
export const getAnswerDetailUrl = (answerSeq) => ANSWER + `/detail/${answerSeq}`
export const commentUrl = ANSWER + `/comment`
export const answerUpDownUrl = (answerSeq) => ANSWER + `/updown/${answerSeq}`
export const answerAcceptUrl = (answerSeq) => ANSWER + `/accept/${answerSeq}`
export const getAnswerCommentUrl = (answerSeq) => ANSWER + `/comment/${answerSeq}`
export const getAnswerListUrl = (questionSeq) => ANSWER + `/${questionSeq}`

// TAGS
export const allTagsData = TAGS
export const singleTagData = (tagName) => TAGS + `/${tagName}`
export const registTag = TAGS + "/regist"
export const watchTag = (tagName) => TAGS + `/watch/${tagName}`
export const ignoreTag = (tagName) => TAGS + `/ignore/${tagName}`
export const watchTagList = TAGS + `/watch`
export const ignoreTagList = TAGS + `/ignore`
export const allTagsStringList = TAGS + `/list`
export const rankingTag = () => "/koflowa_tag_ranking/_search"
export const allTag = () => "/koflowa_tag/_search"
// MEETING

// TALK

//SIGN
export const auth = AUTH
export const getNickname = (nickname) => NICKNAME + `/${nickname}`
