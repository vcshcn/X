package net.xway.process.designer.command;

import net.xway.process.designer.node.AbstractNode;
import net.xway.process.designer.node.ProcessDefinitionPane;
import net.xway.process.designer.node.flow.SequenceFlowNode;

public class CreateFlowCommand implements ICommand {

	private final ProcessDefinitionPane pane;
	private final AbstractNode start;
	private final AbstractNode end;
	
	public CreateFlowCommand(ProcessDefinitionPane pane, AbstractNode start, AbstractNode end) {
		this.pane = pane;
		this.start = start;
		this.end = end;
	}
	
	@Override
	public void execute() {
        SequenceFlowNode line = new SequenceFlowNode(pane, start, end);
        pane.addFlow(line);
	}

}
