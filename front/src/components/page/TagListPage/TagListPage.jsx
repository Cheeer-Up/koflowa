import React, { useEffect } from "react";

function TagListPage(props) {
  useEffect(() => (document.title = props.title), []);
  return <div>태그 리스트들이 있는 페이지 입니다</div>;
}

export default TagListPage;
