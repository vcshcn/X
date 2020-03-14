import React from 'react';
import { Link } from "react-router-dom";
import Text from '../components/Text';

export default class Header extends React.Component {

    render() {
        return (
            <header className="banner">
                <div className="left">
                    <h1 className="title">
                        Admin Console
                    </h1>
                </div>
                <div className="center">
                    <canvas id="picture_canvas" height="38px" width="38px">
                    </canvas>
                </div>
                <div className="right">
                    <div style={{width: "250px",float:"right"}}>
                        <span style={{lineHeight:"25px",margin:0,padding:0}}><Text name="Welcome"/> <Text name="Admin"/></span>
                        <br/><span style={{lineHeight:"25px",margin:0,padding:0}}>(<Link to="/ViewProfile/Profile"><Text name="Account"/></Link> | <a href="Help.do"><Text name="Help"/></a> | <Link to="/Logout"><Text name="Logout"/></Link>)</span>
                    </div>
                    <img style={{float:"right",borderRadius:"50%"}} border="0" width="36" height="36" src={require('../images/head.png')} alt="myself" />
                </div>
                <div style={{clear:"both"}}></div>
            </header>
        );    
    }
}

