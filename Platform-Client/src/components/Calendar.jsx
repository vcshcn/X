"use strict";
import React from 'react';
import PropTypes from 'prop-types';
import Ajax from './Ajax';
import Dialog from './Dialog';
import Text from './Text';
import EnvContext from './EnvContext';
import '../css/calendar.less';



function StringArray(s) {
    
    let i = 0;
    let length = s.length;
    let str = s;

    this.next = function() {
        if (i < length)
            return str.charAt(i++);
        else 
            return null;
    }

    this.length = function() {
        return str.length;
    }

    this.reset = function() {
        i = 0;
    }

    this.toString = function() {
        return str;
    }
}


function parse(format, value) {  
    if (!format)
        throw "Illegal Argument Exception of first parameter format is " + format;
    if (!value)
        throw "Illegal Argument Exception of second parameter value is " + value;

    let year="", month="", date="", hour="", minute="", second="", millsecond="";

    let fArray = new StringArray(format);
    let vArray = new StringArray(value);

    let lastFormat = null;

    let pf = fArray.next(), pc = vArray.next();
    let f = pf, c =pc;

    while (pf || pc) {
        if (pc == null || (pf == null && lastFormat == null)) break; 
        if (pf == null ) 
            f = lastFormat;
        else 
            f = pf;
        
        c = pc;
        if (f == c) {
            pf = fArray.next();
            pc = vArray.next();
            lastFormat = null;
            continue ;
        }

        if ( c.isDigit() ) {
            if (f.isAlpha() == false)
                f = lastFormat;

            switch (f) {
                case 'Y': year += c; break;
                case 'M': month += c; break;
                case 'd': date += c; break;
                case 'H': hour += c; break;
                case 'm': minute +=c; break;
                case 's': second +=c; break;
                case 'S': millsecond +=c; break; 
            }
            
            pc = vArray.next();
            if (pf != null && pf.isAlpha()) 
                pf = fArray.next();
            
            lastFormat = f;
            continue ;
        }
        else if (pf != null) {
            pf = fArray.next();
        }
        else {
            console.warn("Argument " + value + " Not Match of  " + format);
            return null;
        }

    }

    year = year!="" ? parseInt(year) : 0; 
    month = month!="" ? parseInt(month)-1 : 0;
    date = date!= "" ? parseInt(date) : 1;
    hour = hour!= "" ? parseInt(hour) : 0;
    minute = minute!= "" ? parseInt(minute) : 0;
    second = second!="" ? parseInt(second) : 0;
    millsecond = millsecond!="" ? parseInt(millsecond) : 0;
    
    let d = new Date();
    d.setFullYear(year);
    d.setMonth(month);
    d.setDate(date);
    d.setHours(hour);
    d.setMinutes(minute);
    d.setSeconds(second);
    d.setMilliseconds(millsecond);
    return d;
}


export default class Calendar extends Dialog {

    constructor(props) {
        super(props);

        this.state.date = this.props.date ? new Date(this.props.date) : null;
        this.state.today = new Date();
    }

    pick(event, dateObj) {
        if (this.props.callback) {
            this.props.callback(dataObj);
        }
        else if (this.target ) {
            if (this.props.type == 'date')
                this.target.value = dateObj ? dateObj.FormatDate(this.context.getUser().customize.dateformat) : "";
            else if (this.props.type == 'datetime')
                this.target.value = dateObj ? dateObj.FormatDate(this.context.getUser().customize.datetimeformat) : "";
        }

        super.close(event);
        return dateObj;
    }

    show(event) {
        event.persist();

        if (event && event.target) 
            this.target = event.target;

        if (event && event.target && event.target.value)
            this.setState({date: parse("YYYY-MM-dd", event.target.value)});
        
        Ajax.get("Calendar.do")
        .then((response)=>{
            let now = new Date(parseInt(response.data));

            this.setState({date: this.state.date? this.state.date : now, today: now});
            super.show(event);
        })
        .catch(ex=>{
            console.error(ex);
        });
    }

    onPrevYear() {
        let d = this.state.date.decYear();
        this.setState({date: d});
    }

    onNextYear() {
        let d = this.state.date.incYear();
        this.setState({date: d});
    }

    onPrevMonth() {
        let d = this.state.date.decMonth();
        this.setState({date: d});
    }

    onNextMonth() {
        let d = this.state.date.incMonth();
        this.setState({date: d});
    }

    changeYear(year) {
        let d = this.state.date;
        d.setFullYear(year);
        this.setState({date: d});
    }

