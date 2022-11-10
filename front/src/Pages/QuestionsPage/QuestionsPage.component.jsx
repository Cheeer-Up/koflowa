import React, { Fragment, useEffect, useState } from "react"
import { useLocation } from "react-router-dom"
// import PropTypes from "prop-types"
import handleSorting from "utils/handleSorting"

import LinkButton from "components/Components/LinkButton/LinkButton.component"
import PostItem from "components/Components/PostItem/PostItem.component"
import Spinner from "components/Components/Spinner/Spinner.component"
import ButtonGroup from "components/Components/ButtonGroup/ButtonGroup.component"
import SearchBox from "components/Components/SearchBox/SearchBox.component"
import Pagination from "components/Layouts/Pagination/Pagination.component"
import { getQuestionsData } from "api/question"

import "./QuestionsPage.styles.scss"

const itemsPerPage = 10

const QuestionsPage = () => {
  const [page, setPage] = useState(1)
  // console.log(page)
  const [posts, setPosts] = useState(null)

  getQuestionsData(page - 1, itemsPerPage).then((res) => {
    setPosts(res.data.result.data.content)
  })

  const [sortType, setSortType] = useState("Newest")

  let searchQuery = new URLSearchParams(useLocation().search).get("search")

  const handlePaginationChange = (e, value) => {
    setPage(value)
    setPosts(getQuestionsData(page - 1, itemsPerPage))
  }

  return posts === null ? (
    <Spinner type='page' width='75px' height='200px' />
  ) : (
    <Fragment>
      <div id='mainbar' className='questions-page fc-black-800'>
        <div className='questions-grid'>
          <h3 className='questions-headline'>{searchQuery ? "검색 결과" : "모든 질문"}</h3>
          <div className='questions-btn'>
            <LinkButton text={"질문 하기"} link={"/add/question"} type={"s-btn__primary"} />
          </div>
        </div>
        {searchQuery ? (
          <div className='search-questions'>
            <span style={{ color: "#acb2b8", fontSize: "12px" }}>Results for {searchQuery}</span>
            <SearchBox placeholder={"검색..."} name={"search"} pt={"mt8"} />
          </div>
        ) : (
          ""
        )}
        <div className='questions-tabs'>
          <span>{new Intl.NumberFormat("en-IN").format(posts.length)} 질문글</span>
          <ButtonGroup
            buttons={["최신순", "인기순", "조회순", "오래된순"]}
            // buttons={["Newest", "Top", "Views", "Oldest"]}
            selected={sortType}
            setSelected={setSortType}
          />
        </div>
        <div className='questions'>
          {/* {posts
            .filter((post) => post.title.toLowerCase().includes(searchQuery ? searchQuery : ""))
            ?.sort(handleSorting(sortType))
            .slice((page - 1) * itemsPerPage, (page - 1) * itemsPerPage + itemsPerPage)
            .map((post, index) => (
              <PostItem key={index} post={post} />
            ))} */}
        </div>
        <Pagination
          page={page}
          itemList={posts.filter((post) =>
            post.title.toLowerCase().includes(searchQuery ? searchQuery : "")
          )}
          itemsPerPage={itemsPerPage}
          handlePaginationChange={handlePaginationChange}
        />
      </div>
    </Fragment>
  )
}

// QuestionsPage.propTypes = {
// getPosts: PropTypes.func.isRequired,
// post: PropTypes.object.isRequired,
// }

export default QuestionsPage
