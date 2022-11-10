import { createSlice } from "@reduxjs/toolkit"

const initialState = {
  loading: true,
  redirect: false,
  error: {},
}

const TalkSlice = createSlice({
  name: "TalkSlice",
  initialState,
  reducers: {},
})

export const {} = TalkSlice.actions
export default TalkSlice.reducer
