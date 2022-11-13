import React, { Fragment, useEffect, useState } from "react"

import { redirect } from "react-router-dom"
import LinkButton from "components/Components/LinkButton/LinkButton.component"
import PostItem from "components/Components/PostItem/PostItem.component"
import Spinner from "components/Components/Spinner/Spinner.component"
import handleSorting from "utils/handleSorting"
import Pagination from "components/Layouts/Pagination/Pagination.component"
import ButtonGroup from "components/Components/ButtonGroup/ButtonGroup.component"
import handleFilter from "utils/handleFilter"

import "./HomePage.styles.scss"

import { signIn_Out } from "../../api/sign"
import { useDispatch, useSelector } from "react-redux"
import { setUser, selectUser } from "../../redux/slice/AuthSlice"
import axios from "axios"

//redux 사용하기 위한 함수

// import { setY, selectY } from "../../redux/slice/CharSlice"

const itemsPerPage = 10

const HomePage = () => {
  const [posts, setposts] = useState(null)

  const [page, setPage] = useState(1)
  const [sortType, setSortType] = useState("Month")
  const dispatcher = useDispatch()
  const [userState, setUserState] = useState(useSelector(selectUser))

  useEffect(() => {
    const token = getUrlParameter("token")
    const error = getUrlParameter("error")

    // token
    if (token) {
      localStorage.setItem("accessToken", token)
      localStorage.setItem("refreshToken", null)

      if (!localStorage.getItem("accessToken")) {
        return Promise.reject("No access token set.")
      }
      signIn_Out(token)
        .then(({ data }) => {
          console.log(data)
          let information = data.information
          let user = {
            authProvider: information.authProvider,
            email: information.email,
            name: information.name,
            profile: information.profile,
            role: information.role,
            seq: information.seq,
          }
          dispatcher(setUser(user))
          setUserState(user)
          // 로그인 데이터만 받아온 상황.
          // 헤더에 적용시켜주기 위해 리로드
          // window.location.href 같은 즉시 이동은 redux 저장이나 state 저장 전에 실행
          // setTimeOut으로 조절
          setTimeout(() => {
            window.location.href = "/"
          }, 50)
        })
        .catch((err) => {
          console.log(err)
        })
    }
  }, [])

  /**token을 받아올 수 있는 주소를 parsing */
  const getUrlParameter = (name) => {
    name = name.replace(/[\\[]/, "\\[").replace(/[\]]/, "\\]")
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)")
    console.log("regex: ", regex)
    var results = regex.exec(window.location.search)
    console.log("results : ", results)

    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "))
  }

  const handlePaginationChange = (e, value) => setPage(value)

  return posts === null ? (
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
  // getPosts: PropTypes.func.isRequired,
  // post: PropTypes.object.isRequired,
}

export default HomePage
