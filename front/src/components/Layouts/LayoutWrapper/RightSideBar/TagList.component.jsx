import React, { Fragment } from "react"

function TagList(props) {
  const tagsListDiv = []
  for (const tag of props.tags) {
    tagsListDiv.push(
      <span key={tag.id} className='tag-wrapper ReactTags__tag' styles='opacity: 1;'>
        {tag.text}
      </span>
    )
  }

  return (
    <Fragment>
      <div className='ReactTags__selected'>{tagsListDiv}</div>
    </Fragment>
  )
}

export default TagList
