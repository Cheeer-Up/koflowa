import { createSlice } from "@reduxjs/toolkit"

export const agSlice = createSlice({
  name: "TagSlice",
  initialState: {
    tags: [],
    tag: null,
    loading: true,
    redirect: false,
    error: {},
  },
  reducers: {
    getTag: (state, action) => {
      state.tag = action.payload
      state.loading = false
      state.redirect = false
    },
    getTags: (state, action) => {
      state.tags = action.payload
      state.loading = false
      state.redirect = false
    },
    tagErr: (state, action) => {
      state.error = action.payload
      state.loading = false
      state.redirect = true
    },
  },
})

export const { getTag, getTags, tagErr } = agSlice.actions

// computed 같은친구
// export const selectY = (state) => state.char.y

export default agSlice.reducer
