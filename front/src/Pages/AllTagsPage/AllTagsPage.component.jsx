import React, { Fragment, useState } from "react"
// import { getTags } from "redux/tags/tags.actions" //=> slice
import handleSorting from "utils/handleSorting"

import TagPanel from "Pages/AllTagsPage/TagPanel/TagPanel.component"
import Spinner from "components/Components/Spinner/Spinner.component"
import SearchBox from "components/Components/SearchBox/SearchBox.component"
import ButtonGroup from "components/Components/ButtonGroup/ButtonGroup.component"
import Pagination from "components/Layouts/Pagination/Pagination.component"
import { useSelector, useDispatcher } from "react-redux"
import "./AllTagsPage.styles.scss"

const itemsPerPage = 12

const AllTagsPage = (loading) => {
  // const AllTagsPage = ({ getTags, tag: { tags, loading } }) => {
  // useEffect(() => {
  //   getTags()
  // }, [getTags])

  // const [tags, setTags] = useState([])
  const tags = [
    {
      seq: 1,
      name: "java",
      description:
        "썬 마이크로시스템즈에서 1995년에 개발한 객체 지향 프로그래밍 언어. 창시자는 제임스 고슬링이다. 2010년에 오라클이 썬 마이크로시스템즈를 인수하면서 Java의 저작권을 소유하였다. 현재는 OpenJDK는 GPL2이나 오라클이 배포하는 Oracle JDK는 상업라이선스로 2019년 1월부터 유료화정책을 강화하고 있다. Java EE는 이클립스 재단의 소유이다. Java 언어는 J2SE 1.4부터는 Java Community Process (JCP)에서 개발을 주도하고 있다. C#과 문법적 성향이 굉장히 비슷하며[2], 그에 비해 2019년 Q3에서 가장 많이 이용하는 언어로 뽑혔다.",
      createdTime: "2022-11-07T15:23:37.393141",
      modifiedTime: "2022-11-07T15:23:37.393141",
    },
    {
      seq: 2,
      name: "Python",
      description:
        "1991년에 발표된 인터프리터 방식의 프로그래밍 언어. 영어 문법과 비슷해서 읽고 쓰기 쉬운 특유의 문법과, 이 점에 매료된 프로그래머들로부터 만들어진 수많은 패키지들 덕분에 2010년대 중반부터 전 세계에서 가장 많이 사용되는 프로그래밍 언어 중 하나로 떠올랐다.",
      createdTime: "2022-11-07T15:24:34.593238",
      modifiedTime: "2022-11-07T15:24:34.593238",
    },
    {
      seq: 3,
      name: "JavaScript",
      description:
        "Ecma International의 프로토타입 기반의 프로그래밍 언어로, 스크립트 언어에 해당된다. 특수한 목적이 아닌 이상 모든 웹 브라우저에 인터프리터가 내장되어 있다. 오늘날 HTML, CSS와 함께 웹을 구성하는 요소 중 하나다. HTML이 웹 페이지의 기본 구조를 담당하고, CSS가 디자인을 담당한다면 JavaScript는 클라이언트 단에서 웹 페이지가 동작하는 것을 담당한다.[1] 웹 페이지를 자동차에 비유하자면, HTML은 자동차의 뼈대, CSS는 자동차의 외관, JavaScript는 자동차의 동력이라고 볼 수 있다.",
      createdTime: "2022-11-07T15:24:55.105625",
      modifiedTime: "2022-11-07T15:24:55.105625",
    },
    {
      seq: 1,
      name: "java",
      description:
        "썬 마이크로시스템즈에서 1995년에 개발한 객체 지향 프로그래밍 언어. 창시자는 제임스 고슬링이다. 2010년에 오라클이 썬 마이크로시스템즈를 인수하면서 Java의 저작권을 소유하였다. 현재는 OpenJDK는 GPL2이나 오라클이 배포하는 Oracle JDK는 상업라이선스로 2019년 1월부터 유료화정책을 강화하고 있다. Java EE는 이클립스 재단의 소유이다. Java 언어는 J2SE 1.4부터는 Java Community Process (JCP)에서 개발을 주도하고 있다. C#과 문법적 성향이 굉장히 비슷하며[2], 그에 비해 2019년 Q3에서 가장 많이 이용하는 언어로 뽑혔다.",
      createdTime: "2022-11-07T15:23:37.393141",
      modifiedTime: "2022-11-07T15:23:37.393141",
    },
    {
      seq: 2,
      name: "Python",
      description:
        "1991년에 발표된 인터프리터 방식의 프로그래밍 언어. 영어 문법과 비슷해서 읽고 쓰기 쉬운 특유의 문법과, 이 점에 매료된 프로그래머들로부터 만들어진 수많은 패키지들 덕분에 2010년대 중반부터 전 세계에서 가장 많이 사용되는 프로그래밍 언어 중 하나로 떠올랐다.",
      createdTime: "2022-11-07T15:24:34.593238",
      modifiedTime: "2022-11-07T15:24:34.593238",
    },
    {
      seq: 3,
      name: "JavaScript",
      description:
        "Ecma International의 프로토타입 기반의 프로그래밍 언어로, 스크립트 언어에 해당된다. 특수한 목적이 아닌 이상 모든 웹 브라우저에 인터프리터가 내장되어 있다. 오늘날 HTML, CSS와 함께 웹을 구성하는 요소 중 하나다. HTML이 웹 페이지의 기본 구조를 담당하고, CSS가 디자인을 담당한다면 JavaScript는 클라이언트 단에서 웹 페이지가 동작하는 것을 담당한다.[1] 웹 페이지를 자동차에 비유하자면, HTML은 자동차의 뼈대, CSS는 자동차의 외관, JavaScript는 자동차의 동력이라고 볼 수 있다.",
      createdTime: "2022-11-07T15:24:55.105625",
      modifiedTime: "2022-11-07T15:24:55.105625",
    },
    {
      seq: 1,
      name: "java",
      description:
        "썬 마이크로시스템즈에서 1995년에 개발한 객체 지향 프로그래밍 언어. 창시자는 제임스 고슬링이다. 2010년에 오라클이 썬 마이크로시스템즈를 인수하면서 Java의 저작권을 소유하였다. 현재는 OpenJDK는 GPL2이나 오라클이 배포하는 Oracle JDK는 상업라이선스로 2019년 1월부터 유료화정책을 강화하고 있다. Java EE는 이클립스 재단의 소유이다. Java 언어는 J2SE 1.4부터는 Java Community Process (JCP)에서 개발을 주도하고 있다. C#과 문법적 성향이 굉장히 비슷하며[2], 그에 비해 2019년 Q3에서 가장 많이 이용하는 언어로 뽑혔다.",
      createdTime: "2022-11-07T15:23:37.393141",
      modifiedTime: "2022-11-07T15:23:37.393141",
    },
    {
      seq: 2,
      name: "Python",
      description:
        "1991년에 발표된 인터프리터 방식의 프로그래밍 언어. 영어 문법과 비슷해서 읽고 쓰기 쉬운 특유의 문법과, 이 점에 매료된 프로그래머들로부터 만들어진 수많은 패키지들 덕분에 2010년대 중반부터 전 세계에서 가장 많이 사용되는 프로그래밍 언어 중 하나로 떠올랐다.",
      createdTime: "2022-11-07T15:24:34.593238",
      modifiedTime: "2022-11-07T15:24:34.593238",
    },
    {
      seq: 3,
      name: "JavaScript",
      description:
        "Ecma International의 프로토타입 기반의 프로그래밍 언어로, 스크립트 언어에 해당된다. 특수한 목적이 아닌 이상 모든 웹 브라우저에 인터프리터가 내장되어 있다. 오늘날 HTML, CSS와 함께 웹을 구성하는 요소 중 하나다. HTML이 웹 페이지의 기본 구조를 담당하고, CSS가 디자인을 담당한다면 JavaScript는 클라이언트 단에서 웹 페이지가 동작하는 것을 담당한다.[1] 웹 페이지를 자동차에 비유하자면, HTML은 자동차의 뼈대, CSS는 자동차의 외관, JavaScript는 자동차의 동력이라고 볼 수 있다.",
      createdTime: "2022-11-07T15:24:55.105625",
      modifiedTime: "2022-11-07T15:24:55.105625",
    },
    {
      seq: 1,
      name: "java",
      description:
        "썬 마이크로시스템즈에서 1995년에 개발한 객체 지향 프로그래밍 언어. 창시자는 제임스 고슬링이다. 2010년에 오라클이 썬 마이크로시스템즈를 인수하면서 Java의 저작권을 소유하였다. 현재는 OpenJDK는 GPL2이나 오라클이 배포하는 Oracle JDK는 상업라이선스로 2019년 1월부터 유료화정책을 강화하고 있다. Java EE는 이클립스 재단의 소유이다. Java 언어는 J2SE 1.4부터는 Java Community Process (JCP)에서 개발을 주도하고 있다. C#과 문법적 성향이 굉장히 비슷하며[2], 그에 비해 2019년 Q3에서 가장 많이 이용하는 언어로 뽑혔다.",
      createdTime: "2022-11-07T15:23:37.393141",
      modifiedTime: "2022-11-07T15:23:37.393141",
    },
    {
      seq: 2,
      name: "Python",
      description:
        "1991년에 발표된 인터프리터 방식의 프로그래밍 언어. 영어 문법과 비슷해서 읽고 쓰기 쉬운 특유의 문법과, 이 점에 매료된 프로그래머들로부터 만들어진 수많은 패키지들 덕분에 2010년대 중반부터 전 세계에서 가장 많이 사용되는 프로그래밍 언어 중 하나로 떠올랐다.",
      createdTime: "2022-11-07T15:24:34.593238",
      modifiedTime: "2022-11-07T15:24:34.593238",
    },
    {
      seq: 3,
      name: "JavaScript",
      description:
        "Ecma International의 프로토타입 기반의 프로그래밍 언어로, 스크립트 언어에 해당된다. 특수한 목적이 아닌 이상 모든 웹 브라우저에 인터프리터가 내장되어 있다. 오늘날 HTML, CSS와 함께 웹을 구성하는 요소 중 하나다. HTML이 웹 페이지의 기본 구조를 담당하고, CSS가 디자인을 담당한다면 JavaScript는 클라이언트 단에서 웹 페이지가 동작하는 것을 담당한다.[1] 웹 페이지를 자동차에 비유하자면, HTML은 자동차의 뼈대, CSS는 자동차의 외관, JavaScript는 자동차의 동력이라고 볼 수 있다.",
      createdTime: "2022-11-07T15:24:55.105625",
      modifiedTime: "2022-11-07T15:24:55.105625",
    },
    {
      seq: 1,
      name: "java",
      description:
        "썬 마이크로시스템즈에서 1995년에 개발한 객체 지향 프로그래밍 언어. 창시자는 제임스 고슬링이다. 2010년에 오라클이 썬 마이크로시스템즈를 인수하면서 Java의 저작권을 소유하였다. 현재는 OpenJDK는 GPL2이나 오라클이 배포하는 Oracle JDK는 상업라이선스로 2019년 1월부터 유료화정책을 강화하고 있다. Java EE는 이클립스 재단의 소유이다. Java 언어는 J2SE 1.4부터는 Java Community Process (JCP)에서 개발을 주도하고 있다. C#과 문법적 성향이 굉장히 비슷하며[2], 그에 비해 2019년 Q3에서 가장 많이 이용하는 언어로 뽑혔다.",
      createdTime: "2022-11-07T15:23:37.393141",
      modifiedTime: "2022-11-07T15:23:37.393141",
    },
    {
      seq: 2,
      name: "Python",
      description:
        "1991년에 발표된 인터프리터 방식의 프로그래밍 언어. 영어 문법과 비슷해서 읽고 쓰기 쉬운 특유의 문법과, 이 점에 매료된 프로그래머들로부터 만들어진 수많은 패키지들 덕분에 2010년대 중반부터 전 세계에서 가장 많이 사용되는 프로그래밍 언어 중 하나로 떠올랐다.",
      createdTime: "2022-11-07T15:24:34.593238",
      modifiedTime: "2022-11-07T15:24:34.593238",
    },
    {
      seq: 3,
      name: "JavaScript",
      description:
        "Ecma International의 프로토타입 기반의 프로그래밍 언어로, 스크립트 언어에 해당된다. 특수한 목적이 아닌 이상 모든 웹 브라우저에 인터프리터가 내장되어 있다. 오늘날 HTML, CSS와 함께 웹을 구성하는 요소 중 하나다. HTML이 웹 페이지의 기본 구조를 담당하고, CSS가 디자인을 담당한다면 JavaScript는 클라이언트 단에서 웹 페이지가 동작하는 것을 담당한다.[1] 웹 페이지를 자동차에 비유하자면, HTML은 자동차의 뼈대, CSS는 자동차의 외관, JavaScript는 자동차의 동력이라고 볼 수 있다.",
      createdTime: "2022-11-07T15:24:55.105625",
      modifiedTime: "2022-11-07T15:24:55.105625",
    },
    {
      seq: 1,
      name: "java",
      description:
        "썬 마이크로시스템즈에서 1995년에 개발한 객체 지향 프로그래밍 언어. 창시자는 제임스 고슬링이다. 2010년에 오라클이 썬 마이크로시스템즈를 인수하면서 Java의 저작권을 소유하였다. 현재는 OpenJDK는 GPL2이나 오라클이 배포하는 Oracle JDK는 상업라이선스로 2019년 1월부터 유료화정책을 강화하고 있다. Java EE는 이클립스 재단의 소유이다. Java 언어는 J2SE 1.4부터는 Java Community Process (JCP)에서 개발을 주도하고 있다. C#과 문법적 성향이 굉장히 비슷하며[2], 그에 비해 2019년 Q3에서 가장 많이 이용하는 언어로 뽑혔다.",
      createdTime: "2022-11-07T15:23:37.393141",
      modifiedTime: "2022-11-07T15:23:37.393141",
    },
    {
      seq: 2,
      name: "Python",
      description:
        "1991년에 발표된 인터프리터 방식의 프로그래밍 언어. 영어 문법과 비슷해서 읽고 쓰기 쉬운 특유의 문법과, 이 점에 매료된 프로그래머들로부터 만들어진 수많은 패키지들 덕분에 2010년대 중반부터 전 세계에서 가장 많이 사용되는 프로그래밍 언어 중 하나로 떠올랐다.",
      createdTime: "2022-11-07T15:24:34.593238",
      modifiedTime: "2022-11-07T15:24:34.593238",
    },
    {
      seq: 3,
      name: "JavaScript",
      description:
        "Ecma International의 프로토타입 기반의 프로그래밍 언어로, 스크립트 언어에 해당된다. 특수한 목적이 아닌 이상 모든 웹 브라우저에 인터프리터가 내장되어 있다. 오늘날 HTML, CSS와 함께 웹을 구성하는 요소 중 하나다. HTML이 웹 페이지의 기본 구조를 담당하고, CSS가 디자인을 담당한다면 JavaScript는 클라이언트 단에서 웹 페이지가 동작하는 것을 담당한다.[1] 웹 페이지를 자동차에 비유하자면, HTML은 자동차의 뼈대, CSS는 자동차의 외관, JavaScript는 자동차의 동력이라고 볼 수 있다.",
      createdTime: "2022-11-07T15:24:55.105625",
      modifiedTime: "2022-11-07T15:24:55.105625",
    },
    {
      seq: 1,
      name: "java",
      description:
        "썬 마이크로시스템즈에서 1995년에 개발한 객체 지향 프로그래밍 언어. 창시자는 제임스 고슬링이다. 2010년에 오라클이 썬 마이크로시스템즈를 인수하면서 Java의 저작권을 소유하였다. 현재는 OpenJDK는 GPL2이나 오라클이 배포하는 Oracle JDK는 상업라이선스로 2019년 1월부터 유료화정책을 강화하고 있다. Java EE는 이클립스 재단의 소유이다. Java 언어는 J2SE 1.4부터는 Java Community Process (JCP)에서 개발을 주도하고 있다. C#과 문법적 성향이 굉장히 비슷하며[2], 그에 비해 2019년 Q3에서 가장 많이 이용하는 언어로 뽑혔다.",
      createdTime: "2022-11-07T15:23:37.393141",
      modifiedTime: "2022-11-07T15:23:37.393141",
    },
    {
      seq: 2,
      name: "Python",
      description:
        "1991년에 발표된 인터프리터 방식의 프로그래밍 언어. 영어 문법과 비슷해서 읽고 쓰기 쉬운 특유의 문법과, 이 점에 매료된 프로그래머들로부터 만들어진 수많은 패키지들 덕분에 2010년대 중반부터 전 세계에서 가장 많이 사용되는 프로그래밍 언어 중 하나로 떠올랐다.",
      createdTime: "2022-11-07T15:24:34.593238",
      modifiedTime: "2022-11-07T15:24:34.593238",
    },
    {
      seq: 3,
      name: "JavaScript",
      description:
        "Ecma International의 프로토타입 기반의 프로그래밍 언어로, 스크립트 언어에 해당된다. 특수한 목적이 아닌 이상 모든 웹 브라우저에 인터프리터가 내장되어 있다. 오늘날 HTML, CSS와 함께 웹을 구성하는 요소 중 하나다. HTML이 웹 페이지의 기본 구조를 담당하고, CSS가 디자인을 담당한다면 JavaScript는 클라이언트 단에서 웹 페이지가 동작하는 것을 담당한다.[1] 웹 페이지를 자동차에 비유하자면, HTML은 자동차의 뼈대, CSS는 자동차의 외관, JavaScript는 자동차의 동력이라고 볼 수 있다.",
      createdTime: "2022-11-07T15:24:55.105625",
      modifiedTime: "2022-11-07T15:24:55.105625",
    },
  ]

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
          {/* A tag is a keyword or label that categorizes your question with other,
          similar questions. Using the right tags makes it easier for others to
          find and answer your question */}
          태그는 귀하의 질문을 유사한 다른 질문으로 분류하는 키워드 또는 레이블입니다. 올바른 태그를
          사용하면 다른 사람들이 귀하의 질문을 더 쉽게 찾고 답변할 수 있습니다.
        </p>
        <div className='headline-count'>
          <span>{new Intl.NumberFormat("en-IN").format(tags.length)} 개의 태그들</span>
        </div>
        <div className='tags-box pl16 pr16 pb16'>
          <SearchBox placeholder={"태그 명으로 검색"} handleChange={handleChange} width={"200px"} />
          <ButtonGroup
            buttons={["인기순", "이름순", "최신순"]}
            // buttons={["Popular", "Name", "New"]}
            selected={sortType}
            setSelected={setSortType}
          />
        </div>
        <div className='user-browser'>
          <div className='grid-layout'>
            {tags
              .filter((tag) => tag.name.toLowerCase().includes(fetchSearch.toLowerCase()))
              ?.sort(handleSorting(sortType))
              .slice((page - 1) * itemsPerPage, (page - 1) * itemsPerPage + itemsPerPage)
              .map((tag, index) => (
                <TagPanel key={index} tag={tag} />
              ))}
          </div>
        </div>
        <Pagination
          page={page}
          itemList={tags.filter((tag) =>
            tag.name.toLowerCase().includes(fetchSearch.toLowerCase())
          )}
          itemsPerPage={itemsPerPage}
          handlePaginationChange={handlePaginationChange}
        />
      </div>
    </Fragment>
  )
}

AllTagsPage.propTypes = {
  // getTags: PropTypes.func.isRequired,
  // tag: PropTypes.object.isRequired,
}

// const mapStateToProps = (state) => ({
//   tag: state.tag,
// })

// export default connect(mapStateToProps, { getTags })(AllTagsPage)

export default AllTagsPage
