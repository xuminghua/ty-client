import React, { Component } from 'react';
import '../style/Common.css'
import {Button,ButtonToolbar, ButtonGroup } from "react-bootstrap";
import home from "../images/home_click@2x.png";
class BottomBar extends Component {
    constructor(props){
        super(props);
        this.state= {
            carouselList:[]
        }
    }
    render() {
        return (
            <div className="fixToolBtn box">
                <div className="item">
                    <div className="homeIcon">
                    </div>
                    <p>首页</p></div>
                <div className="item">  <div className="addIcon">
                </div><p>发布</p></div>
                <div className="item">  <div className="findIcon">
                </div><p>发现</p></div>
                <div className="item">  <div className="myIcon">
                </div><p>我的</p></div>
            </div>
        )
    }
}
export default BottomBar;
