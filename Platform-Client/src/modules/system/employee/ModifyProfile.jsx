import React from 'react';
import PropTypes from 'prop-types';
import { Link } from "react-router-dom";
import {Tabs, Tab, Text, Panel, ErrorDialog} from '../../../components/Controls';
import { DateInput, Select } from '../../../components/Forms';
import ajax from '../../../components/Ajax';
import { getText } from '../../../components/Text';
import EnvContext from '../../../components/EnvContext';
import View, {Field} from '../../view/ComponentView';

class Profile extends React.Component {

    constructor(props) {
        super(props);
        this.user = props.user;

        this.nicknameKey = React.createRef();
        this.telKey = React.createRef();
        this.mobileKey = React.createRef();
        this.birthdayKey = React.createRef();
        this.emailKey = React.createRef();
        this.homepageKey = React.createRef();
        this.zipcodeKey = React.createRef();
        this.addressKey = React.createRef();
        this.countryKey = React.createRef();
        this.localeKey = React.createRef();
        this.timezoneKey = React.createRef();

    }

    SaveProfile(e) {
        e.preventDefault();
        let params = {
            nickname : this.nicknameKey.current.value,
            tel : this.telKey.current.value,
            mobile : this.mobileKey.current.value,
            birthday : this.birthdayKey.current.value,
            email : this.emailKey.current.value,
            homepage : this.homepageKey.current.value,
            zipcode : this.zipcodeKey.current.value,
            address : this.addressKey.current.value,
            countryid:  this.countryKey.current.value ? parseInt(this.countryKey.current.value) : null,
            localeid: this.localeKey.current.value ? parseInt(this.localeKey.current.value) : null,
            timezoneid:  this.timezoneKey.current.value ? parseInt(this.timezoneKey.current.value) : null
        };

        ajax.post("SaveProfileInformation.do", params)
        .then((response)=>{
            if (response.data.error == false) {
                ((u)=>this.context.setUser(u))(response.data.object);
                this.props.history.push("/ViewProfile/Profile");
            }
        })
        .catch(ex=>{
            console.error(ex);
        });
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
                <form autoComplete="off" onSubmit={(e)=>this.SaveProfile(e)}>
                    <Panel layout="grid" cols="3">
                        <Field name="name">{this.user.name}</Field>
                        <Field name="gender">{this.user.gender}</Field>
                        <Field name="role">{this.user.role ? this.user.role.name : null}</Field>
                        <Field name="org" cols="3">{this.user.department ? this.user.department.org.name : null}</Field>
                        <Field name="department" >{this.user.department ? this.user.department.name : null}</Field>
                    </Panel>
                    <br/>
                    <Panel layout="grid" cols="3">
                        <Field name="nickname" required={true} >
                            <input type="text" name="nickname" ref={this.nicknameKey} defaultValue={this.user.nickname} required={true} />
                        </Field>
                        <Field name="tel">
                            <input type="text" name="tel" ref={this.telKey} defaultValue={this.user.tel }/>
                        </Field>
                        <Field name="mobile">
                            <input type="text" name="mobile" ref={this.mobileKey} defaultValue={this.user.mobile}/>
                        </Field>

                        <Field name="birthday">
                            <DateInput name="birthday" value={this.user.birthday} ref={this.birthdayKey} />
                        </Field>
                        <Field name="email">
                            <input type="email" ref={this.emailKey} name="email" defaultValue={this.user.email} />
                        </Field>
                        <Field name="homepage">
                            <input type="url" ref={this.homepageKey} name="homepage" defaultValue={this.user.homepage} />
                        </Field>

                        <Field name="zipcode">
                            <input  type="text" name="zipcode" ref={this.zipcodeKey} defaultValue={this.user.zipcode}/>
                        </Field>
                        <Field name="address" cols="3">
                            <input type="text" name="address" ref={this.addressKey} defaultValue={this.user.address}/>
                        </Field>

                        <Field name="country">
                            <Select name="Country" ref={this.countryKey} valueKey="countryid" labelKey="name" value={this.user.country ? this.user.country.countryid : null}/>
                        </Field>
                        <Field name="language">
                            <Select name="Locale" ref={this.localeKey} valueKey="localeid" labelKey="name" value={this.user.locale ? this.user.locale.localeid : null} />
                        </Field>
                        <Field name="timezone">
                            <Select name="TimeZone" ref={this.timezoneKey} valueKey="timezoneid" labelKey="name" value={this.user.timezone ? this.user.timezone.timezoneid : null}/>
                        </Field>

                        <Field name="aboutme" cols="5">
                            <textarea></textarea>
                        </Field>
                    </Panel>
                    <footer>
                        <input type="submit" />&nbsp;
                        <Link to="/ViewProfile/Profile"><Text name="View" /></Link>
                    </footer>
                </form>
            </View>
        );
    }

    render() {
        return  <Panel layout="border" left={this.getPhotoRender()} center={this.getProfileRender()} />;
    }
}

Profile.contextType = EnvContext;

Profile.propTypes = {
    user : PropTypes.object.isRequired
}

class Account extends React.Component {

    constructor(props) {
        super(props);
        this.user = props.user;
        this.password = React.createRef();
        this.password2 = React.createRef();
        this.errorDlg = React.createRef();
    }

