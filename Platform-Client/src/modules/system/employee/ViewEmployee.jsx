import React from 'react';
import { Link } from "react-router-dom";
import View, {Field} from '../../view/ComponentView';
import { Text, Panel} from '../../../components/Controls';
import { DateValue } from '../../../components/Forms';
import ajax from '../../../components/Ajax';

export default class ViewEmployee extends  React.Component {

    constructor(props) {
        super(props);
        this.state = {
            employee : {}
        };
    }

    componentDidMount() {
        ajax.get("ViewEmployee.do?employeeid=" + this.props.match.params.employeeid )
        .then(response=>{
            if (response.data.error == false) {
                this.setState({employee: response.data.object});
            }
        })
        .catch((ex)=>{
            console.error(ex);
        });
    }

    render() {
        return (
            <View view="Employee" >
                <Panel layout="grid" cols="3">
                    <Field name="name">{this.state.employee.name}</Field>
                    <Field name="nickname"> {this.state.employee.nickname} </Field>
                    <Field name="gender">{this.state.employee.gender}</Field>

                    <Field name="role" >{this.state.employee.role ? this.state.employee.role.name : null}</Field>
                    <Field name="department" cols="3">{this.state.employee.department ? this.state.employee.department.name: null}</Field>
                    
                    <Field name="birthday"><DateValue value={this.state.employee.birthday} /></Field>
                    <Field name="tel">{this.state.employee.tel}</Field>
                    <Field name="email">{this.state.employee.email}</Field>

                    <Field name="address">{this.state.employee.address}</Field>
                    <Field name="zipcode">{this.state.employee.zipcode}</Field>
                    <Field name="homepage">{this.state.employee.homepage}</Field>
                </Panel>
                <footer>
                    <Link to={"/ModifyEmployee/"+this.props.match.params.employeeid}><Text name="Modify" /></Link>&nbsp;
                    <Link to="/ListEmployee/"><Text name="List" /></Link>
                </footer>
            </View>
        );
    }
}
