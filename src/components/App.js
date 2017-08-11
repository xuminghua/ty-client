import React, { Component } from 'react';
import '../style/App.css';
import '../style/Common.css';
import BottomBar from "./BottomBar";
import Slide from "./Slide";
import TravelsList from "./TravelsList";
import Head from "./Head.js"

class App extends Component {
   render() {
    return (
      <div className="App">
          <Head/>
          <Slide/>
          <div className="seperate"><p> 精彩游记</p></div>
          <TravelsList/>
          <BottomBar/>
      </div>
    );
  }
}
export default App;
