import React from 'react';
import Dialog from '../../../components/Dialog';
import DynamicComponent  from '../../DynamicComponent';
import Text from '../../../components/Text';

export default class ToolBoxDialog extends Dialog {

    constructor(props) {
        super(props);
        this.state.name = null;
    }

    show(e, toolid, obj) {
        super.show(e);
        this.setState({name: obj.name});
    }

    onClose(e) {
        this.setState({name: null});
    }

    getTitle() {
        return <Text name={"toolbox."+this.state.name} />;
    }

    getContent() {
        return this.state.name ? <DynamicComponent {...this.props}  path={ "./system/toolbox/tools/" + this.state.name }/>  : null
    }
}


ToolBoxDialog.defaultProps = {
    title: 'ToolBox',
    modal: true,
    position: "center"
}
