import React, { Fragment, useState, useEffect } from "react"
import { connect } from "react-redux"
import PropTypes from "prop-types"
import { getAnswerList } from "api/answer"
import handleSorting from "../../../utils/handleSorting"

import AnswerItem from "./AnswerItem/AnswerItem.component"
import Spinner from "../../../components/Components/Spinner/Spinner.component"
import AnswerForm from "./AnswerForm/AnswerForm.component"
import ButtonGroup from "../../../components/Components/ButtonGroup/ButtonGroup.component"

import "./AnswerSection.styles.scss"
  
const AnswerSection = ({ questionSeq, page, size} ) => {
  const [auth, setAuth] = useState("");
  const [answer, setAnswer] = useState([]);
  useEffect(() => {
    getAnswerList(questionSeq, page, size).then((res)=>{
      setAnswer(res.data.result.data.content);
      console.log("res.data.result.data.content: ",res.data.result.data.content);
    })
    // eslint-disable-next-line
  }, [getAnswerList])

  const [sortType, setSortType] = useState("Newest")

  return (
    <Fragment>
      <div className='answer'>
        <div className='answer-header fc-black-800'>
          <div className='answer-sub-header'>
            <div className='answer-headline'>
              <h2>Answers</h2>
            </div>
            <ButtonGroup
              buttons={["Newest", "Oldest"]}
              selected={sortType}
              setSelected={setSortType}
            />
          </div>
        </div>
        {/* {answer.loading === null ? (
          <Spinner width='25px' height='25px' />
        ) : (
          answer.answers?.sort(handleSorting(sortType)).map((answer, index) => (
            <div key={index} className='answers'>
              <AnswerItem answer={answer} />
              <p>test</p>
            </div>
          ))
        )} */}

        {
        answer.map((data, idx) => (
            <div key={idx} className='answers'>
              <AnswerItem answer={data} />
            </div>
          ))
        }

        {/* {answer.answers?.sort().map((answer, index) => (
          <div key={index} className='answers'>
            <AnswerItem answer={answer} />
            <p>test</p>
          </div>
        ))} */}

        <div className='add-answer'>
          <AnswerForm auth={auth}/>
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
