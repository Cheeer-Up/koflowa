import { createSlice } from "@reduxjs/toolkit"

const initialState = {
  loading: true,
  redirect: false,
  error: {},
}

const MeetingSlice = createSlice({
  name: "MeetingSlice",
  initialState,
  reducers: {},
})

export const {} = MeetingSlice.actions
export default MeetingSlice.reducer
