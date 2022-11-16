import React, { Fragment } from "react"

function TagList(props) {
  const tagsListDiv = []
  for (const tag of props.tags) {
    tagsListDiv.push(
      <span className='tag-wrapper ReactTags__tag' styles='opacity: 1;'>
        {tag.text}
      </span>
    )
  }

  return (
    <Fragment>
      <div class='ReactTags__selected'>{tagsListDiv}</div>
    </Fragment>
  )
}

export default TagList
