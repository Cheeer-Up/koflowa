import React, { useEffect, Fragment, useState } from "react"
import PropTypes from "prop-types"
import { Link } from "react-router-dom"

import TagsWidgetItem from "./TagsWidgetItem.component"

import "./TagsWidget.styles.scss"
import { getRankingTags } from "api/tags"
import axios from "axios"
import { EastTwoTone } from "@mui/icons-material"

const TagsWidget = () => {
  const [tags, setTags] = useState([])
  const loading = false // test
  useEffect(() => {
    getRankingTags()
      .then((res) => {
        let datas = res.data.hits.hits
        console.log(datas)
        let gatherTags = []
        if (datas !== null) {
          datas.map(({ _source }) =>
            gatherTags.push({
              tag_seq: _source.tag_seq,
              tag_count: _source.count,
              tag_name: _source.tag_name,
            })
          )
          setTags(gatherTags)
        }
      })
      .catch((err) => {
        console.log(err)
      })
  }, [])
  return (
    <Fragment>
      <div className='side-bar-tags'>
        <h4 className='tag-headline'>Top Tags</h4>
        {tags.map((tag, idx) => {
          return <TagsWidgetItem key={idx} tag_name={tag.tag_name} posts_count={tag.tag_count} />
        })}
        <Link className='show-tags' to='/tags'>
          show more tags
        </Link>
      </div>
    </Fragment>
  )
}

export default TagsWidget
