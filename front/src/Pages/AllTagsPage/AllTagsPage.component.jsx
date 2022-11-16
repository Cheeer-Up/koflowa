import React, { Fragment, useState, useEffect } from "react"

import TagPanel from "Pages/AllTagsPage/TagPanel/TagPanel.component"
import Spinner from "components/Components/Spinner/Spinner.component"
import LinkButton from "components/Components/LinkButton/LinkButton.component"
import "./AllTagsPage.styles.scss"
import Pagination from "components/Layouts/Pagination/Pagination.component"
import { getAllTagsData } from "api/tags"

const itemsPerPage = 12

const AllTagsPage = () => {
  const [tags, setTags] = useState([])
  const [page, setPage] = useState(1)
  const [totalPages, setTotalPages] = useState(1)

  useEffect(() => {
    getAllTagsData({
      params: {
        page: page - 1,
        size: itemsPerPage,
        sort: "createdTime,desc",
      },
    }).then((result) => {
      setTags(result.data.result.data.content)
      setTotalPages(result.data.result.data.totalPages)
    })
  }, [page])

  const handlePaginationChange = (e, value) => setPage(value)

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
        <Pagination page={page} count={totalPages} handlePaginationChange={handlePaginationChange} />
      </div>
    </Fragment>
  )
}

export default AllTagsPage
