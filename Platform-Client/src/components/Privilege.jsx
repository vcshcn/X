import React from 'react';
import PropTypes from 'prop-types';
import EnvContext, { Defaults } from './EnvContext';

export default class Privilege extends React.Component {

    render() {
        let user = this.context.getUser();
        let found = user.role.privileges.find(p=>p.name == this.props.name) != null;

        return found ? this.props.children : null;
    }
}

Privilege.propTypes = {
    name : PropTypes.string.isRequired
};

Privilege.contextType = EnvContext;
