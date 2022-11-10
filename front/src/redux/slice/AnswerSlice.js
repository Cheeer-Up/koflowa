import { createSlice } from "@reduxjs/toolkit"

const initialState = {
  loading: true,
  redirect: false,
  error: {},
}

const AnswerSlice = createSlice({
  name: "AnswerSlice",
  initialState,
  reducers: {},
})

export const {} = AnswerSlice.actions
export default AnswerSlice.reducer
