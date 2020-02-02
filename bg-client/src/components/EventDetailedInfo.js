import React from "react";
import '../styles/EventDetaildInfo.css';
class EventDetailedInfo extends React.Component {
    handleGoBack = () => {
        this.props.handleReadMore(null);
    };

    render() {
        console.log(this.props.eventInfo.title);
        return (
            <div className='event-detailed-info-body'>
                <p>{this.props.eventInfo.title}</p>
                <button type="button" className="btn btn-secondary btn-lg btn-block" onClick={this.handleGoBack}>Go back </button>
            </div>
        );
    }
}

export default EventDetailedInfo;