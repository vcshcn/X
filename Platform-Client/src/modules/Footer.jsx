import React from 'react';

export default class Footer extends React.Component {

    render() {
        return (
            <section className="footer">
                <strong>&copy;XWAY Project Copyright 2012</strong> <img src={require('../images/favicon.ico')} border="0"/>
            </section>
        );    
    }
}

