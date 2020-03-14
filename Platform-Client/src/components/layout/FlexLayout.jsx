import React from 'react';
import PropTypes from 'prop-types';
import '../../css/form.less';

export default class FlexLayout extends React.Component {

    constructor(props) {
        super(props);
        this.className = "flex-layout" + (props.className ? " " + props.className : "");
        this.direction = props.direction;
    }


    render() {
        let styles = { };
        if (this.direction)
            styles.flexDirection = this.direction;


        return <div className={this.className} style={styles}>{this.props.children}</div>;
    }
}

FlexLayout.propTypes = {
    direction : PropTypes.oneOf(['row', 'column']).isRequired,
};

FlexLayout.defaultProps = {
	direction : "row",
};
