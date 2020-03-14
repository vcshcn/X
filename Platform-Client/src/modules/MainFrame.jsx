import React from 'react';
import Header from './Header';
import Body from './Body';
import Footer from './Footer';
import '../css/frame.less';

export default class MainFrame extends React.Component {

    render() {
        return (
            <div className="MainFrame">
                <Header {...this.props} />
                <Body {...this.props} />
                <Footer {...this.props} />
                <p><img src="/xlog/i.servlet" border="0" width="0" height="0" /></p>
            </div>
        );
    }
}
