import { createSlice } from "@reduxjs/toolkit"
// import axios from "axios"

export const AuthSlice = createSlice({
  name: "AuthSlice",
  initialState: {
    token: localStorage.getItem("token"),
    isAuthenticated: false,
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
    setIsAuthenticated: (state) => {
      state.isAuthenticated = !state.isAuthenticated
    },
  },
})

export const { setToken, setUser, setIsEdit, setIsAuthenticated } = AuthSlice.actions

export const selectToken = (state) => state.auth.token
export const selectUser = (state) => state.auth.user
export const selectEdit = (state) => state.auth.isEdit
export default AuthSlice.reducer
