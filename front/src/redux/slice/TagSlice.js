import { createSlice } from "@reduxjs/toolkit"

const initialState = {
  tags: [],
}

const TagSlice = createSlice({
  name: "TagSlice",
  initialState,
  reducers: {
    setTags: (state, action) => {
      state.tags = action.payload
    },
  },
})

export const selectTags = (state) => state.tag.tags

export const { setTags } = TagSlice.actions
export default TagSlice.reducer
