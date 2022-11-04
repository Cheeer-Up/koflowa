import React from "react"
import ReactDOM from "react-dom"
import { BrowserRouter } from "react-router-dom"

//react-redux 관련 함수
// import { Provider as ReduxProvider } from "react-redux"
// import { store, persistor } from "./redux/store"

//redux-persist 관련 함수
// import { PersistGate } from "redux-persist/integration/react"

import App from "./App"

ReactDOM.render(
  <BrowserRouter>
    {/* <ReduxProvider store={store}> */}
    {/* <PersistGate loading={null} persistor={persistor}> */}
    <App />
    {/* </PersistGate> */}
    {/* </ReduxProvider> */}
  </BrowserRouter>,
  document.getElementById("root")
)
