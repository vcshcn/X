package net.xway.process.def;

import net.xway.process.ICondition;
import net.xway.process.IExecute;

public abstract class AbstractFlow extends AbstractNode implements ICondition {

	private Integer processDefinitionFlowID ;
	private IExecute source;
	private IExecute target;
	
	public Integer getProcessDefinitionFlowID() {
		return processDefinitionFlowID;
	}

	public void setProcessDefinitionFlowID(Integer processDefinitionFlowID) {
		this.processDefinitionFlowID = processDefinitionFlowID;
	}

	public void setSource(IExecute source) {
		this.source = source;
	}

	public void setTarget(IExecute target) {
		this.target = target;
	}

	@Override
	public IExecute getSource() {
		return source;
	}

	@Override
	public IExecute getTarget() {
		return target;
	}

}
