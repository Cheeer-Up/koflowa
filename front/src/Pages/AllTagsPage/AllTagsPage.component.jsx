import React, { Fragment, useState, useEffect } from "react"
import { getTags } from "redux/slice/TagSlice"

import TagPanel from "Pages/AllTagsPage/TagPanel/TagPanel.component"
import Spinner from "components/Components/Spinner/Spinner.component"
import SearchBox from "components/Components/SearchBox/SearchBox.component"
import ButtonGroup from "components/Components/ButtonGroup/ButtonGroup.component"
import Pagination from "components/Layouts/Pagination/Pagination.component"
import { useSelector, useDispatch } from "react-redux"
import "./AllTagsPage.styles.scss"
import { getAllTagsData } from "api/tags"

const itemsPerPage = 12

const AllTagsPage = () => {
  const { tags, loading } = useSelector((state) => ({
    tags: state.tag.tags,
    loading: state.tag.loading,
  }))
  const dispatch = useDispatch()

  useEffect(() => {
    getAllTagsData().then((result) => {
      console.log("result", result)
      dispatch(getTags(result.data))
    })
  }, [])

  const [page, setPage] = useState(1)
  const [fetchSearch, setSearch] = useState("")
  const [sortType, setSortType] = useState("Popular")

  const handleChange = (e) => {
    e.preventDefault()
    setSearch(e.target.value)
    setPage(1)
  }

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
          <span>{new Intl.NumberFormat("en-IN").format(tags.length)} 개의 태그들</span>
        </div>
        <div className='tags-box pl16 pr16 pb16'>
          <SearchBox placeholder={"태그 명으로 검색"} handleChange={handleChange} width={"200px"} />
          <ButtonGroup
            buttons={["인기순", "이름순", "최신순"]}
            selected={sortType}
            setSelected={setSortType}
          />
        </div>
        <div className='user-browser'>
          <div className='grid-layout'>
            {tags.map((tag, index) => (
              <TagPanel key={index} tag={tag} />
            ))}
          </div>
        </div>
        {/* <Pagination
          page={page}
          itemList={tags.filter((tag) =>
            tag.name.toLowerCase().includes(fetchSearch.toLowerCase())
          )}
          itemsPerPage={itemsPerPage}
          handlePaginationChange={handlePaginationChange}
        /> */}
      </div>
    </Fragment>
  )
}

export default AllTagsPage
