import { createSlice } from "@reduxjs/toolkit"

const initialState = {
  answers: [],
  answer: {},
  loading: true,
  redirect: false,
  error: {},
  isEdit: false,
}

const AnswerSlice = createSlice({
  name: "AnswerSlice",
  initialState,
  reducers: {
    setAnswer: (state, action) => {
      state.tag = action.payload
      state.loading = false
      state.redirect = false
    },
    setAnswers: (state, action) => {
      state.tag = action.payload
      state.loading = false
      state.redirect = false
    },
    setIsEdit: (state) => {
      state.isEdit = !state.isEdit
    },
  },
})

export const selectAnswers = (state) => state.tag.tags
export const selectAnswer = (state) => state.tag.tag
export const selectEdit = (state) => state.answer.isEdit

export const { setIsEdit } = AnswerSlice.actions
export default AnswerSlice.reducer
