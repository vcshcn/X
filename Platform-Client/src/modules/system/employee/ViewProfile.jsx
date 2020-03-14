import React from 'react';
import { Link } from "react-router-dom";
import EnvContext from '../../../components/EnvContext';
import {Tabs, Tab, Text, Panel } from '../../../components/Controls';
import View, {Field} from '../../view/ComponentView';

class Profile extends React.Component {

    constructor(props) {
        super(props);
        this.user = this.props.user;
    }

    getPhotoRender() {
        return (
            <div>
                <img alt="my photo" src="ViewProfilePhoto.do" width="128px" height="128px" />
                <p></p>
                <div style={{textAlign:"center"}}> <a className="linkbutton">Not i</a> </div>
            </div>
        );
    }

    getProfileRender() {
        return (
            <View view="Employee">
                <Panel layout="grid" cols="3">
                    <Field name="name">{this.user.name}</Field>
                    <Field name="gender">{this.user.gender}</Field>
                    <Field name="role" >{this.user.role ? this.user.role.name : null}</Field>

                    <Field name="org" cols="3">{this.user.department ? this.user.department.org.name : null}</Field>
                    <Field name="department" > {this.user.department ? this.user.department.name : null}</Field>

                    <Field name="nickname">{this.user.nickname}</Field>
                    <Field name="tel">{this.user.tel}</Field>
                    <Field name="mobile" >{this.user.mobile}</Field>

                    <Field name="birthday">{this.user.birthday}</Field>
                    <Field name="email">{this.user.email}</Field>
                    <Field name="homepage" ><a href={this.user.homepage} target="_blank" style={{color:"blue"}}>{this.props.user.homepage}</a></Field>

                    <Field name="zipcode">{this.user.zipcode}</Field>
                    <Field name="address" cols="3">{this.user.address}</Field>

                    <Field name="country">{this.user.country ? this.props.user.country.name : null}</Field>
                    <Field name="language">{this.user.locale ? this.props.user.locale.name : null}</Field>
                    <Field name="timezone">{this.user.timezone ? this.props.user.timezone.name : null}</Field>
                </Panel>
                <footer>
                    <p />
                    <Link to="/ModifyProfile/Profile"><Text name="Modify" /></Link>
                </footer>
            </View>
        );
    }

    render() {
        return <Panel layout="border" left={this.getPhotoRender()} center={this.getProfileRender()} />;
    }
}

Profile.contextType = EnvContext;

class Account extends React.Component {
    render() {
        return (
            <View view="Login" >
                <Panel layout="grid" cols="1">
                    <Field name="loginname">********</Field>
               </Panel>
                <footer>
                    <p />
                    <Link to="/ModifyProfile/Account"><Text name="Modify" /></Link>
                </footer>
            </View>
        );
    }
}

class Customize extends React.Component {

    constructor(props) {
        super(props);
        this.user = this.props.user;
    }

    render() {
        return (
            <View view="Customize" >
                <Panel layout="grid" cols="3">
                    <Field name="dataformat">{this.user.customize.dateformat}</Field>
                    <Field name="timeformat">{this.user.customize.timeformat}</Field>
                    <Field name="datatimeformat">{this.user.customize.datetimeformat}</Field>
                    <Field name="pagesize">{this.user.customize.pagesize}</Field>
                    <Field name="style"><Text name={this.user.customize.style}/></Field>
               </Panel>
               <footer>
                    <p />
                    <Link to="/ModifyProfile/Customize"><Text name="Modify" /></Link>
                </footer>	
            </View>
        );
    }
}


export default class ViewProfile extends React.Component {

    constructor(props) {
        super(props);
        this.active = this.props.match && this.props.match.params.active ? this.props.match.params.active : "Profile";
    }

    render() {
        return (
            <Tabs active={this.active}>
                <Tab key="Profile" title="Profile"><Profile {...this.props}/></Tab>
                <Tab key="Account" title="Account"><Account {...this.props} /></Tab>
                <Tab key="Customize" title="Customize"><Customize {...this.props} /></Tab>
            </Tabs>
        );
    }
}
ViewProfile.contextType = EnvContext;
