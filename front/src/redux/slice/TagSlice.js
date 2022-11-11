import { createSlice } from "@reduxjs/toolkit"

const initialState = {
  tags: [],
  tag: {},
  loading: true,
  redirect: false,
  error: {},
}

const TagSlice = createSlice({
  name: "TagSlice",
  initialState,
  reducers: {
    setTag: (state, action) => {
      state.tag = action.payload
      state.loading = false
      state.redirect = false
    },
    setTags: (state, action) => {
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

export const selectTags = (state) => state.tag.tags
export const selectTag = (state) => state.tag.tag
export const selectLoading = (state) => state.tag.loading

export const { setTag, setTags, tagErr } = TagSlice.actions
export default TagSlice.reducer
