import React from 'react';

export default class NotFound extends React.Component {

    render() {
        return (
        <span>404. Not Found {this.props.history.location.pathname}</span>
        );    
    }
}

