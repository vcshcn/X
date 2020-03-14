import React from 'react';

export default class GridLayout extends React.Component {

    constructor(props) {
        super(props);
        this.cols = props.cols ? parseInt(props.cols) : null;
        this.className = "grid-layout" + (props.className ? " " + props.className : "");
    }

    render() {
        let styles = {  
        };
        if (this.cols) {
            let col = (1 / this.cols * 100).toFixed(2);
            styles.gridTemplateColumns = "repeat("+this.cols+", "+(col*.3) + "% " +  (col*.7) + "%)";
        }

        return <div className={this.className} style={styles}>{this.props.children}</div>;
    }
}
