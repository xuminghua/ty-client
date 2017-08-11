import React, {Component} from 'react';
import Ajax from '../common/ajax';
class TravelsList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            travelList:[]
        }
    }

    componentDidMount() {
        Ajax({
            url: 'travels.json',
            type: 'get',
            callback: (res) => {
                if (res) {
                    this.setState({
                        travelList: res.data.travels
                    });
                } else {
                    console.log("error");
                }
            }
        })
    }

    render() {
        console.log(this.state.travelList);
        const shows = this.state.travelList.map((travel, index) => {
            return <div className="travel-container">
                <h4>{travel.title}</h4>
                <div>
                <div className="travel-icon"><img width={"100%"}  src={travel.img}/></div>
                <div className="travel-content">
                    <p>{travel.content}</p>
                </div>
                </div>
                <div className="travel-detail">{travel.author} {travel.date} {travel.viewCount} {travel.likeCount}</div>
            </div>;
        });
        return (
            <div>{shows}</div>
        )
    }
}
export default TravelsList;