    changeMonth(month) {
        let d = this.state.date;
        d.setMonth(month-1);
        this.setState({date: d});
    }

    clear(event) {
        this.pick(event, null);
    }

    today(event) {
        this.pick(event, this.state.today);
    }

	getButtons() {
        return [ <button className="clear-button" onClick={e=>this.clear(e)} key="btnclear"><Text name="Calendar.clear"/></button>, <button className="today-button" onClick={(e)=>this.today(e)} key="btntoday"><Text name="Calendar.today"/></button>, this.getCloseButton() ];
    }

    getContent() {
        let date = this.state.date ? this.state.date : new Date();
        let year = date.getFullYear();
        let month = date.getMonth() + 1;
        let week = getWeek(date);
        let firstDate = getCalendarFirstDate(date);
        let weeks = getWeeks(date);

        let body = [];
        for (let i=1; i<=weeks; i++) {
            
            let tds = [];
            for (let j=1; j<=7; j++) {

                let klass = "day";
                if (firstDate.equals(this.state.today)) {
                    klass += " today";
                }
                else  if (j == 1) {
                    klass += " sunday";
                }
                else  if (week == i) {
                    klass +=  " week";
                }
                if (firstDate.equals(this.state.date))
                    klass += " select";

                let dup = firstDate;
                tds.push(<td key={i*j} className={klass} onClick={(e)=>this.pick(e, dup)}><a>{firstDate.getDate()}</a></td>);
                firstDate = firstDate.incDate();
            }
            body.push(<tr key={i}>{tds}</tr>);
        }


        return (
                <div className="calendar">
                    <section>
                        <a onClick={()=>this.onPrevYear()}>&lt;&lt;&nbsp;</a>
                        <a  onClick={()=>this.onPrevMonth()}>&lt;&nbsp; </a>
                        <select value={year} onChange={ (e)=>{this.changeYear(e.target.value)}}>
                        { [year-10, year-9, year-8, year-7, year-6, year-5, year-4, year-3, year-2, year-1, year, year+1, year+2, year+3, year+4, year+5, year+6, year+7, year+8, year+9, year+10].map( (year)=>{
                            return <option value={year} key={year}>{year}</option>
                        } )}
                        </select>
                        &nbsp;&nbsp;
                        <select value={month} onChange={(e)=>this.changeMonth(e.target.value)}>
                        { [1,2,3,4,5,6,7,8,9,10,11,12].map((month)=>{
                            return <option value={month} key={month}>{month}</option>
                        }) }  
                        </select>
                        <a onClick={()=>this.onNextMonth()}>&nbsp; &gt;</a>
                        <a onClick={()=>this.onNextYear()}>&nbsp;&gt;&gt;</a>
                    </section>
                    <hr/>
                    <table>
                        <thead>
                            <tr>
                                <th key="0"><Text name="Calendar.sunday" /></th>
                                <th key="1"><Text name="Calendar.monday" /></th>
                                <th key="2"><Text name="Calendar.tuesday" /></th>
                                <th key="3"><Text name="Calendar.wednesday" /></th>
                                <th key="4"><Text name="Calendar.thursday" /></th>
                                <th key="5"><Text name="Calendar.friday" /></th>
                                <th key="6"><Text name="Calendar.satursday" /></th>
                            </tr>
                        </thead>
                        <tbody >
                            {body}
                        </tbody>
                    </table>
                </div>
        );
    }

}

Calendar.contextType = EnvContext;

Calendar.propTypes = {
    title: PropTypes.string,
    type: PropTypes.oneOf(['date', 'datetime']).isRequired,
    modal: PropTypes.bool
}

Calendar.defaultProps = {
    type: 'date',
    title: 'Calendar',
    modal: true,
    position: "mouse"
}

function getWeeks(now) {
    let d = new Date(now.getTime());
    d.setDate(1);
    let p = d.getDay();
    d.setMonth(d.getMonth() + 1);
    d.setDate(1);
    d = d.decDate();
    return parseInt( (p + d.getDate() + (6-d.getDay())) / 7 );
}

function getWeek(now) {
    let d = new Date(now.getTime());
    d.setDate(1);
    let p = d.getDay();
    let i = p + now.getDate() + (6-now.getDay());
    return Math.ceil( i / 7 );

}

function getCalendarFirstDate(now) {
    let d = new Date(now.getTime());
    d.setDate(1);
    d.setTime(d.getTime() - d.getDay() * 24 * 60 * 60 * 1000);
    return d;
}
