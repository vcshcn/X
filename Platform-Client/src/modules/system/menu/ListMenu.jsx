import React from 'react';
import Table from '../../../components/Table';
import Panel from '../../../components/Panel';
import View, {Field} from '../../view/ComponentView';

class SearchMenu extends React.Component {

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
            <View view="Menu" ptitle="Search">
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


class MenuTable extends  React.Component {

    constructor(props) {
        super(props);
        this.state = {
            params: null
        }
    }

    query(q) {
        this.setState({params, q});
    }

    render() {
        return <Table view="Menu" params={this.state.params} {...this.props} url="ListAllMenu.do" />;
    }

}


export default class ListMenu extends  React.Component {

    render() {
        return <Panel layout="border" left={<SearchMenu {...this.props}/>} center={<MenuTable {...this.props} />} />;
    }
}

