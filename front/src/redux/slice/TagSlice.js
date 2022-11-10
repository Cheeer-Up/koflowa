import { createSlice } from "@reduxjs/toolkit"

const initialState = {
  tags: [],
  tag: null,
  loading: true,
  redirect: false,
  error: {},
}

const TagSlice = createSlice({
  name: "TagSlice",
  initialState,
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

export const { getTag, getTags, tagErr } = TagSlice.actions
export default TagSlice.reducer

// computed 같은친구
// export const selectY = (state) => state.char.y
