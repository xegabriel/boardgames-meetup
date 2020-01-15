import React from "react";
import '../styles/Events.css';
import axios from "axios";
import {API_HOST, TOKEN_COOKIE} from "../utils/constants";
import Cookies from "universal-cookie";
import HomeIcon from '@material-ui/icons/Home';
import AccessTimeIcon from '@material-ui/icons/AccessTime';

class Events extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            events: []
        }
    }

    componentDidMount() {
        let cookies = new Cookies();
        let config = {
            headers: {
                'Authorization': "Bearer " + cookies.get(TOKEN_COOKIE)
            }

        };
        axios.get(API_HOST + '/api/core/getAllEvents', config)
            .then(res => {
                this.setState({events: res.data})
            });
    }

    render() {
        const eventsList = this.state.events.map((event) =>

            <div className="card event-card" key={event.id.counter}>
                <div className="card-body">
                    <h5 className="card-title">{event.title}</h5>
                    <h6 className="card-subtitle mb-2 text-muted">{event.location}</h6>
                    <p className="card-text">{event.description}</p>
                    <p><HomeIcon/> {event.fullAddress}</p>
                    <p>
                        <AccessTimeIcon/> {new Date(event.startDate).toLocaleString() + ' - ' + new Date(event.endDate).toLocaleString()}
                    </p>
                    <p className="card-link access-event">Read More</p>
                </div>
            </div>
        );
        return (<div className="events-body">{eventsList}</div>);
    }
}

export default Events;