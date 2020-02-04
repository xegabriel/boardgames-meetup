import React from 'react';
import './App.css';
import SecurityForm from "./components/SecurityForm";
import Events from "./components/Events";
import UserStatus from "./components/UserStatus";
import AddEvent from "./components/AddEvent";
import 'bootstrap/dist/css/bootstrap.min.css';

import AddIcon from '@material-ui/icons/Add';
import EventDetailedInfo from "./components/EventDetailedInfo";

class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            alert: '',
            isUserLoggedIn: false,
            displayAddEvent: false,
            displayEventInfo: null
        }
    }

    handleUserLoggedStatus = (status) => {
        this.setState({isUserLoggedIn: status})
    };

    handleDisplayAddEvent = () => {
        this.setState({displayAddEvent: !this.state.displayAddEvent})
    };

    handleReadMore = (event) => {
        this.setState({displayEventInfo: event});
    };

    render() {
        let componentToDisplay;
        if (!this.state.isUserLoggedIn) {
            componentToDisplay = <SecurityForm handleUserLoggedStatus={this.handleUserLoggedStatus}/>;
        } else {
            if (this.state.displayAddEvent) {
                componentToDisplay = <AddEvent handleDisplayAddEvent={this.handleDisplayAddEvent}/>;
            } else if(this.state.displayEventInfo){
                componentToDisplay = <EventDetailedInfo eventInfo={this.state.displayEventInfo} handleReadMore={this.handleReadMore}/>
            } else {
                componentToDisplay = <Events handleReadMore={this.handleReadMore}/>;
            }
        }
        let displayAddEventButton;
        if (!this.state.displayAddEvent && this.state.isUserLoggedIn) { //TODO: Display only for organizer
            displayAddEventButton = <span className="float-add-event" onClick={this.handleDisplayAddEvent}><AddIcon
                className="plus-icon-float"/></span>;
        }
        return (
            <React.Fragment>
                {this.state.isUserLoggedIn ? <UserStatus handleUserLoggedStatus={this.handleUserLoggedStatus}/> : null}

                <div className="container">
                    <h2 className="text-center" id="boardgamesMeetupTitle">Boardgames Meetup</h2>
                    {componentToDisplay}
                    {displayAddEventButton}
                </div>
            </React.Fragment>

        );
    }

}

export default App;
