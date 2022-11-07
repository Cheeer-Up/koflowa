import { createSlice } from "@reduxjs/toolkit"
import { loadUserData, registerUser, loginUser } from "../../api/authApi"
// import axios from "axios"

export const AuthSlice = createSlice({
  name: "AuthSlice",
  initialState: {
    token: localStorage.getItem("token"),
    isAuthenticated: null,
    loading: true,
    user: null,
  },
  reducers: {
    USER_LOADED: (state, action) => {
      ;(state.user = action.payload), (state.isAuthenticated = true), (state.loading = false)
    },
    LOGIN_SUCCESS: (state, action) => {
      localStorage.setItem("token", action.payload.token)
      ;(state.isAuthenticated = true), (state.loading = false)
    },
    LOGOUT: () => {
      localStorage.removeItem("token")
      ;(state.token = null), (state.isAuthenticated = false), (state.loading = false)
    },
  },
})

export const { USER_LOADED, LOGIN_SUCCESS, LOGOUT } = AuthSlice.actions

// Load User
export const loadUser = () => async (dispatch) => {
  if (localStorage.token) {
    setAuthToken(localStorage.token)
  }
  try {
    const res = await loadUserData()

    dispatch({
      type: USER_LOADED,
      payload: res.data.data,
    })
  } catch (err) {
    dispatch({
      type: AUTH_ERROR,
    })
  }
}

// Register User
export const register =
  ({ username, password }) =>
  async (dispatch) => {
    try {
      const res = await registerUser(username, password)

      dispatch({
        type: REGISTER_SUCCESS,
        payload: res.data.data,
      })

      dispatch(loadUser())
    } catch (err) {
      dispatch({
        type: REGISTER_FAIL,
      })
    }
  }

// Login User
export const login =
  ({ username, password }) =>
  async (dispatch) => {
    try {
      const res = await loginUser(username, password)

      dispatch({
        type: LOGIN_SUCCESS,
        payload: res.data.data,
      })

      dispatch(loadUser())
    } catch (err) {
      dispatch({
        type: LOGIN_FAIL,
      })
    }
  }

//LOGOUT
export const logout = () => (dispatch) => {
  localStorage.removeItem("token")

  dispatch({ type: LOGOUT })
}

export default AuthSlice.reducer
