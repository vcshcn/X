package net.xway.process.designer.node.activity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import net.xway.process.designer.NodeType;
import net.xway.process.designer.node.ProcessDefinitionPane;

public class UserTaskNode extends AbstractTask  {

	public UserTaskNode(ProcessDefinitionPane parent) {
		super(parent);
	}


	@Override
	public NodeType getType() {
		return NodeType.UserTask;
	}

	@Override
	public String toXML() {
		return "<userTask id=\"" + key.get() +  "\"  name=\"" + name.get() + "\" />\r\n";
	}

}
