import React from 'react';
import { Link } from "react-router-dom";
import Panel from '../../../components/Panel';
import View, {Field} from '../../view/ComponentView';
import { Text} from '../../../components/Controls';
import ajax from '../../../components/Ajax';

export default class ModifyRoleMenu extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            role: { menus: [] },
            menus: []
        }
    }

    componentDidMount() {
        ajax.get("ListAllMenuTree.do" )
        .then(response=>{
            if (response.data.error == false) {
                this.setState({menus: response.data.object});
            }
        })
        .catch((ex)=>{
            console.error(ex);
        });

        ajax.get("ViewRole.do?roleid=" + this.props.match.params.roleid )
        .then(response=>{
            if (response.data.error == false) {
                this.setState({role: response.data.object});
            }
        })
        .catch((ex)=>{
            console.error(ex);
        });
    }

    render() {
        let styles = {
            border: "1px solid",
            margin: "0 auto"
        };

        let th = {
            background: "var(--MenuLevel1_Item_Background)",
            width: "10%"
        }

        let td = {
            verticalAlign: "top",
            background: "#bad7e6"
        }

        let div = {
            padding: "3px",
            cursor : "default",
            width: "100%"

        }

        return (
            <React.Fragment>
                <View view="Role" >
                    <Panel layout="grid" cols="3">
                        <Field name="name">{this.state.role.name}</Field>
                        <Field name="description" cols="3">{this.state.role.description}</Field>
                    </Panel>
                    <footer>
                        <Link to={"/ViewRoleMenu/"+this.props.match.params.roleid}><Text name="View" /></Link>&nbsp;
                        <Link to="/ListRole/"><Text name="List" /></Link>
                    </footer>
                </View>
                <p></p>
                <p></p>
                <table style={styles}>
                    <tr>
                        {
                            this.state.menus.map(menu=>{
                                let bool = this.state.role.menus.find(m=>m.menuid==menu.menuid) != null;
                                return (
                                    <th key={menu.menuid} style={th}> <input type="checkbox" checked={bool} value={menu.menuid}/><Text name={"component.Privilege." + menu.label } /></th>
                                )
                            })
                        }
                    </tr>
                    <tr>
                    {
                            this.state.menus.map(menu=>{
                                let mymain = this.state.role.menus.find(m=>m.menuid==menu.menuid);

                                return (<td key={menu.menuid} style={td}>
                                {
                                    menu.children.map(m=>{
                                        let bool = mymain && mymain.children.find(cm=>cm.menuid==m.menuid) != null;
                                        return <div key={m.menuid} style={div}> <input type="checkbox" checked={bool} value={m.menuid}/><Text name={"component.Privilege." + m.label } /></div>
                                    })
                                }
                                </td>)
                            })
                        }
                   </tr>
                </table>
            </React.Fragment>
        )
    }
}

