/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.xway.process.designer.node.event.start;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import net.xway.process.designer.NodeType;
import net.xway.process.designer.node.ProcessDefinitionPane;

public class NoneStartNode extends AbstractStartEventNode {
	
   public NoneStartNode(ProcessDefinitionPane parent) {
    	super(parent);
    }
    
	@Override
	public NodeType getType() {
		return NodeType.NoneStartEvent;
	}

	@Override
	public String toXML() {
		return "<startEvent id=\"" + key.get() +  "\"  name=\"" + name.get() + "\" />\r\n";
	}

}

