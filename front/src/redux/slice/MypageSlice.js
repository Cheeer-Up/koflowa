import { createSlice } from "@reduxjs/toolkit"

const initialState = {
  loading: true,
  redirect: false,
  error: {},
}

const MypageSlice = createSlice({
  name: "MypageSlice",
  initialState,
  reducers: {},
})

export const {} = MypageSlice.actions
export default MypageSlice.reducer
