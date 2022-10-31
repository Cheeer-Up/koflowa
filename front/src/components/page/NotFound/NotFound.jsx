import React, { Fragment, useEffect } from "react";
import { Link } from "react-router-dom";

import "./NotFound.css";

export function NotFound(props) {
  // useEffect(() => (document.title = props.title), []);
  useEffect(() => (document.title = props.title), []);
  return (
    <div>
      <Fragment>
        <div className='page'>
          <div className='box'>
            <div className='box__description'>
              <h2>404 Page Not Found</h2>
              <div className='box__description-container'>
                <div className='box__description-text fc-black-500'>
                  당신이 원하는 페이지를 찾을수 없습니다.
                </div>
              </div>
              <Link to='/' className='box__button'>
                홈페이지로 돌아가기
              </Link>
            </div>
          </div>
        </div>
      </Fragment>
    </div>
  );
}

export default NotFound;

// class NotFound extends Component {
//   render() {
//     return (
// <Fragment>
//   <div className='page'>
//     <div className='box'>
//       <div className='box__description'>
//         <h2>404 Page Not Found</h2>
//         <div className='box__description-container'>
//           <div className='box__description-text fc-black-500'>
//             당신이 원하는 페이지를 찾을수 없습니다.
//           </div>
//         </div>
//         <Link to='/' className='box__button'>
//           홈페이지로 돌아가기
//         </Link>
//       </div>
//     </div>
//   </div>
// </Fragment>
//     );
//   }
// }

// export default NotFound;
