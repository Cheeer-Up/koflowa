import { createSlice } from "@reduxjs/toolkit"

export const AuthSlice = createSlice({
  name: "AuthSlice",
  initialState: {
    token: localStorage.getItem("token"),
    isAuthenticated: false,
    loading: true,
    user: {
      authProvider: "",
      email: "",
      name: "",
      nickname: "",
      profile: "",
      role: "",
      seq: "",
    },
    isEdit: false,
    file: null,
    newInfo: null,
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
    setIsAuthenticated: (state) => {
      state.isAuthenticated = !state.isAuthenticated
    },
    setFile: (state, action) => {
      state.file = action.payload
    },
    setNewInfo: (state, action) => {
      state.newInfo = action.payload
    },
    setChangeImage: (state, action) => {
      state.user.profile = action.payload
    },
    setChangeInfo: (state, action) => {
      console.log(action.payload)
    },
  },
})

export const { setToken, setUser, setIsEdit, setIsAuthenticated, setFile, setNewInfo, setChangeImage, setChangeInfo } =
  AuthSlice.actions

export const selectToken = (state) => state.auth.token
export const selectUser = (state) => state.auth.user
export const selectEdit = (state) => state.auth.isEdit
export const selectIsAuthenticated = (state) => state.auth.isAuthenticated
export const selectFile = (state) => state.auth.file
export const selectNewInfo = (state) => state.auth.newInfo

export default AuthSlice.reducer
