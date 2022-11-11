import React, { Fragment, useState, useEffect } from "react"
import { selectTags, selectLoading, setTags } from "redux/slice/TagSlice"

import TagPanel from "Pages/AllTagsPage/TagPanel/TagPanel.component"
import Spinner from "components/Components/Spinner/Spinner.component"
// import SearchBox from "components/Components/SearchBox/SearchBox.component"
// import ButtonGroup from "components/Components/ButtonGroup/ButtonGroup.component"
import { useSelector, useDispatch } from "react-redux"
import "./AllTagsPage.styles.scss"
import Pagination from "components/Layouts/Pagination/Pagination.component"
import { getAllTagsData } from "api/tags"

const itemsPerPage = 12

const AllTagsPage = () => {
  let tags = useSelector(selectTags)
  let loading = useSelector(selectLoading)
  const dispatch = useDispatch()
  const [page, setPage] = useState(1)
  const [totalPages, setTotalPages] = useState(1)
  // const [fetchSearch, setSearch] = useState("")

  useEffect(() => {
    getAllTagsData({
      params: {
        page: page - 1,
        size: itemsPerPage,
        sort: "createdTime,desc",
      },
    }).then((result) => {
      dispatch(setTags(result.data.content))
      setTotalPages(result.data.totalPages)
    })
  }, [page])

  // const handleChange = (e) => {
  //   e.preventDefault()
  //   setSearch(e.target.value)
  //   setPage(1)
  // }

  const handlePaginationChange = (e, value) => setPage(value)

  return loading || tags === null ? (
    <Spinner type='page' width='75px' height='200px' />
  ) : (
    <Fragment>
      <div id='mainbar' className='tags-page fc-black-800'>
        <h1 className='headline'>태그</h1>
        <p className='fs-body'>
          올바른 태그를 사용하면 다른 사람들이 당신의 질문을 더 쉽게 찾고 답변할 수 있습니다.
        </p>
        <div className='headline-count'>
          <span>{new Intl.NumberFormat("en-IN").format(totalPages)} 개의 태그들</span>
        </div>
        <div className='tags-box pl16 pr16 pb16'>
          {/* <SearchBox placeholder={"태그 명으로 검색"} handleChange={handleChange} width={"200px"} /> */}
        </div>
        <div className='user-browser'>
          <div className='grid-layout'>
            {tags.map((tag, index) => (
              <TagPanel key={index} tag={tag} />
            ))}
          </div>
        </div>
        <Pagination
          page={page}
          count={totalPages}
          handlePaginationChange={handlePaginationChange}
        />
      </div>
    </Fragment>
  )
}

export default AllTagsPage
