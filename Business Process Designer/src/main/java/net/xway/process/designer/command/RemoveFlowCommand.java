package net.xway.process.designer.command;

import net.xway.process.designer.node.flow.AbstractFlowNode;

public class RemoveFlowCommand implements ICommand {

	private final AbstractFlowNode flow;
	
	public RemoveFlowCommand(AbstractFlowNode flow) {
		this.flow = flow;
	}
	
	@Override
	public void execute() {
		flow.getDefinitionPane().removeFlow(flow);
	}

}
