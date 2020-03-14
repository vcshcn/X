import React from 'react';
import EnvContext, {registerApplication} from '../components/EnvContext';
import LoginFrame from './LoginFrame';
import MainFrame from './MainFrame';
import EnumTable from '../components/EnumTableService';
import DataView from '../components/DataViewService';
import Storage from '../components/StorageService';
import Theme from '../components/ThemeService';
import Browser from '../components/BrowserService';

// default theme css
//import '../css/skin.orange.less';
export default class App extends React.Component {

    login(user) {
        this.setUser(user);
    }

    logout() {
        this.setUser(null);

        let len = window.sessionStorage.length;
        for (let i=len-1; i>=0; i--) {
            let key = window.sessionStorage.key(i);
            if (key.startsWith("tmp."))
                window.sessionStorage.removeItem(key);
        }
    }

    setUser(user) {
        this.storeUserToStorage(user);

        if (user && user.customize && user.customize.style)
            this.GlobalContext.getThemeService.applyTheme( user.customize.style);
        
        if (user && user.locale && user.locale.code) {
            document.body.lang = user.locale.code;
        }
        window.setTimeout(()=>{
            this.setState({user: user});
        },10)
    }

    getUser() {
        return this.state.user;
    }

    registerService(container) {
        let t = this;

        return (function(container) {

            let browser = new Browser();
            let storage = new Storage(window.sessionStorage);
            let dataview = new DataView(storage);
            let enumtable = new EnumTable(storage);
            let theme = new Theme(t.getUser());

            container.getStorageService = storage;
            container.getDataViewService = dataview;
            container.getEnumTableService = enumtable;
            container.getThemeService = theme;
            container.getBrowserService = browser;

            container.getAppService = {
                setTitle:  (title)=>{document.title=title},
                getTitle:  ()=>{return document.title},
                getTheme:  ()=>{return this.getThemeService.getCurrentTheme()}
            }

            registerApplication(t);
        })(container);
    }

    constructor(props) {
        super(props);

        this.state = {
            user: this.restoreUserFromStorage()
        }

        this.GlobalContext = {
            getUser :   ()=>  { return this.getUser(); },
            setUser :   (u)=> { this.state.user = u;
                                window.setTimeout( 
                                    ()=>{this.setUser(this.state.user)}
                                ,30)},
            logout :    ()=>  { this.logout(); }
        }
        this.registerService(this.GlobalContext);
        this.setUser(this.state.user);
    }

    render() {
        return (
            <EnvContext.Provider value={this.GlobalContext}>
            {
                this.state.user == null ? <LoginFrame loginCallback={(u)=>{this.login(u)}} {...this.props}/> : <MainFrame user={ this.state.user} {...this.props} />
            }
            </EnvContext.Provider>
        )
    }
    
    storeUserToStorage(user) {
        if (user)
            window.sessionStorage.setItem("user", JSON.stringify(user) );
        else 
            window.sessionStorage.removeItem("user");
    }

    restoreUserFromStorage() {
        return JSON.parse(window.sessionStorage.getItem("user"));
    }

}
