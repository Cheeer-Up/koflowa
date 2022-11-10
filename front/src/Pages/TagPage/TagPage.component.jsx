import React, { useEffect, Fragment, useState } from "react"
import { Navigate, useParams } from "react-router-dom"
import PropTypes from "prop-types"
import handleSorting from "utils/handleSorting"

import LinkButton from "components/Components/LinkButton/LinkButton.component"
import PostItem from "components/Components/PostItem/PostItem.component"
import Spinner from "components/Components/Spinner/Spinner.component"
import ButtonGroup from "components/Components/ButtonGroup/ButtonGroup.component"
import { useSelector, useDispatch } from "react-redux"
import "./TagPage.styles.scss"
import { selectLoading, selectTag, setTag } from "redux/slice/TagSlice"
import { getSingleTagData } from "api/tags"

const TagPage = () => {
  let loading = useSelector(selectLoading)
  let tag = useSelector(selectTag)
  const dispatch = useDispatch()
  const { tagName } = useParams()

  useEffect(() => {
    getSingleTagData(tagName).then((result) => {
      dispatch(setTag(result.data))
    })
  }, [])

  const [sortType, setSortType] = useState("Newest")

  if (tag.redirect) {
    return <Navigate to='/tags' />
  }

  return tag.tag === null || tag.loading || loading ? (
    <Spinner type='page' width='75px' height='200px' />
  ) : (
    <Fragment>
      <div id='mainbar' className='questions-page fc-black-800'>
        <div className='questions-grid'>
          <h3 className='questions-headline'>Questions tagged [{tag.tag.name}]</h3>
          <div className='questions-btn'>
            <LinkButton text={"Ask Question"} link={"/add/question"} type={"s-btn__primary"} />
          </div>
        </div>
        <p className='fs-body' dangerouslySetInnerHTML={{ __html: tag.tag.description }} />
        <div className='questions-tabs'>
          <span>
            {new Intl.NumberFormat("en-IN").format(tag.tag.posts_count)}{" "}
            {tag.tag.posts_count === 1 ? "question" : "questions"}
          </span>
          <ButtonGroup
            buttons={["Newest", "Top", "Views", "Oldest"]}
            selected={sortType}
            setSelected={setSortType}
          />
        </div>
        {/* <div className='questions'>
          {tag.tag.posts_count === 0 ? (
            <h4 style={{ margin: "30px 30px" }}>There are no questions from this tag</h4>
          ) : (
            posts
              ?.sort(handleSorting(sortType))
              .map((post, index) => <PostItem key={index} post={post} />)
          )}
        </div> */}
      </div>
    </Fragment>
  )
}

TagPage.propTypes = {
  // getTag: PropTypes.func.isRequired,
  // getTagPosts: PropTypes.func.isRequired,
  // post: PropTypes.object.isRequired,
  // tag: PropTypes.func.isRequired,
}

export default TagPage
