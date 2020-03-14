package net.xway.process.designer.tools;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import net.xway.process.designer.NodeType;

public class ExclusiveGatewayTool extends AbstractTool {

	@Override
	public NodeType getType() {
		return NodeType.ExclusiveGateway;
	}

	@Override
	public String getTitle() {
		return "Tool.Gateway";
	}

	@Override
	public Canvas getIcon() {
    	Canvas canvas = new Canvas(getIconWidth(), getIconHeight());
    	GraphicsContext ctx = canvas.getGraphicsContext2D();
    	ctx.beginPath();
    	ctx.setFill(Color.WHITE);
    	ctx.moveTo(0, canvas.getHeight()/2);
    	ctx.lineTo(canvas.getWidth()/2, 0);
    	ctx.lineTo(canvas.getWidth(), canvas.getHeight()/2);
    	ctx.lineTo(canvas.getWidth()/2, canvas.getHeight());
    	ctx.lineTo(0, canvas.getHeight()/2);
    	ctx.closePath();
    	ctx.fill();
    	ctx.stroke();
    	return canvas;
	}
 
}
