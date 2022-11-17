import React, { useEffect, Fragment, useState } from "react"
import PropTypes from "prop-types"
import { Link } from "react-router-dom"

import TagsWidgetItem from "./TagsWidgetItem.component"

import "./TagsWidget.styles.scss"
import { getTagsRanking } from "api/tags"
import axios from "axios"
import { EastTwoTone } from "@mui/icons-material"

// const { Client } = require("@elastic/elasticsearch")
// const client = new Client({
//   cloud: {
//     id: "<cloud-id>",
//   },
//   auth: {
//     username: "elastic",
//     password: "changeme",
//   },
// })
const data = {
  _source: ["tag_seq", "tag_name", "tag_count"],
  size: 10,
  collapse: {
    field: "tag_seq",
  },
  sort: [
    {
      tag_count: {
        order: "desc",
      },
    },
  ],
}
const TagsWidget = () => {
  const [tags, setTags] = useState([])
  const loading = false // test
  useEffect(() => {
    axios
      .post("http://k7d202.p.ssafy.io:9200/koflowa_tag_ranking/_search", data, {
        headers: {
          "Content-Type": "application/json",
        },
      })
      .then((res) => {
        console.log("axios get: ", res.data.hits.hits)
      })
      .catch((err) => {
        console.log(err)
      })

    // getTagsRanking()
    //   .then((res) => {
    //     let datas = res.data.hits.hits
    //     console.log("getTagsRanking: ", datas)
    //     let gatherTags = []
    //     if (datas !== null) {
    //       console.log(datas)
    //       datas.map(({ _source }) =>
    //         gatherTags.push({
    //           tag_seq: _source.tag_seq,
    //           tag_count: _source.tag_count,
    //           tag_name: _source.tag_name,
    //         })
    //       )
    //       setTags(gatherTags)
    //     }

    //     console.log("getTagsRanking: ", res)
    //   })
    //   .catch((err) => {
    //     console.log(err)
    //   })
  }, [])
  return (
    <Fragment>
      <div className='side-bar-tags'>
        <h4 className='tag-headline'>Top Tags</h4>
        {tags.map((tag, idx) => {
          console.log("tag: ", tag)
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
