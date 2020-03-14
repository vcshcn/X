/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.xway.process.designer.node.event.end;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import net.xway.process.designer.IReloadHandle;
import net.xway.process.designer.NodeType;
import net.xway.process.designer.node.ProcessDefinitionPane;

/**
 *
 * @author cc
 */
public class NoneEndNode extends AbstractEndEventNode implements IReloadHandle {

	public NoneEndNode(ProcessDefinitionPane parent) {
		super(parent);
	}

	@Override
	public NodeType getType() {
		return NodeType.NoneEndEvnet;
	}

	@Override
	public String toXML() {
		return "<endEvent id=\"" + key.get() +  "\"  name=\"" + name.get() + "\" />\r\n";
	}


}
