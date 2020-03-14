import React from 'react';
import Ajax from '../components/Ajax';
import Footer from './Footer';
import I18n from '../components/Text';
import '../css/LoginFrame.module.less';

// so so version
export default class LoginFrame extends React.Component {
    
    constructor(props) {
        super(props);

        this.loginname = React.createRef();
        this.password = React.createRef();

        this.state = {
            hasError: false,
            AccountIsEmpty : false,
            PasswordIsEmpty : false,
            NoSuchAccount : false,
            PasswordNotMatch: false,
            AccountLock: false
        }
    }

    clearState() {
        this.setState({
            hasError: false,
            AccountIsEmpty : false,
            PasswordIsEmpty : false,
            NoSuchAccount : false,
            PasswordNotMatch : false,
            AccountLock: false
        });
    }

    login(event) {
        this.clearState();
        Ajax.get("Login.do", {
            params: {  
                loginname: this.loginname.current.value,
                password: this.password.current.value
             }    
        })
        .then((response)=> {
            if (response.data.error == true) {
                for (let key in response.data.errors) {
                    key = parseInt(key);
                    this.setState({ hasError : true });
                    if (key == 1) 
                        this.setState({ AccountIsEmpty : true });
                    else if (key == 2) 
                        this.setState({ PasswordIsEmpty : true });
                    else if (key == 3)
                        this.setState({ NoSuchAccount : true });
                    else if (key == 4)
                        this.setState({ PasswordNotMatch : true });
                }
            }
            else {
                ((user)=>{this.props.loginCallback( user )})(response.data.object);
            }
        })
        .catch((ex)=>{
            console.dir(ex);
        });

        event.preventDefault();
    }

    render() {
        return (
            <div className="LoginForm" style={{ textAlign:'center' }} >
                <div>
                    <div className="aside">
                    </div>
                    <div style={{height: '100px'}}>
                    </div>
                
                    <div className="outer">
                        <div className="inner">
                            <header>
                                <span><I18n name="Login" /></span>
                            </header>
                            <hr style={{color: 'lightgray'}}/>
                            <div className="login">
                                <form method="post" autoComplete="off" name="form1" onSubmit={ ()=>{return false}}>
                                    <input type="hidden" name="height" />
                                    <input type="hidden" name="width" />
                                    <input type="hidden" name="color" />
                                    <input type="hidden" name="dpi" />
                                    <input type="hidden" name="offset" />
                                    <input type="hidden" name="latitude" />
                                    <input type="hidden" name="longitude" />
                                    {
                                        this.state.hasError ?  (
                                            <div  style={{color:"red", textAlign:"left", fontSize: "large"}}>
                                                <img width="24px" height="24px" src={require('../images/Error.png')}  align="AbsBottom" border="0" style={{marginLeft:"50px"}}/>
                                                <strong><I18n name="tip.LoginError" /></strong>
                                            </div>
                                        ) : null
                                    }
                                    <div>
                                        <label htmlFor="loginname"><I18n name='Account' /></label>
                                        <span><input type="text" ref={this.loginname} required autoFocus defaultValue="admin" /></span>
                                        <em>
                                            { this.state.AccountIsEmpty ? (<I18n name="tip.AccountIsEmpty" />) : null }
                                            { this.state.NoSuchAccount ? (<I18n name="tip.NoSuchAccount" />) : null }
                                            { this.state.AccountLock ? (<I18n name="tip.AccountLock" />) : null }
                                        </em>
                                    </div>
                                    <div>
                                        <label htmlFor="password"><I18n name='Password' /></label>
                                        <span><input type="password" ref={this.password} required defaultValue="admin"/></span>
                                        <em>
                                            { this.state.PasswordIsEmpty ?  (<I18n name="tip.PasswordIsEmpty" />) : null }
                                            { this.state.PasswordNotMatch ?  (<I18n name="tip.PasswordNotMatch" />) : null }
                                        </em>
                                    </div>
                                    <div>
                                        <label>&nbsp;</label>
                                        <span><button  onClick={ (event)=>this.login(event) } ><I18n name="Login" /></button></span>
                                    </div>
                                </form>
                            </div>
                            <footer>
                                <hr style={{color: 'lightgray'}} />
                                <br />&nbsp;
                                <br />&nbsp;
                            </footer>
                        </div>
                    </div>
                </div>
                <Footer />
            </div>
        );    
    }

}
