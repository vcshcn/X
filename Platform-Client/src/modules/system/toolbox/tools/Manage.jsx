import React from 'react';
import Table from '../../../../components/Table';

export default class Manage extends React.Component {

    constructor(props) {
        super(props);
    }

    save(e) {
        alert(1);
    }

    render() {
        let SaveButton = {label:"Save", onClick:(event)=>{this.save(event)},  privilege:"SaveToolBox"}
        return <Table view="ToolBox" addButton={false} checkButton={true}  viewButton={false} toolBarButtons={[SaveButton]}/>
    }
}

