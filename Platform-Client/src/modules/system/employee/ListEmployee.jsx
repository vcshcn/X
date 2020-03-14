import React from 'react';
import Table from '../../../components/Table';
import Panel from '../../../components/Panel';
import { DateInput, Pickup } from '../../../components/Forms';
import View, {Field} from '../../view/ComponentView';

class SearchEmployee extends React.Component {

    constructor(props) {
        super(props);
    }

    onSubmit(e) {
        e.preventDefault();
        let q = {};
        this.props.center.current.query(q);
    }

    render() {
        return (
            <View view="Employee" ptitle="Search">
                <form  onSubmit={(e)=>this.onSubmit(e)}>
                    <Panel layout="flex" direction="column">
                        <Field name="name">
                            <input type="search" autoFocus/>
                        </Field>
                        <Field name="nickname">
                            <input type="search"/>
                        </Field>
                        <Field name="role">
                            <Pickup view="Role" />
                        </Field>
                        <Field name="department">
                            <Pickup view="Department" />
                        </Field>
                        <Field name="birthday">
                            <DateInput name="birthday1" style={{width:"42%"}} /> - <DateInput name="birthday2" style={{width:"42%"}} />
                        </Field>
                    </Panel>
                    <footer>
                        <input type="reset" />&nbsp;
                        <input type="submit" />
                    </footer>
                </form>
            </View>
        );
    }
}


class EmployeeTable extends  React.Component {

    constructor(props) {
        super(props);
        this.state = {
            params: null
        }

    }

    viewUserAccount() {
        alert(1);
    }

    query(q) {
        this.setState({params, q});
    }

    render() {
        let AccountButton = {label:"Account", style: {backgroundImage: 'url('+require('../../../images/person.png')+')'}, onClick:(event)=>{this.viewUserAccount()},  privilege:"ViewUserAccount"}
        return <Table view="Employee" params={this.state.params} extendOperateButtons={[AccountButton]} {...this.props} />;
    }

}


export default class ListEmployee extends  React.Component {

    render() {
        return <Panel layout="border" left={<SearchEmployee {...this.props}/>} center={<EmployeeTable {...this.props} />} />;
    }
}

