import React from 'react';
import PropTypes from 'prop-types';
import { Text} from '../../components/Controls';

const ViewContext = React.createContext();

export class Field extends  React.Component {
    constructor(props) {
        super(props);
        this.cols = props.cols;
    }

    render() {
        let styles = {};
        if (this.cols)
            styles.gridColumnStart = "span " + this.cols;

        this.view = this.context.view;
        this.label = this.props.label ? this.props.label : "component." + this.view + "." + this.props.name;

        return (
            <React.Fragment>
                <label><Text name={this.label} /></label>
                <span style={styles}>{this.props.children}</span>
            </React.Fragment>
        );
    }
}

Field.contextType = ViewContext;


export default class ComponentView extends  React.Component {

    constructor(props) {
        super(props);
        this.ptitle = props.ptitle;
        this.stitle = props.stitle;
        this.title = props.title ? props.title : "component." + props.view;
        this.view = props.view;
    }

    render() {

        return (
            <ViewContext.Provider value={{view: this.view}}>
                <div className="view">
                    <header>{this.ptitle ? <Text name={this.ptitle}/> : null } <Text name={this.title} />{this.stitle ? <Text name={this.stitle}/> : null }</header>
                    { this.props.children  }
                </div>
            </ViewContext.Provider>
        );
    }
}

ComponentView.propTypes = {
    title: PropTypes.string,
    view: PropTypes.string,
}
