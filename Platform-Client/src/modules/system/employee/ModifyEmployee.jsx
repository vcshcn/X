import React from 'react';
import { Link } from "react-router-dom";
import View, {Field} from '../../view/ComponentView';
import { DateInput, Select, Pickup } from '../../../components/Forms';
import { Panel, Text } from '../../../components/Controls';
import ajax from '../../../components/Ajax';

export default class ModifyEmployee extends  React.Component {

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

    onSubmit(e) {
        e.preventDefault();

    }

    render() {
        return (
            <View view="Employee">
                <form onSubmit={(e)=>this.onSubmit(e)}>
                    <input type="hidden" value={this.state.employee.employeeid} />
                    <Panel layout="grid" cols="3">
                        <Field name="name">
                            <input type="text" required={true} defaultValue={this.state.employee.name}/>
                        </Field>
                        <Field name="nickname" >
                            <input type="text" required={true} defaultValue={this.state.employee.nickname}/>
                        </Field>
                        <Field name="gender">
                            <Select name="Gender"/>
                        </Field>

                        <Field name="role"  >
                            <Pickup view="Role" required={true}/>
                        </Field>
                        <Field name="org">
                            <Pickup view="Org" required={true} />
                        </Field>
                        <Field name="department" >
                            <Pickup view="Department" required={true} />
                        </Field>
                        
                        <Field name="birthday">
                            <DateInput name="birthday"  value={this.state.employee.birthday}/>
                        </Field>
                        <Field name="tel">
                            <input  defaultValue={this.state.employee.tel}/>
                        </Field>
                        <Field name="email">
                            <input type="email" defaultValue={this.state.employee.enail}/>
                        </Field>

                        <Field name="address">
                            <input type="text"  defaultValue={this.state.employee.address}/>
                        </Field>
                        <Field name="zipcode">
                            <input   defaultValue={this.state.employee.zipcode}/>
                        </Field>
                        <Field name="homepage">
                            <input  type="text" defaultValue={this.state.employee.hoempage}/>
                        </Field>
                    </Panel>
                    <footer>
                        <button type="submit" ><Text name="Submit"/></button>&nbsp;
                        {this.state.employee.employeeid ? <Link to={"/ViewEmployee/"+this.props.match.params.employeeid}><Text name="View" /></Link> : null} &nbsp;
                        <Link to="/ListEmployee/"><Text name="List" /></Link>
                     </footer>
                </form>
            </View>
        );
    }
}

