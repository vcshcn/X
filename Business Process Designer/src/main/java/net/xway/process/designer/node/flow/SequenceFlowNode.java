/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.xway.process.designer.node.flow;

import net.xway.process.designer.NodeType;
import net.xway.process.designer.node.AbstractNode;
import net.xway.process.designer.node.ProcessDefinitionPane;

/**
 *
 * @author cc
 */
public class SequenceFlowNode  extends AbstractFlowNode  {

	public SequenceFlowNode(ProcessDefinitionPane parent, AbstractNode source, AbstractNode target) {
		super(parent, source, target);
	}

	@Override
	public NodeType getType() {
		return NodeType.SequenceFlow;
	}

	@Override
	public String toXML() {
		StringBuilder sb = new StringBuilder();
		sb.append("<sequenceFlow id=\"" + key.get() +  "\"  name=\"" + name.get() + "\" sourceRef=\"" + source.getKeyProperty().get() + "\" targetRef=\"" + target.getKeyProperty().get() + "\">\r\n");
		if (conditionExpression.get().length() > 0) {
			sb.append("\t\t\t<conditionExpression> ");
			sb.append(conditionExpression.get());
			sb.append(" </conditionExpression>\r\n");
		}
		sb.append("\t\t</sequenceFlow>\r\n");
		return sb.toString();
	}

}
