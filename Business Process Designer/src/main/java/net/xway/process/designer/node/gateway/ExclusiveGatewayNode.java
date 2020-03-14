package net.xway.process.designer.node.gateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import net.xway.process.designer.NodeType;
import net.xway.process.designer.node.ProcessDefinitionPane;

public class ExclusiveGatewayNode extends AbstractGateway  {

	public ExclusiveGatewayNode(ProcessDefinitionPane parent) {
		super(parent);
	}

	@Override
	public NodeType getType() {
		return NodeType.ExclusiveGateway;
	}

	@Override
	public String toXML() {
		return "<exclusiveGateway id=\"" + key.get() +  "\"  name=\"" + name.get() + "\" />\r\n";
	}

}