    SaveProfilePassword(e) {
        e.preventDefault();

        let password = this.password.current.value;
        let password2 = this.password2.current.value;
        if (password != password2) {
            this.errorDlg.current.show();
            return false;
        }

        ajax.post("SaveProfilePassword.do", { password: password} )
        .then((response)=>{
            if (response.data.error == false) {
                this.props.history.push("/ViewProfile/Account");
            }
        })
        .catch(ex=>{
            console.error(ex);
        });

    }

    render() {
        return (
            <React.Fragment>
                <ErrorDialog ref={this.errorDlg}><Text name="tip.PasswordNotMatch" /></ErrorDialog>
                <View view="Login" title="Account">
                    <form autoComplete="off" onSubmit={(e)=>this.SaveProfilePassword(e)}>
                        <Panel layout="grid" cols="1">
                            <Field name="loginname">********</Field>
                            <Field name="password">
                                <input type="password" name="password" defaultValue="******" required={true}  ref={this.password}/>
                            </Field>
                            <Field name="password">
                                <input type="password" defaultValue="******" required={true}  ref={this.password2}/>
                            </Field>
                        </Panel>
                        <footer>
                            <input type="submit" />&nbsp;
                            <Link to="/ViewProfile/Account"><Text name="View" /></Link>
                        </footer>			
                     </form>
                </View>
            </React.Fragment>
        );
    }
}

Account.propTypes = {
    user : PropTypes.object.isRequired
}


class Customize extends React.Component {
    constructor(props) {
        super(props);
        this.user = props.user;

        this.state = {
            dateformat : this.user.customize.dateformat,
            timeformat : this.user.customize.timeformat,
            datetimeformat: this.user.customize.datetimeformat,
            pagesize : this.user.customize.pagesize,
            style : this.user.customize.style
        }
    }

    SaveProfileCustomize(e) {
        e.preventDefault();
        let params = {
            dateformat : this.state.dateformat,
            timeformat : this.state.timeformat,
            datetimeformat : this.state.datetimeformat,
            pagesize : this.state.pagesize,
            style : this.state.style
        };

        ajax.post("SaveProfileCustomize.do", params)
        .then((response)=>{
            if (response.data.error == false) {
                ((u)=>this.context.setUser(u))(response.data.object);
                this.props.history.push("/ViewProfile/Customize");
            }
        })
        .catch(ex=>{
            console.error(ex);
        });
    }

    toggleStyle(e) {
        this.context.getThemeService.applyTheme(e.target.value);
        this.setState({style: e.target.value});
    }

    render() {
        return (
            <View view="Customize" >
            <form autoComplete="off" onSubmit={(e)=>this.SaveProfileCustomize(e)}>
                <Panel layout="grid" cols="3">
                    <Field name="dataformat">
                        <select value={this.state.dateformat} onChange={(e)=>this.setState({dateformat: e.target.value})}>
                            <option value="yyyy-MM-dd">yyyy-MM-dd</option>
                            <option value="MM/dd/yyyy">MM/dd/yyyy</option>
                        </select>
                    </Field>

                    <Field name="timeformat">
                        <select value={this.state.timeformat} onChange={(e)=>this.setState({timeformat: e.target.value})}>
                            <option value="HH:mm">HH:mm</option>
                            <option value="HH:mm:ss">HH:mm:ss</option>
                        </select>
                    </Field>
                    <Field name="datatimeformat">
                        <select value={this.state.datetimeformat} onChange={(e)=>this.setState({datatimeformat: e.target.value})}>
                            <option value="yyyy-MM-dd HH:mm">yyyy-MM-dd HH:mm</option>
                            <option value="yyyy-MM-dd HH:mm:ss">yyyy-MM-dd HH:mm:ss</option>
                            <option value="MM/dd/yyyy HH:mm">MM/dd/yyyy HH:mm</option>
                        </select>
                    </Field>
                    <Field name="pagesize">
                        <input type="range" min="5" max="50" step="5" defaultValue={this.state.pagesize} />                    
                    </Field>
                    <Field name="style">
                        <select value={this.state.style}  onChange={(e)=>this.toggleStyle(e)}>
                            <option value="orange" >{getText("orange")}</option>
                            <option value="dark" >{getText("dark")}</option>
                        </select>
                    </Field>
                </Panel>
                <footer>
                    <input type="submit" />&nbsp;
                    <Link to="/ViewProfile/Customize"><Text name="View" /></Link>
                </footer>	
            </form>
            </View>
        );
    }
}

Customize.contextType = EnvContext;

Customize.propTypes = {
    user : PropTypes.object.isRequired
}


export default class ModifyProfile extends React.Component {

    constructor(props) {
        super(props);
        this.active = this.props.match && this.props.match.params.active ? this.props.match.params.active : "Profile";
    }

    render() {
        return (
            <Tabs active={this.active}>
                <Tab key="Profile" title="Profile"><Profile  {...this.props}/></Tab>
                <Tab key="Account" title="Account"><Account  {...this.props}/></Tab>
                <Tab key="Customize" title="Customize"><Customize  {...this.props}/></Tab>
            </Tabs>
        );
    }
}
