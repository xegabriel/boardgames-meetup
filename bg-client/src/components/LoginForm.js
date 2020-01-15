import React from "react";
import '../styles/LoginForm.css'
import axios from 'axios';
import Alert from "./Alert";
import RegisterForm from "./RegisterForm";
import Cookies from 'universal-cookie';
import {API_HOST, CREDENTIALS_COOKIE, TOKEN_COOKIE} from '../utils/constants';

class LoginForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            email: '',
            password: '',
            rememberPassword: '',
            alert: ''
        };
    }

    componentDidMount() {
        this.restoreSavedCredentials();
        this.props.handleUserLoggedStatus(this.isUserLoggedIn());
    }

    handleSignIn = (e) => {
        e.preventDefault();
        const user = {
            'username': this.state.email,
            'password': this.state.password
        };

        const authenticateUrl = API_HOST + '/authenticate';
        axios.post(authenticateUrl, user)
            .then(res => {
                this.rememberPassword();
                this.storeAuthToken(res.data[TOKEN_COOKIE]);
                this.props.handleUserLoggedStatus(this.isUserLoggedIn());
            }).catch((error) => {
            if (error.response) {
                this.handleError(error.response.data.message);
            }
        });
    };

    isUserLoggedIn = () => {
        let cookies = new Cookies();
        let isUserLoggedIn = false;
        if (cookies.get(TOKEN_COOKIE)) {
            isUserLoggedIn = true;
        }
        return isUserLoggedIn;
    };

    rememberPassword = () => {
        if (this.state.rememberPassword) {
            const cookies = new Cookies();
            let credentials = {};
            credentials['email'] = this.state.email;
            credentials['password'] = this.state.password;
            cookies.set(CREDENTIALS_COOKIE, credentials, {path: '/'});
        } else {
            const cookies = new Cookies();
            cookies.remove(CREDENTIALS_COOKIE, {path: '/'});
        }
    };

    restoreSavedCredentials = () => {
        const cookies = new Cookies();
        const credentialsCookie = cookies.get(CREDENTIALS_COOKIE);
        if (credentialsCookie) {
            this.setState({email: credentialsCookie['email'], password: credentialsCookie['password']});
        }
    };

    storeAuthToken = (token) => {
        const cookies = new Cookies();
        cookies.set('token', token, {path: '/'});
    };

    controlEmailInput = (e) => {
        this.setState({email: e.target.value});
    };

    controlPasswordInput = (e) => {
        this.setState({password: e.target.value});
    };

    controlRememberPasswordInput = (e) => {
        this.setState({rememberPassword: e.target.checked});
    };

    handleError = (message) => {
        this.setState({alert: <Alert message={message} type={'danger'}/>})
    };

    handleRegister = () => {
        this.props.handleDisplayedForm(<RegisterForm handleDisplayedForm={this.props.handleDisplayedForm}
                                                     handleUserLoggedStatus={this.props.handleUserLoggedStatus}/>);
    };

    render() {
        return (
            <div className="row">
                <div className="col-sm-9 col-md-7 col-lg-5 mx-auto">
                    <div className="card card-signin my-5">
                        <div className="card-body">
                            <h5 className="card-title text-center">Sign In</h5>
                            <form className="form-signin" onSubmit={this.handleSignIn}>
                                <div className="form-label-group">
                                    <input type="email" id="inputEmail" className="form-control"
                                           placeholder="Email address" required autoFocus
                                           onChange={this.controlEmailInput} value={this.state.email}/>
                                    <label htmlFor="inputEmail">Email address</label>
                                </div>

                                <div className="form-label-group">
                                    <input type="password" id="inputPassword" className="form-control"
                                           placeholder="Password" required onChange={this.controlPasswordInput}
                                           value={this.state.password} autoComplete="on"/>
                                    <label htmlFor="inputPassword">Password</label>
                                </div>

                                <div className="custom-control custom-checkbox mb-3">
                                    <input type="checkbox" className="custom-control-input" id="customCheck1"
                                           onChange={this.controlRememberPasswordInput}
                                           checked={this.state.rememberPassword}/>
                                    <label className="custom-control-label" htmlFor="customCheck1">Remember
                                        password</label>
                                </div>
                                <button className="btn btn-lg btn-primary btn-block text-uppercase"
                                        type="submit">Sign in
                                </button>
                            </form>
                            <div className="my-4">
                                <button className="btn btn-lg btn-secondary btn-block text-uppercase"
                                        onClick={this.handleRegister}
                                        type="submit"><i className="fab fa-google mr-2"/> Go to registration
                                </button>
                            </div>
                            {this.state.alert}
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default LoginForm;