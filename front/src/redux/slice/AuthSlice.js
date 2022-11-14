import { createSlice } from "@reduxjs/toolkit"
// import axios from "axios"

export const AuthSlice = createSlice({
  name: "AuthSlice",
  initialState: {
    token: localStorage.getItem("token"),
    // isAuthenticated: null,
    isAuthenticated: true,
    loading: true,
    user: null,
    isEdit: false,
  },
  reducers: {
    setToken: (state, action) => {
      state.token = action.payload
    },
    setUser: (state, action) => {
      state.user = action.payload
    },
    setIsEdit: (state, action) => {
      state.isEdit = action.payload
    },
  },
})

export const { setToken, setUser, setIsEdit } = AuthSlice.actions

export const selectToken = (state) => state.auth.token
export const selectUser = (state) => state.auth.user
export const selectEdit = (state) => state.auth.isEdit
export default AuthSlice.reducer
