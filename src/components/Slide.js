import React, {Component,PropTypes } from 'react';
import {Carousel} from 'react-bootstrap';
import Ajax from '../common/ajax';
import '../style/Common.css'
class Slide extends Component{
    constructor(props){
        super(props);
        this.state= {
            carouselList:[]
        }
    }
    static propTypes = {
        carouselList: PropTypes.array
    };
    componentDidMount(){
        Ajax({
            url: 'carousel.json',
            type: 'get',
            callback: (res) => {
                if (res) {
                    this.setState({
                       carouselList:res.data.data1
                    });
                } else {
                    console.log("error");
                }
            }
        })
    }
    render(){
        console.log(this.state.carouselList);
        const shows = this.state.carouselList.map((carousel, index) => {
            return <Carousel.Item>
                <img src={carousel.imgUrl}/>
                <Carousel.Caption>
                    <p className="title">{carousel.title}</p>
                    <p className="memo">{carousel.memo}</p>
                </Carousel.Caption>
            </Carousel.Item>;
        });
        return(
            <Carousel>
                {shows}
            </Carousel>
        )
    }
}
export default  Slide;