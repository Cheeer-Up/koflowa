import { createSlice } from "@reduxjs/toolkit"

const initialState = {
  loading: true,
  redirect: false,
  error: {},
}

const QuestionSlice = createSlice({
  name: "QuestionSlice",
  initialState,
  reducers: {
    getQuestions: (state, action) => {
      state.loading = false
      state.redirect = false
      return action.payload
    },
    getQuestion: () => {},
    postQuestion: () => {},
  },
})

export const { getQuestions } = QuestionSlice.actions
export default QuestionSlice.reducer
