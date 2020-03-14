package net.xway.process.designer.command;

import net.xway.process.designer.node.AbstractNode;

public class MoveNodeCommand implements ICommand {

	private final AbstractNode node;
	private final double x, y;
	private final double oldx, oldy; 

	public MoveNodeCommand(AbstractNode node, double x, double y) {
		this.node = node;
		this.x = x;
		this.y = y;
		oldx = node.getLayoutX();
		oldy = node.getLayoutY();
	}
	
	@Override
	public void execute() {
		node.getDefinitionPane().moveNode(node, x, y);
	}

}
