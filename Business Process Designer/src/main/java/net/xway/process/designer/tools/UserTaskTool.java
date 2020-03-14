package net.xway.process.designer.tools;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import net.xway.process.designer.NodeType;

public class UserTaskTool extends AbstractTool {

	@Override
	public NodeType getType() {
		return NodeType.UserTask;
	}

	@Override
	public String getTitle() {
		return "Tool.UserTask";
	}

	@Override
	public Canvas getIcon() {
    	Canvas canvas = new Canvas(getIconWidth(), getIconHeight());
    	GraphicsContext ctx = canvas.getGraphicsContext2D();
    	ctx.setFill(Color.LIGHTYELLOW);
    	ctx.setStroke(Color.BLACK);
    	ctx.fillRoundRect(0, 0,canvas.getWidth(), canvas.getHeight(), 30, 30);
    	ctx.strokeRoundRect(0, 0, canvas.getWidth(), canvas.getHeight(), 30, 30);
    	return canvas;
	}

}
