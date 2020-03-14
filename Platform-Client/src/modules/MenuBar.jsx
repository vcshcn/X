import React from 'react';
import Text from '../components/Text';
import EnvContext from '../components/EnvContext';

class MenuBarMain extends React.Component {

    constructor(props) {
        super(props);
    }

    onClick(event, menu) {
        setTimeout(()=>{
            this.props.onMainMenuClick( menu);
            if (menu.resource && menu.resource.link)
                this.props.history.push("/"+menu.resource.link);
            else
                this.props.history.push("/null");
        }, 50);
    }

    render() {
        return (
			<menu type="toolbar"  className="menubar1">
                {
                    this.props.menus.map( (m, idx)=>{
                        return (<li className={this.props.MainMenuCurrent && this.props.MainMenuCurrent==m.menuid ? "active":null}  key={idx}>
                            <a href="#" onClick={(e)=>{this.onClick(e, m)}}><Text name={"resource."+m.label} /></a>
                        </li>);
                    })
                }
            </menu>
        );
    }
}

class MenuBarSub extends React.Component {

    constructor(props) {
        super(props);
    }

    onClick(e, menu) {
        setTimeout(()=> {
            this.props.onSubMenuClick(menu);
            if (menu.resource.link)
                this.props.history.push("/"+menu.resource.link);
            else
                this.props.history.push("/null");
        }, 50);
        e.preventDefault();
    }

    render() {
        return (
			<menu type="toolbar" className="menubar2">
                {
                    this.props.menus.map((m, idx)=> {
                        return (
                            <li key={idx}>
                                <a href="#" onClick={(e)=>{this.onClick(e, m)}} className={this.props.SubMenuCurrent && this.props.SubMenuCurrent==m.menuid ? "active":null} >&nbsp;<Text name={"resource."+m.label} />&nbsp;</a>
                            </li>
                        );
                    })
                }
            </menu>
        );
    }
}

export default class MenuBar extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            MainMenuCurrent : this.props.menus ? this.props.menus[0].menuid : null,
            SubMenuCurrent :  this.props.menus ? (this.props.menus[0].children.length > 0 ? this.props.menus[0].children[0].menuid: null) : null
        }
        this.state.MainMenuCurrent =  window.sessionStorage.getItem("tmp.menu.main") ? window.sessionStorage.getItem("tmp.menu.main") : this.state.MainMenuCurrent;
        this.state.SubMenuCurrent =  window.sessionStorage.getItem("tmp.menu.sub") ? window.sessionStorage.getItem("tmp.menu.sub") : this.state.SubMenuCurrent;
    }

    onMainMenuClick(menu) {
        this.setState({
            MainMenuCurrent: menu.menuid,
            SubMenuCurrent:  menu.children.length > 0 ? menu.children[0].menuid : []
        });
        window.sessionStorage.setItem("tmp.menu.main", this.state.MainMenuCurrent);
        window.sessionStorage.setItem("tmp.menu.sub",  this.state.SubMenuCurrent);
    }

    onSubMenuClick(menu) {
        this.setState({
            SubMenuCurrent : menu.menuid
        });
        window.sessionStorage.setItem("tmp.menu.sub",  this.state.SubMenuCurrent);
    }

    render() {
        let subMenus = null;
        if (this.props.menus) {
            for (let m of this.props.menus) 
                if (m.menuid == this.state.MainMenuCurrent)
                    subMenus = m.children;
        }

        return this.props.menus ? (
            <div className="menubar">
                <MenuBarMain menus={this.props.menus} MainMenuCurrent={this.state.MainMenuCurrent} onMainMenuClick={(menu)=>{this.onMainMenuClick(menu)}} history={this.props.history}/>
                <MenuBarSub menus={subMenus} SubMenuCurrent={this.state.SubMenuCurrent} onSubMenuClick={(menu)=>{this.onSubMenuClick(menu)}} history={this.props.history} />
            </div>
        ) : null;
    }
}
MenuBar.contextType = EnvContext;
