import React from 'react';
import Table from '../../../components/Table';
import Panel from '../../../components/Panel';
import View, {Field} from '../../view/ComponentView';

class SearchRole extends React.Component {

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
            <View view="Role" ptitle="Search">
                <form  onSubmit={(e)=>this.onSubmit(e)}>
                    <Panel layout="flex" direction="column">
                        <Field name="name">
                            <input type="search" autoFocus/>
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


class RoleTable extends  React.Component {

    constructor(props) {
        super(props);
        this.state = {
            params: null
        }
    }

    ViewRoleMenu(e, id, obj) {
        this.props.history.push("/ViewRoleMenu" + "/" + id)
    }

    query(q) {
        this.setState({params, q});
    }

    render() {
        let menuButton = {label:"Menu", style: {backgroundImage: 'url('+require('../../../images/menu.png')+')'}, onClick:(event,id,obj)=>{this.ViewRoleMenu(event,id,obj)},  privilege:"ViewRoleMenu"}
        return <Table view="Role" params={this.state.params} extendOperateButtons={[menuButton]} {...this.props} />;
    }

}


export default class ListRole extends  React.Component {

    render() {
        return <Panel layout="border" left={<SearchRole {...this.props}/>} center={<RoleTable {...this.props} />} />;
    }
}

