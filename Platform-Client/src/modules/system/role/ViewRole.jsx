import React from 'react';
import { Link } from "react-router-dom";
import View, {Field} from '../../view/ComponentView';
import { Text, Panel} from '../../../components/Controls';
import ajax from '../../../components/Ajax';

export default class ViewRole extends  React.Component {

    constructor(props) {
        super(props);
        this.state = {
            role: {}
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
        let ps = [];
        if (this.state.role.privileges) {
            let pps = this.state.role.privileges.sort((a,b)=>a.name>b.name);
            for (let p of pps) 
                ps.push(<label style={{textAlign:'center'}} key={p.name}><Text name={"component.Privilege."+p.name}/></label>);
        }

        return (
            <React.Fragment>
                <View view="Role" >
                    <Panel layout="grid" cols="3">
                        <Field name="name">{this.state.role.name}</Field>
                        <Field name="description" cols="3">{this.state.role.description}</Field>
                    </Panel>
                    <footer>
                        <Link to={"/ModifyRole/"+this.props.match.params.roleid}><Text name="Modify" /></Link>&nbsp;
                        <Link to="/ListRole/"><Text name="List" /></Link>
                    </footer>
                </View>
                <p></p>
                <div style={{textAlign:"center",backgroundColor: "#bad7e6", fontWeight:"bold"}}><Text name="List" /> <Text name="component.Privilege" /> </div>
                <p></p>
                <div className="grid-layout" style={{gridTemplateColumns: "repeat(10, 10%)"}}>
                { ps }
                </div>
            </React.Fragment>
        );
    }
}
