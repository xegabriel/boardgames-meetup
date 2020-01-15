import React from 'react';
import './App.css';
import SecurityForm from "./components/SecurityForm";
import Events from "./components/Events";
import UserStatus from "./components/UserStatus";
import 'bootstrap/dist/css/bootstrap.min.css';

class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            alert: '',
            isUserLoggedIn: false
        }
    }

    handleUserLoggedStatus = (status) => {
        this.setState({isUserLoggedIn: status})
    };

    render() {
        return (
            <React.Fragment>
                {this.state.isUserLoggedIn ? <UserStatus handleUserLoggedStatus={this.handleUserLoggedStatus}/> : null}

                <div className="container">
                    <h2 className="text-center" id="boardgamesMeetupTitle">Boardgames Meetup</h2>
                    {this.state.isUserLoggedIn ? <Events/> :
                        <SecurityForm handleUserLoggedStatus={this.handleUserLoggedStatus}/>}
                </div>
            </React.Fragment>

        );
    }

}

export default App;
