import React, { Fragment, useState, useEffect } from "react"

import TagPanel from "Pages/AllTagsPage/TagPanel/TagPanel.component"
import Spinner from "components/Components/Spinner/Spinner.component"
import LinkButton from "components/Components/LinkButton/LinkButton.component"
import "./AllTagsPage.styles.scss"
import Pagination from "components/Layouts/Pagination/Pagination.component"
import { getAllTagsData, getAllTags } from "api/tags"

const itemsPerPage = 12

const AllTagsPage = () => {
  const [tags, setTags] = useState([])
  const [page, setPage] = useState(1)
  const [totalPages, setTotalPages] = useState(1)

  useEffect(() => {
    getAllTags({
      from: (page - 1) * itemsPerPage,
      size: 12,
      sort: "created_time",
      order: "desc",
    }).then((res) => {
      const datas = res.data.hits.hits
      console.log("res: ", res)
      setTotalPages(Math.ceil(res.data.hits.total.value))
      console.log("getAllTags: ", res.data.hits.hits)
      const parse = []
      datas.map((data) =>
        parse.push({
          name: data._source.tag_name,
          description: data._source.tag_description,
          createdTime: data._source.created_time,
        })
      )
      setTags(parse)
    })
  }, [page])

  const handlePaginationChange = (e, value) => {
    console.log(value)
    setPage(value)
  }

  return tags === null ? (
    <Spinner type='page' width='75px' height='200px' />
  ) : (
    <Fragment>
      <div id='mainbar' className='tags-page fc-black-800'>
        <div className='tags-title'>
          <h1 className='headline'>태그</h1>
          <div className='questions-btn'>
            <LinkButton text={"태그 생성하기"} link={"/add/tag"} type={"s-btn__primary"} />
          </div>
        </div>
        <p className='fs-body'>올바른 태그를 사용하면 다른 사람들이 당신의 질문을 더 쉽게 찾고 답변할 수 있습니다.</p>
        <div className='headline-count'>
          <span>{new Intl.NumberFormat("en-IN").format(totalPages)} 개의 태그들</span>
        </div>
        <div className='tags-box pl16 pr16 pb16'></div>
        <div className='user-browser'>
          <div className='grid-layout'>
            {tags.map((tag, index) => (
              <TagPanel key={index} tag={tag} />
            ))}
          </div>
        </div>
        <Pagination page={page} count={Math.ceil(totalPages / 12)} handlePaginationChange={handlePaginationChange} />
      </div>
    </Fragment>
  )
}

export default AllTagsPage
