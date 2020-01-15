import React from "react";
import '../styles/Events.css';
import axios from "axios";
import {API_HOST, TOKEN_COOKIE} from "../utils/constants";
import Cookies from "universal-cookie";
import EventCard from "./EventCard";

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
            <EventCard key={event.id.counter} event={event} />
        );
        return (<div className="events-body">{eventsList}</div>);
    }
}

export default Events;