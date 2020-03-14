package net.xway.code.ui;

import java.awt.Point;
import java.awt.Rectangle;

public class ComponentDiagramRelation {

	private final CComponentDiagram parent;
	private final CComponentDiagram child;
	
	public ComponentDiagramRelation(CComponentDiagram parent, CComponentDiagram child) {
		this.parent = parent;
		this.child = child;
	}
	
	public Point getParentCenterLocation() {
		Rectangle r = parent.getBounds();
		return new Point(r.x+r.width / 2, r.y+r.height / 2);
	}
	
	public Point getChildCenterLocation() {
		Rectangle r = child.getBounds();
		return new Point(r.x+r.width / 2, r.y+r.height / 2);
	}

	public boolean contains(CComponentDiagram diagram) {
		return parent.getModelComponent() == diagram.getModelComponent() || child.getModelComponent() == diagram.getModelComponent();
	}
	
	public Rectangle getRectangle() {
		Point parent = getParentCenterLocation();
		Point child = getChildCenterLocation();
		
		int x = Math.min(parent.x, child.x);
		int y = Math.min(parent.y, child.y);
		return new Rectangle(x, y, Math.abs(parent.x-child.x), Math.abs(parent.y - child.y));
	}
}
