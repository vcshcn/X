import React from 'react';
import { HashRouter  as Router , Switch, Route, hashHistory } from "react-router-dom";
import App from './App';

export default class Index extends React.Component {

    render() {
        return (
            <Router hashType='noslash' history={hashHistory}>
                <Switch>
                        <Route path="/" component={App} />
                </Switch>
            </Router>
       )
    }
}
