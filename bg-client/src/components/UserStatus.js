import React from "react";
import jwt_decode from 'jwt-decode';
import {TOKEN_COOKIE} from '../utils/constants';
import Cookies from 'universal-cookie';
import '../styles/UserStatus.css'

class UserStatus extends React.Component {
    signOut = () => {
        let cookies = new Cookies();
        cookies.remove(TOKEN_COOKIE);
        this.props.handleUserLoggedStatus(false);
    };

    render() {
        let cookies = new Cookies();
        let decodedToken = jwt_decode(cookies.get(TOKEN_COOKIE));

        return (
            <div className="user-status">
                <p>Logged in as {decodedToken.firstName}</p>
                <p className="logout" onClick={this.signOut}>Log out</p>
            </div>
        );
    }
}

export default UserStatus;
