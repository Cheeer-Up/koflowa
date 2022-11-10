import { createSlice } from "@reduxjs/toolkit"

const initialState = {
  loading: true,
  redirect: false,
  error: {},
}

const QuestionSlice = createSlice({
  name: "QuestionSlice",
  initialState,
  reducers: {},
})

export const {} = QuestionSlice.actions
export default QuestionSlice.reducer
