import { configureStore, combineReducers } from "@reduxjs/toolkit"

//redux-persist 관련 함수
import { persistReducer, persistStore } from "redux-persist"

//thunk = 실행을 지연시켜줌
import thunk from "redux-thunk"

//storageSession = 세션스토리지에 저장
import storage from "redux-persist/lib/storage/session"

//redux 관리 데이터
import AuthSlice from "./slice/AuthSlice"
import TagSlice from "./slice/TagSlice"
import MeetingSlice from "./slice/MeetingSlice"
import TalkSlice from "./slice/TalkSlice"
import QuestionSlice from "./slice/QuestionSlice"
import MypageSlice from "./slice/MypageSlice"

//persist 설정
const persistConfig = {
  key: "root",
  storage,
  debug: true,
}

//rootReducer = 조합된 최종 리듀서
const rootReducer = combineReducers({
  tag: TagSlice,
  meeting: MeetingSlice,
  talk: TalkSlice,
  question: QuestionSlice,
  auth: AuthSlice,
  mypage: MypageSlice,
})

//persistReducer(설정, 최종 리듀서)
const persistedReducer = persistReducer(persistConfig, rootReducer)

export const store = configureStore({
  reducer: persistedReducer,
  middleware: [thunk],
})

//index.js에 사용함
export const persistor = persistStore(store)
