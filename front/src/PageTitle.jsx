import { useEffect } from "react";

const PageTitle = (title, some = false) => {
  useEffect(() => {
    document.title = title;
  }, [title]);
};

export default PageTitle;
