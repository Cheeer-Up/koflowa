const handleSorting = (sortType, page = "") => {
  let temp = sortType

  if (page === "users" && temp === "Name") {
    temp = "Username"
  } else if (page === "users" && temp === "Popular") {
    temp = "Popular users"
  }

  let todayDate = Date.now()

  function getTime(a) {
    return new Date(a).getTime()
  }

  const milliSecDay = 86300000
  const milliSecWeek = 604800000
  const milliSecMonth = 2628000000
  const milliSecYear = 31540000000

  switch (temp) {
    case "Newest":
      return (a, b) => new Date(b.createdTime) - new Date(a.createdTime)
    case "New":
      return (a, b) => new Date(b.createdTime) - new Date(a.createdTime)
    case "New Users":
      return (a, b) => new Date(b.createdTime) - new Date(a.createdTime)
    case "Top":
      return (a, b) => b.answer_count + b.comment_count - (a.answer_count + a.comment_count)
    case "Active":
      return (a, b) => b.posts_count + b.tags_count - (a.posts_count + a.tags_count)
    case "Views":
      return (a, b) => b.views - a.views
    case "Oldest":
      return (a, b) => new Date(a.createdTime) - new Date(b.createdTime)
    case "Popular":
      return (a, b) => b.posts_count - a.posts_count
    case "Name":
      return (a, b) => a.tagname.localeCompare(b.tagname)
    case "Username":
      return (a, b) => a.username.localeCompare(b.username)
    case "Popular users":
      return (a, b) => b.views - a.views
    case "Today":
      return (a, b) => {
        const aDate = todayDate - getTime(a.createdTime)
        const bDate = todayDate - getTime(b.createdTime)

        if (aDate < milliSecDay && bDate < milliSecDay) {
          return b.answer_count + b.comment_count - (a.answer_count + a.comment_count)
        }
      }
    case "Week":
      return (a, b) => {
        const aDate = todayDate - getTime(a.createdTime)
        const bDate = todayDate - getTime(b.createdTime)
        if (aDate < milliSecWeek && bDate < milliSecWeek) {
          return b.answer_count + b.comment_count - (a.answer_count + a.comment_count)
        }
      }
    case "Month":
      return (a, b) => {
        const aDate = todayDate - getTime(a.createdTime)
        const bDate = todayDate - getTime(b.createdTime)
        if (aDate < milliSecMonth && bDate < milliSecMonth) {
          return b.answer_count + b.comment_count - (a.answer_count + a.comment_count)
        }
      }
    case "Year":
      return (a, b) => {
        const aDate = todayDate - getTime(a.createdTime)
        const bDate = todayDate - getTime(b.createdTime)
        if (aDate < milliSecYear && bDate < milliSecYear) {
          return b.answer_count + b.comment_count - (a.answer_count + a.comment_count)
        }
      }
    default:
      break
  }
}

export default handleSorting
