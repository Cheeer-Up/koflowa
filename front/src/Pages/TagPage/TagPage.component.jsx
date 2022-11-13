import React, { useEffect, Fragment, useState } from "react"
import { Navigate, useParams } from "react-router-dom"

import PostItem from "components/Components/PostItem/PostItem.component"
import LinkButton from "components/Components/LinkButton/LinkButton.component"
import Spinner from "components/Components/Spinner/Spinner.component"
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

  return tag === null || loading ? (
    <Spinner type='page' width='75px' height='200px' />
  ) : (
    <Fragment>
      <div id='mainbar' className='questions-page fc-black-800'>
        <div className='questions-grid'>
          <h3 className='questions-headline'>Questions tagged [{tag.name}]</h3>
          <div className='questions-btn'>
            <LinkButton text={"Ask Question"} link={"/add/question"} type={"s-btn__primary"} />
          </div>
        </div>
        <p className='fs-body' dangerouslySetInnerHTML={{ __html: tag.description }} />
        <div className='questions-tabs'>
          <span>
            {new Intl.NumberFormat("en-IN").format(tag.questionCount)}{" "}
            {tag.questionCount === 1 ? "question" : "questions"}
          </span>
        </div>
        <div className='questions'>
          {tag.questionCount === 0 ? (
            <h4 style={{ margin: "30px 30px" }}>There are no questions from this tag</h4>
          ) : (
            tag.questions?.map((question, index) => <PostItem key={index} question={question} />)
          )}
        </div>
      </div>
    </Fragment>
  )
}

export default TagPage
