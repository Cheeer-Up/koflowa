import React from "react"

import TagBadge from "components/Components/TagBadge/TagBadge.component"

import "./UserActivity.styles.scss"

const UserTag = ({ tag }) => (
  <div className='top-tags-cells'>
    <div className='top-cell'>
      <div className='tag-cell bg-black-025'>
        <TagBadge tag_name={tag.tagName} size={"s-tag s-tag__md"} float={"left"} />
        <div className='score'>
          <div className='score-txt'>
            <div className='score-tab'>
              <span className='txt fc-light'>Posts</span>
              <span className='number fc-black-800'>{tag.cnt}</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
)

export default UserTag
