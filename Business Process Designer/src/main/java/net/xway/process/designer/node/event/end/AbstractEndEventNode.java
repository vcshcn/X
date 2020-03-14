package net.xway.process.designer.node.event.end;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import net.xway.process.designer.NodeCategory;
import net.xway.process.designer.NodeType;
import net.xway.process.designer.node.ProcessDefinitionPane;
import net.xway.process.designer.node.event.AbstractEventNode;

public abstract class AbstractEndEventNode extends AbstractEventNode {

	public AbstractEndEventNode(ProcessDefinitionPane parent) {
		super(parent);
	}

	@Override
	public String getDefaultKeyValue() {
		return "EndEvent" ;
	}

	@Override
	public String getDefaultNameValue() {
		return "End" ;
	}

	@Override
	public NodeCategory getCategory() {
		return NodeCategory.EndEvent;
	}

	@Override
	public Canvas getIcon() {
    	Canvas canvas = new Canvas(getIconWidth(), getIconHeight());	
    	GraphicsContext ctx = canvas.getGraphicsContext2D();
    	ctx.setFill(Color.RED);
    	ctx.fillOval(0,0, getIconWidth(), getIconHeight());
    	return canvas;
	}


	@Override
	public boolean judgelinkTO(NodeType type) {
		return false;
	}

}
