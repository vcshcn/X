/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.xway.process.designer;

import java.util.ResourceBundle;

import javafx.geometry.Insets;
import javafx.scene.layout.FlowPane;
import net.xway.process.designer.tools.AbstractTool;
import net.xway.process.designer.tools.ExclusiveGatewayTool;
import net.xway.process.designer.tools.NoneEndEventTool;
import net.xway.process.designer.tools.NoneStartEventTool;
import net.xway.process.designer.tools.UserTaskTool;

/**
 *
 * @author cc
 */
public class ToolsPane extends FlowPane implements IReloadHandle {
    
    private NoneStartEventTool startTool = new NoneStartEventTool();
    private NoneEndEventTool endTool = new NoneEndEventTool();
    private UserTaskTool userTaskTool = new UserTaskTool();
    private ExclusiveGatewayTool gatewayTool = new ExclusiveGatewayTool();
    
    private AbstractTool[] tools = new AbstractTool[] {startTool, endTool, userTaskTool, gatewayTool };
        
    public ToolsPane() {
    	super(15, 15);
    	setPadding(new Insets(3, 3, 3,3 ));
        for (final AbstractTool tool : tools) {
            getChildren().add(tool);
        }
        
    }

    @Override
    public void onReloadFinish(ResourceBundle bundle) {
        for (final AbstractTool tool : tools) {
            tool.onReloadFinish(bundle);
        }
    }

  
}
