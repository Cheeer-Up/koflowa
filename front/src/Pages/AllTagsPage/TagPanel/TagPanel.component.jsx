import React from "react"
import moment from "moment"
import { connect } from "react-redux"
import TagBadge from "components/Components/TagBadge/TagBadge.component"

const TagPanel = ({ tag: { name, description, createdTime } }) => {
  return (
    <div className='grid--item s-card js-tag-cell d-flex fd-column'>
      <div className='d-flex jc-space-between ai-center mb12'>
        <TagBadge tag_name={name} size={"s-tag"} float={"left"} />
      </div>

      <div className='flex--item fc-medium mb12 v-truncate4' dangerouslySetInnerHTML={{ __html: description }}></div>

      <div className='mt-auto d-flex jc-space-between fs-caption fc-black-400'>
        <div className='flex--item s-anchors s-anchors__inherit'>
          {moment(createdTime).fromNow(false)}
        </div>
      </div>
    </div>
  )
}

export default connect(null)(TagPanel)
