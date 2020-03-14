"use strict";
import React from 'react';
import PropTypes from 'prop-types';
import EnvContext, { Defaults } from './EnvContext';
import Calendar from './Calendar';
import Text, {getText} from './Text';
import { Dialog, Panel } from './Controls';
import ajax from './Ajax';
import Table from './Table';
import '../css/form.less';

/**
 * Select 
 */
export class Select extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            values : [],
            value : this.props.value ? this.props.value : null
        }

        this.name = this.props.name;
        this.hasNull = this.props.hasNull ? this.props.hasNull : false;
        this.valueKey = this.props.valueKey ? this.props.valueKey : null;
        this.labelKey = this.props.labelKey ? this.props.labelKey : null;
    }

    componentDidMount() {
        let EnumTable = this.context.getEnumTableService;

        EnumTable.getItems(this.name).then( (v)=>{
            this.setState({values: v});
        });

    }

    get value() {
        return this.state.value;
    }

    onChange(e) {
        this.setState({value: e.target.value});
        if (this.props.onChange) 
            this.props.onChange(this.state.value);
    }

    render() {
        this.user = this.context.getUser();
        return (
            <select value={this.state.value} onChange={(e)=>this.onChange(e)}>
                {
                    (this.hasNull ? <option></option> : null)
                }
                {
                    this.state.values && this.state.values.length > 0 != null ? this.state.values.map((item, idx)=>{

                        return (<option key={idx} value={this.valueKey ? item[this.valueKey] : item} >{ this.labelKey ? item[this.labelKey]:getText("enum."+this.name+"."+item)}</option>);
                    }) : null
                }
            </select>
        );
    }
}

Select.propTypes = {
    name : PropTypes.string.isRequired,
    labelKey : PropTypes.string,
    valueKey : PropTypes.string,
    value : PropTypes.any,
    hasNull : PropTypes.bool
};

Select.defaultProps = {
    hasNull: true
};

Select.contextType = EnvContext;


/**
 * Input Date from Calendar
 */
export class DateInput extends React.Component {

    constructor(props) {
        super(props);
        
        this.calendarRef = React.createRef();
        this.inputRef = React.createRef();

        this.format = this.props.format ? this.props.format : "date";
        this.val = this.props.value;

        if (this.val && this.val.indexOf("T")>0) {
            this.val = this.val.substring(0, this.val.indexOf("T"));
        }
    }

    pop(e) {
        this.calendarRef.current.show(e);
    }

    get value() {
        return this.inputRef.current.value;
    }

    set value(value) {
        if (value && value.indexOf("T")>0) {
            value = value.substring(0, value.indexOf("T"));
        }
        this.inputRef.current.value = value;
    }

    render() {
        return (
            <React.Fragment>
                <Calendar ref={this.calendarRef} ></Calendar>
                <input name={this.props.name} ref={this.inputRef} defaultValue={this.val}  className="dateinput" style={this.props.style} onDoubleClick={(e)=>this.pop(e)} autoComplete="off"/>
            </React.Fragment>
        );
    }


}

/**
 *  Date
 */
export class DateValue extends React.Component {

    constructor(props) {
        super(props);
        this.format = this.props.format;
    }

    render() {
        this.value = this.props.value ? new Date(this.props.value) : null;

        let pattern = null,  user = this.context.getUser();

        if (user && user.customize)
            pattern = "date" == this.format ? user.customize.dateformat : user.customize.datetimeformat;

        if (pattern == null) 
            pattern = "date" == this.format ? Defaults.dateformat : Defaults.datetimeformat;

        return this.value ? "date" == this.format ? this.value.FormatDate(pattern) : this.value.FormatTime(pattern) : null;
    }
}

DateValue.propTypes = {
    value : PropTypes.string,
    format : PropTypes.oneOf(['date', 'datetime'])
};

DateValue.defaultProps = {
    format : 'date'
};

DateValue.contextType = EnvContext;



/**
 * PickUp
 */
export class PickupDialog extends Dialog {

    constructor(props) {
        super(props);
        this.view = props.view;
        this.onPickup = props.onPickup;
    }

    onSubmit(e) {
        e.preventDefault();
    }

    getTitle() {
        return <React.Fragment><Text name={this.props.title} />{" "}<Text name="Pickup" /></React.Fragment>;
    }

    getContent() {
        return  (
            <div className="pickup-dialog">
                <div className="search-bar">
                    <Panel layout="flex" direction="row">
                        <form onSubmit={(e)=>this.onSubmit(e)}>
                            <label><Text name="Name" /></label>
                            <input type="search" autoFocus  />
                            <input type="submit" />
                        </form>
                    </Panel>
                </div>
                <div>
                    <Table view={this.view} onPickup={(e, id, name, object)=>this.onPickup(e, id, name, object)} addButton={false} modifyColumnButton={false} viewButton={false} modifyButton={false} deleteButton={false} pickupButton={true} />
                </div>
            </div>
        );
    }
}

PickupDialog.propTypes = {
};

PickupDialog.defaultProps = {
    position : "center"
};


export class Pickup extends React.Component {

    constructor(props) {
        super(props);
        this.pickupDialog = React.createRef();
        this.view = props.view;

        this.state = {
            name : null,
            id : null
        }
    }

    show(e, id, name, object) {
        this.pickupDialog.current.show(e);
    }

    pick(e, id, name, object) {
        this.setState({ name: name, id: id });
        this.pickupDialog.current.close();
    }

    render() {
        return (
            <React.Fragment>
                <input type="text" required={this.props.required} defaultValue={this.state.name} readOnly/>
                <input type="hidden" defaultValue={this.state.id} />
                <img className="pickup-img" src={require('../images/Search.png')} onClick={(e)=>this.show(e)} />
                <PickupDialog onPickup={(e, id, name, object)=>this.pick(e, id, name, object)} ref={this.pickupDialog} view={this.view} title={"component."+this.view} />
            </React.Fragment>
        );
    }
}
