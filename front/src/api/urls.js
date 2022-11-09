const USERS = "/users"
const MYPAGES = "/my-pages"
const QUESTION = "/question"
const ANSWER = "/answer"
const TAGS = "/tags"
const MEETING = "/meeting"
const TALK = "/talk"

// USERS
export const usersData = "/users"
export const profileData = "/users/{id}"

// MYPAGES

// QUESTION

// ANSWER

// TAGS
export const allTagsData = TAGS
export const singleTagData = (tagSeq) => TAGS + `/$(tagSeq)`
export const registTag = TAGS + "/regist"
export const watchTag = (tagSeq) => TAGS + `/watch/$(tagSeq)`
export const ignoreTag = (tagSeq) => TAGS + `/ignore/$(tagSeq)`

// MEETING

// TALK
