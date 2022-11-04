import React, { Fragment, useEffect, useState } from "react"
import { connect } from "react-redux"
// useSelector, useDispatch - redux
import PropTypes from "prop-types"

import { getPosts } from "../../redux/posts/posts.actions"
import LinkButton from "../../components/Components/LinkButton/LinkButton.component"
import PostItem from "../../components/Components/PostItem/PostItem.component"
import Spinner from "../../components/Components/Spinner/Spinner.component"
import handleSorting from "../../utils/handleSorting"
import Pagination from "../../components/Layouts/Pagination/Pagination.component"
import ButtonGroup from "../../components/Components/ButtonGroup/ButtonGroup.component"
import handleFilter from "../../utils/handleFilter"

import "./HomePage.styles.scss"

//redux 사용하기 위한 함수

// import { setY, selectY } from "../../redux/slice/CharSlice"

const itemsPerPage = 10

const HomePage = ({ getPosts, post: { posts, loading } }) => {
  // const dispatcher = useDispatch()

  // const [charX, setCharX] = useState(useSelector(selectY))
  // setCharX(400)
  // dispatcher(setY(charX))

  useEffect(() => {
    getPosts()
  }, [getPosts])

  const [page, setPage] = useState(1)
  const [sortType, setSortType] = useState("Month")

  const handlePaginationChange = (e, value) => setPage(value)

  return loading || posts === null ? (
    <Spinner type='page' width='75px' height='200px' />
  ) : (
    <Fragment>
      <div id='mainbar' className='homepage fc-black-800'>
        <div className='questions-grid'>
          <h3 className='questions-headline'>주요 질문</h3>
          <div className='questions-btn'>
            <LinkButton text={"질문 하기"} link={"/add/question"} type={"s-btn__primary"} />
          </div>
        </div>
        <div className='questions-tabs'>
          <span>{new Intl.NumberFormat("en-IN").format(posts.length)} 질문 글</span>
          <div className='btns-filter'>
            <ButtonGroup
              // buttons={["일간", "주간", "월간", "년간"]}
              buttons={["Today", "Week", "Month", "Year"]}
              selected={sortType}
              setSelected={setSortType}
            />
          </div>
        </div>
        <div className='questions'>
          <div className='postQues'>
            {posts
              .sort(handleSorting(sortType))
              .filter(handleFilter(sortType))
              .slice((page - 1) * itemsPerPage, (page - 1) * itemsPerPage + itemsPerPage)
              .map((post, index) => (
                <PostItem key={index} post={post} />
              ))}
          </div>
        </div>
        <Pagination
          page={page}
          itemList={posts.sort(handleSorting(sortType)).filter(handleFilter(sortType))}
          itemsPerPage={itemsPerPage}
          handlePaginationChange={handlePaginationChange}
        />
      </div>
    </Fragment>
  )
}

HomePage.propTypes = {
  getPosts: PropTypes.func.isRequired,
  post: PropTypes.object.isRequired,
}

const mapStateToProps = (state) => ({
  post: state.post,
})

export default connect(mapStateToProps, { getPosts })(HomePage)
