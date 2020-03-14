import React from 'react';
import EnvContext from '../components/EnvContext';
import {getText} from '../components/Text';
import ToolboxDialog from './system/toolbox/ToolBoxDialog';

export default class ToolBox extends React.Component {

    constructor(props) {
        super(props);

        this.boxRef = React.createRef();
    }

    openToolBox(e, toolboxid, obj) {
        this.boxRef.current.show(e, toolboxid, obj);
    }

    render() {
        let user = this.context.getUser();
        return (
            <React.Fragment>
                <ToolboxDialog ref={this.boxRef} {...this.props}/>
                <div className="toolbox">
                    {
                        user.toolboxs.map(tool=>{
                            return <a key={tool.name} onClick={(e)=>this.openToolBox(e, tool.toolboxid, tool)}><img title={getText("toolbox."+tool.name)} alt={getText("toolbox."+tool.name)} src={require("../images/"+tool.icon)} className="ToolBox-Tool" /></a>
                        })
                    }
                </div>
                <hr />
            </React.Fragment>
        );    
    }
}

ToolBox.contextType = EnvContext;
