package net.xway.process.def;

import java.util.List;

import net.xway.process.ICondition;
import net.xway.process.IExecute;

public abstract class AbstractExecutor extends AbstractNode implements IExecute {

	private Integer processDefinitionNodeID; 
	private List<ICondition> flows;
	
	public Integer getProcessDefinitionNodeID() {
		return processDefinitionNodeID;
	}

	public void setProcessDefinitionNodeID(Integer processDefinitionNodeID) {
		this.processDefinitionNodeID = processDefinitionNodeID;
	}

	public void setFlows(List<ICondition> flows) {
		this.flows = flows;
	}

	@Override
	public List<ICondition> getFlows() {
		return flows;
	}

}
