import React from 'react';
import Text from './Text';
import '../css/Tabs.less';

export class Tabs extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            active : this.props.active
        }
    }

    toggle(key) {
        this.setState({active: key});
    }

    render() {
        return (
            <div className="tabs">
                <ul>
                {
                    this.props.children.map((tab, idx)=>{
                        return <li key={idx} className={tab.key==this.state.active ? "active":null}><a onClick={()=>{this.toggle(tab.key)}}><Text name={tab.props.title} /></a></li>
                    })
                }
                </ul>
                { 
                    this.props.children.map( (tab, idx)=>{
                        return <div key={idx} style={{display: tab.key == this.state.active ? "" :"none"}} className="tab-context-border">{tab}</div>
                    }) 
                }
            </div>
        );
    }
}

export class Tab extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return this.props.children;
    }
}
