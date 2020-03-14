package net.xway.process.designer.command;

import net.xway.process.designer.node.AbstractNode;

public class RemoveNodeCommand implements ICommand {

	private final AbstractNode node;
	
	public RemoveNodeCommand(AbstractNode node) {
		this.node = node;
	}
	
	@Override
	public void execute() {
		node.getDefinitionPane().removeNode(node);
	}

}
