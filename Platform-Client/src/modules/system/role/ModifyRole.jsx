import React from 'react';
import { Link } from "react-router-dom";
import View, {Field} from '../../view/ComponentView';
import { Text, Panel} from '../../../components/Controls';
import ajax from '../../../components/Ajax';

export default class ModifyRole extends  React.Component {

    constructor(props) {
        super(props);
        this.state = {
            role: {},
            privileges: []
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

        ajax.get("ListAllPrivilege.do" )
        .then(response=>{
            if (response.data.error == false) {
                this.setState({privileges: response.data.object});
            }
        })
        .catch((ex)=>{
            console.error(ex);
        });
    }

    render() {
        let ps = [];
        if (this.state.role.privileges && this.state.privileges) {
            for (let p of this.state.privileges) {

                let checked =  this.state.role.privileges.find(p1=>{ return p1.privilegeid == p.privilegeid});
                ps.push(<label style={{textAlign:'center'}} key={p.name} ><input type="checkbox" checked={checked} value={p.name} /><Text name={"component.Privilege."+p.name}/></label>);
            }
        }
        return (
            <React.Fragment>
                <View view="Role" >
                    <Panel layout="grid" cols="3">
                        <Field name="name">
                            <input type="text" required={true} defaultValue={this.state.role.name}/>
                        </Field>
                        <Field name="description" cols="3">
                            <input type="text" defaultValue={this.state.role.description}/>
                        </Field>
                    </Panel>
                    <footer>
                        <button type="submit" ><Text name="Submit"/></button>&nbsp;
                        {this.state.role.roleid ? <Link to={"/ViewRole/"+this.props.match.params.roleid}><Text name="View" /></Link> : null} &nbsp;
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
