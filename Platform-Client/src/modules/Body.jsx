import React from 'react';
import { Route, Switch, Redirect } from 'react-router-dom';
import EnvContext from '../components/EnvContext';
import MenuBar from './MenuBar';
import ToolBox from './ToolBox';
import NotFound from './NotFound';
import DynamicComponent from './DynamicComponent';

const ComponentDefines = [
    { id: "Help", link: "/Help"},
    { id: "Home", link: "/task", path: "./home/Home"},
    /* Profile */
    { id: "ViewProfile", link: "/ViewProfile/:active", path: "./system/employee/ViewProfile"},
    { id: "ModifyProfile", link: "/ModifyProfile/:active", path: "./system/employee/ModifyProfile"},
    /* Employee */
    { id: "ListEmployee", link: "/ListEmployee", path: "./system/employee/ListEmployee"},
    { id: "CreateEmployee", link: "/CreateEmployee", path: "./system/employee/ModifyEmployee"},
    { id: "ModifyEmployee", link: "/ModifyEmployee/:employeeid", path: "./system/employee/ModifyEmployee"},
    { id: "ViewEmployee", link: "/ViewEmployee/:employeeid", path: "./system/employee/ViewEmployee"},
    /* Role */
    { id: "ListRole", link: "/ListRole", path: "./system/role/ListRole"},
    { id: "CreateRole", link: "/CreateRole", path: "./system/role/ModifyRole"},
    { id: "ModifyRole", link: "/ModifyRole/:roleid", path: "./system/role/ModifyRole"},
    { id: "ViewRole", link: "/ViewRole/:roleid", path: "./system/role/ViewRole"},
    { id: "ViewRoleMenu", link: "/ViewRoleMenu/:roleid", path: "./system/role/ViewRoleMenu"},
    { id: "ModifyRoleMenu", link: "/ModifyRoleMenu/:roleid", path: "./system/role/ModifyRoleMenu"},
    /* Org */
    { id: "ListOrg", link: "/ListOrg", path: "./system/org/ListOrg"},
    { id: "CreateOrg", link: "/CreateOrg", path: "./system/org/ModifyOrg"},
    { id: "ModifyOrg", link: "/ModifyOrg/:orgid", path: "./system/org/ModifyOrg"},
    { id: "ViewOrg", link: "/ViewOrg/:orgid", path: "./system/org/ViewOrg"},
    /* Department */
    { id: "ListDepartment", link: "/ListDepartment", path: "./system/department/ListDepartment"},
    { id: "CreateDepartment", link: "/CreateDepartment", path: "./system/department/ModifyDepartment"},
    { id: "ModifyDepartment", link: "/ModifyDepartment/:departmentid", path: "./system/department/ModifyDepartment"},
    { id: "ViewDepartment", link: "/ViewDepartment/:departmentid", path: "./system/department/ViewDepartment"},
    /* Menu */
    { id: "ListMenu", link: "/ListAllMenu", path: "./system/menu/ListMenu"},

];



export default class Body extends React.Component {

   constructor(props) {
        super(props);
        this.components = this.registerAll();
    }

    registerAll() {
        let r = [];
        ComponentDefines.map((cd, idx)=> {
            if (cd.id && cd.link && cd.path)
                r.push( <Route exact path={cd.link} key={idx} render={ (props) => { return <DynamicComponent {...props} user={this.props.user} id={cd.id} path={cd.path}/> }} />)
        });
        return r;
    }

    render() {
        return (
            <div>
                <MenuBar {...this.props} menus={this.props.user.role.menus} />
                <Switch>
                    <Redirect exact from="/home" to="/task"/>
                    <Route exact path="/Logout" render={ ()=>{ this.context.logout(); return <Redirect to="/" />; }} />
                    { this.components }
                    <Redirect exact from="/" to="/home" />
                    <Route path="*"><NotFound {...this.props}/></Route>
                </Switch>
                <hr/>
            <ToolBox {...this.props} />
            </div>
    );
    }
}

Body.contextType = EnvContext;
