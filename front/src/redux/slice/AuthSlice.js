import { createSlice } from "@reduxjs/toolkit"
import { loadUserData, registerUser, loginUser } from "../../api/authApi"
// import axios from "axios"

export const AuthSlice = createSlice({
  name: "AuthSlice",
  initialState: {
    token: localStorage.getItem("token"),
    // isAuthenticated: null,
    isAuthenticated: true,
    loading: true,
    user: null,
  },
  reducers: {},
})

export const {} = AuthSlice.actions

export default AuthSlice.reducer
