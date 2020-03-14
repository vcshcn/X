package net.xway.process.designer.node.event.start;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import net.xway.process.designer.NodeCategory;
import net.xway.process.designer.NodeType;
import net.xway.process.designer.node.ProcessDefinitionPane;
import net.xway.process.designer.node.event.AbstractEventNode;

public abstract class AbstractStartEventNode extends AbstractEventNode {

	public AbstractStartEventNode(ProcessDefinitionPane parent) {
		super(parent);
	}

	@Override
	public boolean judgelinkTO(NodeType type) {
		return type.isStartEvent() == false;
	}

	@Override
	public String getDefaultKeyValue() {
		return "StartEvent" ;
	}

	@Override
	public String getDefaultNameValue() {
		return "Start";
	}

	@Override
	public NodeCategory getCategory() {
		return NodeCategory.StartEvent;
	}
	
	@Override
	public Canvas getIcon() {
    	Canvas canvas = new Canvas(getIconWidth(), getIconHeight());
    	GraphicsContext ctx = canvas.getGraphicsContext2D();
    	ctx.setFill(Color.LIMEGREEN);
    	ctx.fillOval(0,0, getIconWidth(), getIconHeight());
    	return canvas;
	}

}
