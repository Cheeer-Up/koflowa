import { createSlice } from "@reduxjs/toolkit"

const initialState = {
  answers: [],
  answer: {},
  loading: true,
  redirect: false,
  error: {},
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
  },
})

export const selectAnswers = (state) => state.tag.tags
export const selectAnswer = (state) => state.tag.tag

export const {} = AnswerSlice.actions
export default AnswerSlice.reducer
