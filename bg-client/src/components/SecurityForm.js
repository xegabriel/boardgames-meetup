import React from 'react';
import LoginForm from "./LoginForm";

class SecurityForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            displayedForm: <LoginForm handleDisplayedForm={this.handleDisplayedForm}
                                      handleUserLoggedStatus={this.props.handleUserLoggedStatus}/>,

        }
    }

    handleDisplayedForm = (form) => {
        this.setState({displayedForm: form})
    };

    render() {
        return (
            <React.Fragment>
                {this.state.displayedForm}
            </React.Fragment>
        );
    }
}

export default SecurityForm;