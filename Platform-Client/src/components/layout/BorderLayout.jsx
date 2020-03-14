import React from 'react';

export default class BorderLayout extends React.Component {

    getTop() {
        if (this.props.top != null) {
            return (<div>{this.props.top}</div>)
        }
        else 
            return null;
    }

    getBottomer() {
        if (this.props.bottom != null) {
            return (<div> {this.props.bottom} </div>)
        }
        else 
            return null;
    }

    getLeft() {
        if (this.props.left != null) {
            return (
                <React.Fragment>
                    <td key="left" valign="top" width="230px">{this.props.left}</td>
                    <td key="split1" width="1px" style={{background:"lightblue"}}></td>
                </React.Fragment>
            );
        }
        else 
            return null;
    }

    getCenter() {
        if (this.props.center != null) 
            return (<td key="center" valign="top">{this.props.center}</td>);
        else 
            return null;
    }

    getRight() {
        if (this.props.right != null) {
            return (
                <React.Fragment>
                    <td key="split2" width="1px" style={{background:"lightblue"}}></td>
                    <td key="right" valign="top" width="200px">{this.props.right}</td>
                </React.Fragment>
            );
        }
        else 
            return null;
    }

    render() {
        return (
            <React.Fragment>
                { this.getTop() }

                <table className="borderlayout-main">
                    <tbody>
                        <tr>
                            { this.getLeft() }
                            { this.getCenter() }
                            { this.getRight() }
                        </tr>
                    </tbody>
                </table>

                {this.getBottomer()}
            </React.Fragment>
        );
    }
}

