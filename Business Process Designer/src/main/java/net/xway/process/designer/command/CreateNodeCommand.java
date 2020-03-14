package net.xway.process.designer.command;

import net.xway.process.designer.NodeType;
import net.xway.process.designer.node.AbstractNode;
import net.xway.process.designer.node.ProcessDefinitionPane;
import net.xway.process.designer.node.activity.UserTaskNode;
import net.xway.process.designer.node.event.end.NoneEndNode;
import net.xway.process.designer.node.event.start.NoneStartNode;
import net.xway.process.designer.node.gateway.ExclusiveGatewayNode;
import net.xway.process.designer.tools.AbstractTool;

public class CreateNodeCommand implements ICommand {

	private final AbstractTool tool;
	private final ProcessDefinitionPane pane;
	private final double x, y;
	
	public CreateNodeCommand(ProcessDefinitionPane target, AbstractTool source, double x, double y) {
		this.tool = source;
		this.pane = target;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void execute() {
        AbstractNode node = NodeFactory.create(pane , tool.getType());
        pane.addNode(node, x, y);
	}

}

class NodeFactory {
    
  public static AbstractNode create(ProcessDefinitionPane pane, NodeType type) {
  	AbstractNode node = null;
      switch (type) {
          case NoneStartEvent:  node = new NoneStartNode(pane); break;
          case NoneEndEvnet:    node = new NoneEndNode(pane); break;
          case UserTask:		node = new UserTaskNode(pane); break;
          case ExclusiveGateway:node = new ExclusiveGatewayNode(pane); break;
          default:		throw new java.lang.IllegalArgumentException(type.name());
      }
      return node;
  }

}
