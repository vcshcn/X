import React from 'react';
import { Link } from "react-router-dom";
import View, {Field} from '../../view/ComponentView';
import { Text, Panel} from '../../../components/Controls';
import ajax from '../../../components/Ajax';

export default class ViewOrg extends  React.Component {

    constructor(props) {
        super(props);
        this.state = {
            org: {}
        }
    }

    componentDidMount() {
        ajax.get("ViewOrg.do?orgid=" + this.props.match.params.orgid )
        .then(response=>{
            if (response.data.error == false) {
                this.setState({org: response.data.object});
            }
        })
        .catch((ex)=>{
            console.error(ex);
        });
    }

    render() {
        return (
            <View view="Org" >
                <Panel layout="grid" cols="3">
                    <Field name="name">{this.state.org.name}</Field>
                    <Field name="description" cols="3">{this.state.org.description}</Field>
                </Panel>
                <footer>
                    <Link to={"/ModifyOrg/"+this.props.match.params.orgid}><Text name="Modify" /></Link>&nbsp;
                    <Link to="/ListOrg/"><Text name="List" /></Link>
                </footer>
            </View>
        );
    }
}
