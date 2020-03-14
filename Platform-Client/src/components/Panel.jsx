import React from 'react';
import BorderLayout from './layout/BorderLayout';
import EmptyLayout from './layout/EmptyLayout';
import GridLayout from './layout/GridLayout';
import FlexLayout from './layout/FlexLayout';


export default class Panel extends React.Component {

    getLayout() {
        if (this.props.layout == "border")
            return <BorderLayout {...this.props} />;
        else if (this.props.layout == "empty")
            return <EmptyLayout {...this.props} />;
        else if (this.props.layout == "grid")
            return <GridLayout {...this.props} />
        else if (this.props.layout == "flex")
            return <FlexLayout {...this.props} />
        else if (this.props.layout == null) 
            return <EmptyLayout {...this.props}/>;
    }


    render() {
        return (
            <div className="panel">
                { this.getLayout() }
            </div>
        );    
    }
}

