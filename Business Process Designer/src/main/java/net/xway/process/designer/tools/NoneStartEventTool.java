/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.xway.process.designer.tools;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import net.xway.process.designer.NodeType;

/**
 *
 * @author cc
 */
public class NoneStartEventTool extends AbstractTool {
    
	@Override
	public NodeType getType() {
		return NodeType.NoneStartEvent;
	}

	@Override
	public String getTitle() {
		return "Tool.Start";
	}

	@Override
	public Canvas getIcon() {
    	Canvas canvas = new Canvas(getIconWidth(), getIconHeight());
    	GraphicsContext ctx = canvas.getGraphicsContext2D();
    	ctx.setFill(Color.LIMEGREEN);
    	ctx.fillOval(0,0, canvas.getWidth(), canvas.getHeight());
    	return canvas;
	}
    
}
