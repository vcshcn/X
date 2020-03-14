"use strict";
import React from 'react';
import PropTypes from 'prop-types';
import Text from './Text';
import '../css/dialog.less';

export default class Dialog extends React.Component {

    constructor(props) {
        super(props);

		this._x = 0;
        this._y = 0;
        this.title = this.props.title ? this.props.title : "Dialog";
        this.position = props.position ? props.position : "mouse";
		this._minWidth = 110;
        this._minHeight = 110;
        this.width = this.props.width ? this.props.width : ( props.position == "center" ?  document.body.clientWidth / 2 : 0 );
        
        this.state = {
            open: false,
			left : this.props.left == null ? (document.body.clientWidth - this.width) / 2 : this.props.left,
            top :  this.props.top == null ? (document.body.clientHeight) / 3 : this.props.top,
            width: this.width
       }
    }

	onMoveDragStart(event) {

		this._x = event.nativeEvent.offsetX;
		this._y = event.nativeEvent.offsetY;

		event.dataTransfer.effectAllowed="move";
        event.dataTransfer.dropEffect="move";
        		
		document.addEventListener("dragover", (e)=>this.onMoveDragOver(e));
		document.addEventListener("dragenter", (e)=>this.onMoveDragEnter(e));
        document.addEventListener("drop", (e)=>this.onMoveDrop(e));

	}
	
	onMoveDrag(event) {
		if (event.clientX > 0) {
			this.setState({
				left : event.clientX - this._x,
				top: event.clientY - this._y
			});
        }
        event.preventDefault();

	}
	
	onMoveDragEnter(event) {
		event.preventDefault();
	}
	
	
	onMoveDragOver(event) {
		this.setState({
			left : event.clientX - this._x,
			top: event.clientY - this._y
		});
		event.preventDefault();
		event.stopPropagation();
	}
	
	onMoveDrop(event) {
		document.removeEventListener("dragover", (e)=>this.onMoveDragOver(e));
		document.removeEventListener("dragenter", (e)=>this.onMoveDragEnter(e));
		document.removeEventListener("drop", (e)=>this.onMoveDrop(e));
		event.preventDefault();
    }

    show(event) {
        if (this.position == "mouse") {
            if (event)
                this.setState({left: event.clientX,  top: event.clientY, open: true});
            else
                this.setState({open: true});
        }
        else if (this.position == "center") {
            this.setState({open: true});
        }
    }

    getTitle() {
        return <Text name={this.title} />;
    }

    close(e) {
        this.setState({open:false});
        this.onClose(e);
    }

    onClose(e) {
        if (this.props.onClose)
            this.props.onClose(e);
    }

    componentDidMount() {
        document.body.appendChild(this.instance);
    }

    componentWillUnmount() {
        this.instance.remove();
    }

    getCloseButton() {
        return <button onClick={(e)=>this.close(e)} key="btnclose"><Text name="Close"/></button>;
    }

    getOkButton() {
        return <button onClick={(e)=>this.close(e)} key="btnok"><Text name="OK"/></button>;
    }


    getContent() {
        return this.props.children;
    }

    getButtons() {
        return this.getCloseButton();
    }

    render() {
        const styles = {
            left: this.state.left, 
            top: this.state.top, 
            display: this.state.open ? "":"none",
        }
        if (this.state.width > 0)
            styles.width = this.state.width;

     	return (
            <div ref={(mself)=>{this.instance=mself}} >
                {
                    this.props.modal && this.state.open ? <div className="mask" /> : null
                }
                <div className="dialog" style={styles} >
                    <header >
                        <div className="title" draggable="true" onDrag={(e)=>this.onMoveDrag(e)} onDragStart={(e)=>this.onMoveDragStart(e)}>{this.getTitle()}</div>
                        <div className="icon"><img src={require('../images/close.png')} onClick={(e)=>this.close(e)}/></div>
                    </header>
                    <section>
                        {this.getContent()}
                    </section>
                    <hr  />
                    <footer>
                        <div className="buttons">
                        {this.getButtons() }
                        </div>
                    </footer>
                    <div className="statusBar">
                        <span ></span>
                    </div>
                </div>
            </div>
    	);
    }
    

}
Dialog.propTypes = {
	title: PropTypes.string,
    modal: PropTypes.bool,
    width: PropTypes.number,
    height: PropTypes.number,
    position: PropTypes.oneOf(['center', 'mouse']),
}

Dialog.defaultProps = {
	title: 'Dialog',
    modal:	false,
    width:  0,
    height: 0,
    position: "center"
};

export class InformationDialog extends Dialog {

    getContent() {
        return this.props.text;
    }
}

InformationDialog.defaultProps = {
	title: 'Information',
	modal:	true
};

export class ErrorDialog extends Dialog {

    getContent() {
        return <React.Fragment><img src={require('../images/Error.png')} />{this.props.children}</React.Fragment>
    }

}

ErrorDialog.defaultProps = {
	title: 'Error',
    modal:	true,
    width: 300
};
