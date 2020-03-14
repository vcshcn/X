import React from 'react';
import Table from '../../../components/Table';
import Panel from '../../../components/Panel';
import View, {Field} from '../../view/ComponentView';

class SearchOrg extends React.Component {

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
            <View view="Org" ptitle="Search">
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


class OrgTable extends  React.Component {

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
        return <Table view="Org" params={this.state.params} {...this.props} />;
    }

}


export default class ListOrg extends  React.Component {

    render() {
        return <Panel layout="border" left={<SearchOrg {...this.props}/>} center={<OrgTable {...this.props} />} />;
    }
}

