import React from "react";
import "../styles/AddEvents.css";
import DateTimePicker from 'react-datetime-picker'
import axios from "axios";
import {API_HOST, TOKEN_COOKIE} from "../utils/constants";
import Alert from "./Alert";
import Cookies from "universal-cookie";

class AddEvent extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            title: '',
            startDate: new Date(),
            endDate: new Date(),
            location: '',
            fullAddress: '',
            description: '',
            maximumPlayers: 0,
            availableGames: '',
            alert: ''
        };
    }

    handleTitle = (e) => {
        this.setState({title: e.target.value});
    };

    handleStartDate = (startDate) => {
        this.setState({startDate: startDate});
    };

    handleEndDate = (endDate) => {
        this.setState({endDate: endDate});
    };

    handleEventLocation = (e) => {
        this.setState({location: e.target.value});
    };

    handleFullAddress = (e) => {
        this.setState({fullAddress: e.target.value});
    };

    handleDescription = (e) => {
        this.setState({description: e.target.value});
    };

    handleMaximumPlayers = (e) => {
        this.setState({maximumPlayers: e.target.value});
    };

    handleAvailableGames = (e) => {
        this.setState({availableGames: e.target.value});
    };

    handleSaveEvent = (e) => {
        e.preventDefault();
        let availableGamesArray = this.state.availableGames.split(',').map(game => game.trim());
        let availableGamesAndVotersArray = [];
        let index;
        for (index in availableGamesArray) {
            availableGamesAndVotersArray.push({"gameName": availableGamesArray[index], "votesFromUsers": []});
        }
        const event = {
            'title': this.state.title,
            'startDate': this.state.startDate,
            'endDate': this.state.endDate,
            'location': this.state.location,
            'fullAddress': this.state.fullAddress,
            'description': this.state.description,
            'maximumPlayers': this.state.maximumPlayers,
            'availableGames': availableGamesAndVotersArray
        };
        
        let cookies = new Cookies();
        let config = {
            headers: {
                'Authorization': "Bearer " + cookies.get(TOKEN_COOKIE)
            }
        };
        axios.post(API_HOST + '/api/dashboard/saveEvent', event, config)
            .then(res => {
                if (res.status === 200) {
                    this.handleSuccess('The event was saved!');
                }
            }).catch((error) => {
            if (error.response) {
                this.handleError(error.response.data.message);
            }
        });
    };

    handleError = (message) => {
        this.setState({alert: <Alert message={message} type={'danger'}/>})
    };

    handleSuccess = (message) => {
        this.setState({alert: <Alert message={message} type={'success'}/>})
    };

    handleGoBack = () => {
        this.props.handleDisplayAddEvent();
    };

    render() {
        return (
            <div className="add-event-body">
                <p className="h2">Add event</p>
                <form onSubmit={this.handleSaveEvent}>
                    <div className="form-group">
                        <label htmlFor="title">Title</label>
                        <input minLength="3" type="text" className="form-control" id="title" placeholder="Event Title"
                               onChange={this.handleTitle} value={this.state.title}/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="startDate">Start Date</label>
                        <DateTimePicker className="form-control-date " id="startDate" onChange={this.handleStartDate}
                                        value={this.state.startDate} required/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="endDate">End Date</label>
                        <DateTimePicker className="form-control-date " id="endDate" onChange={this.handleEndDate}
                                        value={this.state.endDate} required/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="location">Location</label>
                        <input minLength="3" type="text" className="form-control" id="location"
                               placeholder="Event Location" onChange={this.handleEventLocation}
                               value={this.state.location}/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="fullAddress">Full Address</label>
                        <input minLength="3" type="text" className="form-control" id="fullAddress"
                               placeholder="Full Address" onChange={this.handleFullAddress}
                               value={this.state.fullAddress}/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="description">Event Description</label>
                        <textarea minLength="10" className="form-control" id="eventDescription"
                                  placeholder="Event Description" rows="3" onChange={this.handleDescription}
                                  value={this.state.description}/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="maximumPlayers">Maximum Players</label>
                        <input type="number" min="1" max="50" className="form-control" id="maximumPlayers"
                               placeholder="Maximum Players" onChange={this.handleMaximumPlayers}
                               value={this.state.maximumPlayers}/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="availableGames">Available boardgames, separated by ,</label>
                        <input type="text" className="form-control" id="availableGames"
                               placeholder="Available Boardgames" onChange={this.handleAvailableGames}
                               value={this.state.availableGames}/>
                    </div>
                    <button type="submit" className="btn btn-primary btn-lg btn-block" value="Save">Save</button>
                    <button type="button" className="btn btn-secondary btn-lg btn-block" id="addEventGoBackButton"
                            onClick={this.handleGoBack}>Go Back
                    </button>
                    {this.state.alert}
                </form>
            </div>
        );
    }
}

export default AddEvent;