import React from "react";
import '../styles/RegisterForm.css'
import axios from 'axios';
import Alert from "./Alert";
import LoginForm from "./LoginForm";
import {API_HOST} from '../utils/constants';

class RegisterForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            email: '',
            password: '',
            firstName: '',
            lastName: '',
            alert: ''
        }
    }

    handleRegister = (e) => {
        e.preventDefault();
        const user = {
            'email': this.state.email,
            'password': this.state.password,
            'firstName': this.state.firstName,
            'lastName': this.state.lastName
        };
        axios.post(API_HOST + '/register', user)
            .then(res => {
                if (res.status === 200) {
                    this.handleSuccess('You have successfully registered!');
                }
            }).catch((error) => {
            if (error.response) {
                this.handleError(error.response.data.message);
            }
        });
    };

    controlEmailInput = (e) => {
        this.setState({email: e.target.value});
    };

    controlPasswordInput = (e) => {
        this.setState({password: e.target.value});
    };

    controlFirstNameInput = (e) => {
        this.setState({firstName: e.target.value});
    };

    controlLastNameInput = (e) => {
        this.setState({lastName: e.target.value});
    };

    handleBack = () => {
        this.props.handleDisplayedForm(<LoginForm handleDisplayedForm={this.props.handleDisplayedForm}
                                                  handleUserLoggedStatus={this.props.handleUserLoggedStatus}/>);
    };

    handleError = (message) => {
        this.setState({alert: <Alert message={message} type={'danger'}/>})
    };

    handleSuccess = (message) => {
        this.setState({alert: <Alert message={message} type={'success'}/>})
    };

    render() {
        return (
            <div className="row" id="registerRow" >
                <div className="col-sm-9 col-md-7 col-lg-5 mx-auto">
                    <div className="card card-register my-5">
                        <div className="card-body">
                            <h5 className="card-title text-center">Register</h5>
                            <form className="form-signin" onSubmit={this.handleSignIn}>
                                <div className="form-label-group">
                                    <input type="email" id="inputEmail" className="form-control"
                                           placeholder="Email address" required autoFocus
                                           onChange={this.controlEmailInput} value={this.state.email}/>
                                    <label htmlFor="inputEmail">Email address</label>
                                </div>

                                <div className="form-label-group">
                                    <input type="text" id="inputFirstName" className="form-control"
                                           placeholder="First Name" required autoFocus
                                           onChange={this.controlFirstNameInput} value={this.state.firstName}/>
                                    <label htmlFor="inputFirstName">First Name</label>
                                </div>

                                <div className="form-label-group">
                                    <input type="text" id="inputLastName" className="form-control"
                                           placeholder="Last Name" required autoFocus
                                           onChange={this.controlLastNameInput} value={this.state.lastName}/>
                                    <label htmlFor="inputLastName">Last Name</label>
                                </div>

                                <div className="form-label-group">
                                    <input type="password" id="inputPassword" className="form-control"
                                           placeholder="Password" required onChange={this.controlPasswordInput}
                                           value={this.state.password} autoComplete="off"/>
                                    <label htmlFor="inputPassword">Password</label>
                                </div>

                                <button className="lightButton btn btn-lg btn-success btn-block text-uppercase"
                                        onClick={this.handleRegister}
                                        type="submit"><i className="fab fa-google mr-2"/> Register
                                </button>
                            </form>
                            <div className="my-4">
                                <button className="darkButton btn btn-lg btn-secondary btn-block text-uppercase"
                                        type="submit" onClick={this.handleBack}>Back
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

export default RegisterForm;