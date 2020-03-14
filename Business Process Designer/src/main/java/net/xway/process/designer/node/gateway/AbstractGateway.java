package net.xway.process.designer.node.gateway;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import net.xway.process.designer.NodeCategory;
import net.xway.process.designer.NodeType;
import net.xway.process.designer.node.AbstractNode;
import net.xway.process.designer.node.AbstractNodeContextMenu;
import net.xway.process.designer.node.AbstractNodeContextPane;
import net.xway.process.designer.node.ProcessDefinitionPane;

public abstract class AbstractGateway extends AbstractNode {

	private GatewayNodePane nodePane = new GatewayNodePane();
	private GatewayNodeMenu nodeMenu = new GatewayNodeMenu();

	public AbstractGateway(ProcessDefinitionPane parent) {
        super(parent);
	}
	
	@Override
	public boolean judgelinkTO(NodeType type) {
		return type.isStartEvent() == false;
	}

	@Override
	public AbstractNodeContextMenu getNodeContextMenu() {
		return nodeMenu;
	}

	@Override
	public AbstractNodeContextPane getNodeContextPane() {
		return nodePane;
	}

	@Override
	public NodeCategory getCategory() {
		return NodeCategory.Gateway;
	}

	@Override
	public Canvas getIcon() {
    	Canvas canvas = new Canvas(getIconWidth(), getIconHeight());	
    	GraphicsContext ctx = canvas.getGraphicsContext2D();
    	ctx.beginPath();
    	ctx.setFill(Color.WHITE);
    	ctx.moveTo(0, getIconHeight()/2);
    	ctx.lineTo(getIconWidth()/2, 0);
    	ctx.lineTo(getIconWidth(), getIconHeight()/2);
    	ctx.lineTo(getIconWidth()/2, getIconHeight());
    	ctx.lineTo(0, getIconHeight()/2);
    	ctx.closePath();
    	ctx.fill();
    	ctx.stroke();
    	return canvas;
	}
	
	@Override
	public String getDefaultKeyValue() {
		return "Gateway" ;
	}

	@Override
	public String getDefaultNameValue() {
		return "Gateway" ;
	}

	class GatewayNodePane extends AbstractNodeContextPane {

		public GatewayNodePane() {
			super(AbstractGateway.this);
		}
		
	}

	class GatewayNodeMenu extends AbstractNodeContextMenu {

		public GatewayNodeMenu() {
			super(AbstractGateway.this);
		}
		
	}


}
