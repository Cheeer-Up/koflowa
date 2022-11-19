import React, { Fragment } from "react"
import { Pagination as MuiPagination, PaginationItem } from "@mui/material"

const Pagination = ({ page, count, handlePaginationChange }) => {
  return (
    <Fragment>
      <MuiPagination
        variant='outlined'
        shape='rounded'
        page={page}
        count={count}
        onChange={handlePaginationChange}
        style={{ float: "right", margin: "0 13px 16px 0" }}
        renderItem={(item) => <PaginationItem {...item} sx={{ color: "#5F8BFF", border: "1px solid #0A95FF" }} />}
      />
    </Fragment>
  )
}

export default Pagination
