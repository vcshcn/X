import React from 'react';
import { Link } from "react-router-dom";
import View, {Field} from '../../view/ComponentView';
import { Text, Panel} from '../../../components/Controls';
import ajax from '../../../components/Ajax';

export default class ModifyDepartment extends  React.Component {

    constructor(props) {
        super(props);
        this.state = {
            department: {}
        }
    }

    componentDidMount() {
        ajax.get("ViewDepartment.do?departmentid=" + this.props.match.params.departmentid )
        .then(response=>{
            if (response.data.error == false) {
                this.setState({department: response.data.object});
            }
        })
        .catch((ex)=>{
            console.error(ex);
        });
    }

    render() {

        return (
            <View view="Department" >
                <Panel layout="grid" cols="3">
                    <Field name="name">
                        <input type="text" required={true} defaultValue={this.state.department.name}/>
                    </Field>
                    <Field name="description" cols="3">
                        <input type="text" defaultValue={this.state.department.description}/>
                    </Field>
                </Panel>
                <footer>
                    <button type="submit" ><Text name="Submit"/></button>&nbsp;
                    {this.state.department.departmentid ? <Link to={"/ViewDepartment/"+this.props.match.params.departmentid}><Text name="View" /></Link> : null} &nbsp;
                    <Link to="/ListDepartment/"><Text name="List" /></Link>
                </footer>
            </View>
        );
    }
}
