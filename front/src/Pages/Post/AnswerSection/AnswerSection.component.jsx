import React, { Fragment, useState, useEffect } from "react"
import { connect, useSelector } from "react-redux"
import PropTypes from "prop-types"
import { getAnswerList } from "api/answer"
import handleSorting from "../../../utils/handleSorting"
import Pagination from "components/Layouts/Pagination/Pagination.component"

import AnswerItem from "./AnswerItem/AnswerItem.component"
import Spinner from "../../../components/Components/Spinner/Spinner.component"
import AnswerForm from "./AnswerForm/AnswerForm.component"
import ButtonGroup from "../../../components/Components/ButtonGroup/ButtonGroup.component"

import "./AnswerSection.styles.scss"
import { selectEdit } from "redux/slice/AnswerSlice"

const AnswerSection = ({ question }) => {
  const [auth, setAuth] = useState("")
  const [answer, setAnswer] = useState([])
  const questionSequence = question.questionSeq
  const [loading, setLoading] = useState(true)

  let isEdit = useSelector(selectEdit)

  useEffect(() => {
    setTimeout(
      () =>
        getAnswerList(questionSequence).then((res) => {
          const payload = res.data.result.data
          setAnswer(payload)
          setLoading(false)
        }),
      250
    )
  }, [isEdit])
  // const handlePaginationChange = (e, value) => {
  //   setPage(value)
  // }
  const [sortType, setSortType] = useState("Newest")
  const handleChange = (e) => {
    e.preventDefault()
  }
  return (
    <Fragment>
      <div className='answer'>
        <div className='answer-header fc-black-800'>
          <div className='answer-sub-header'>
            <div className='answer-headline'>
              <h1>{answer.length} 답변</h1>
            </div>
          </div>
        </div>

        {loading === null ? (
          <Spinner width='25px' height='25px' />
        ) : (
          answer.map((data, idx) => (
            <div key={idx} className='answers'>
              <AnswerItem answer={data} question={question} />
            </div>
          ))
        )}

        <div className='add-answer'>
          <AnswerForm auth={auth} questionSeq={questionSequence} />
        </div>
      </div>
    </Fragment>
  )
}

AnswerSection.propTypes = {
  // getAnswers: PropTypes.func.isRequired,
  // answer: PropTypes.object.isRequired,
  // post: PropTypes.object.isRequired,
}

const mapStateToProps = (state) => ({
  answer: state.answer,
  post: state.post,
})

export default connect(mapStateToProps, { getAnswerList })(AnswerSection)
