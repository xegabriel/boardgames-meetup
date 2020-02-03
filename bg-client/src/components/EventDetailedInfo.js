import React from "react";
import '../styles/EventDetaildInfo.css';
import AccessTimeIcon from '@material-ui/icons/AccessTime';
import HomeIcon from '@material-ui/icons/Home';
import Cookies from "universal-cookie";
import {API_HOST, TOKEN_COOKIE} from "../utils/constants";
import axios from "axios";
import Alert from "./Alert";

class EventDetailedInfo extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            event: props.eventInfo,
            availableGameOption: undefined,
            alert: null
        };
    }

    handleStopEventRegistration = (e) => {
        e.preventDefault();
        let cookies = new Cookies();
        let config = {
            headers: {
                'Authorization': "Bearer " + cookies.get(TOKEN_COOKIE)
            }
        };
        axios.put(API_HOST + '/api/dashboard/stopEventRegistration/' + this.state.event.title + '/' + this.state.availableGameOption, null, config)
            .then(res => {
                if (res.status === 200) {
                    this.setState({event: res.request.response});
                    this.handleSuccess('The event was stopped!');
                }
            }).catch((error) => {
            if (error.response) {
                this.handleError(error.response.data.message);
            }
        });
    };

    handleAvailableGame =(event) => {
        console.log(event.target.value);
        this.setState({availableGameOption: event.target.value});
    };

    handleGoBack = () => {
        this.props.handleReadMore(null);
    };

    handleError = (message) => {
        this.setState({alert: <Alert message={message} type={'danger'}/>})
    };

    handleSuccess = (message) => {
        this.setState({alert: <Alert message={message} type={'success'}/>})
    };

    render() {
        const availableGamesList = this.state.event.availableGames.map((availableGame) =>
            <div className="form-group" key={availableGame.gameName}>
            <div className="form-check" >
                <input className="form-check-input" type="radio" name="availableGames" id={availableGame.gameName}
                       value={availableGame.gameName}/>
                    <label className="form-check-label" htmlFor="exampleRadios2">
                        {availableGame.gameName} - {availableGame.votesFromUsers === null ? 0 : availableGame.votesFromUsers.length} Votes
                    </label>
            </div>
            </div>
        );

        const availableGameOptions = this.state.event.availableGames.map((availableGame) =>
            <option key={availableGame.gameName} value={availableGame.gameName}>{availableGame.gameName}</option>
        );

        return (
            <React.Fragment>
            <div className='event-detailed-info-body'>
                <div className="blog-post">
                    <h2 className="blog-post-title">{this.state.event.title}</h2>
                    <h6 className="card-subtitle mb-2 text-muted">{this.state.event.location} </h6>
                    {this.state.event.eventStillAvailableForRegistration?<span className="eventAvailable">Available</span>:<div><span className="eventClosed">Closed</span> - Game: {this.state.event.finalGame}</div>}
                    <p className="blog-post-meta"><AccessTimeIcon/> {new Date(this.state.event.startDate).toLocaleString() + ' - ' + new Date(this.state.event.endDate).toLocaleString()}</p>
                    <p className="blog-post-meta"><HomeIcon/> {this.state.event.fullAddress}</p>
                    <p>{this.state.event.description}</p>
                    {availableGamesList}
                    <button type="button" className="btn btn-secondary btn-lg btn-block" onClick={this.handleGoBack}>Go back </button>
                </div>
            </div>
                {//TODO: Display only for organizer
                     }
            <div className='stop-event-registration-body'>
                <h5>Event dashboard</h5>
                <h6>Stop event registration</h6>
            <form onSubmit={this.handleStopEventRegistration}>
                <div className="form-group">
                <select defaultValue='' className="form-control form-control-sm" value={this.state.availableGameOption} onChange={this.handleAvailableGame} required>
                    <option value="" disabled hidden>Choose here</option>
                    {availableGameOptions}
                </select>
                </div>
                <button type="submit" className="btn btn-danger btn-lg btn-block" id="stopRegistrationButton">Stop event registration </button>
                {this.state.alert}
            </form>

                <h6>Game suggestions</h6>
                {this.state.event.proposedGames == null? <p>There are no suggestions at the moment.</p>:<p>{this.state.event.proposedGames}</p>}
                <h6>Edit event</h6>{
                //TODO: To implement edit
            }
                <button type="button" className="btn btn-primary btn-lg btn-block" id="editEventButton">Edit</button>
            </div>

            </React.Fragment>
        );
    }
}

export default EventDetailedInfo;