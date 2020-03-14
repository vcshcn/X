import React from 'react';
import { Link } from "react-router-dom";
import Panel from '../../../components/Panel';
import View, {Field} from '../../view/ComponentView';
import { Text} from '../../../components/Controls';
import ajax from '../../../components/Ajax';

export default class ViewRoleMenu extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            role: { menus: [] },
        }
    }

    componentDidMount() {
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
                        <Link to={"/ModifyRoleMenu/"+this.props.match.params.roleid}><Text name="Modify" /></Link>&nbsp;
                        <Link to="/ListRole/"><Text name="List" /></Link>
                    </footer>
                </View>
                <p></p>
                <p></p>
                <table style={styles}>
                    <tr>
                        {
                            this.state.role.menus.map(menu=>{
                                return <th key={menu.menuid} style={th}> <Text name={"component.Privilege." + menu.label } /></th>
                            })
                        }
                    </tr>
                    <tr>
                    {
                            this.state.role.menus.map(menu=>{
                                return (<td key={menu.menuid} style={td}>
                                {
                                    menu.children.map(m=>{
                                        return <div key={m.menuid} style={div}> <Text name={"component.Privilege." + m.label } /></div>
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

