package net.xway.process;

import java.util.List;

public interface IExecute extends INode {
	
	public List<ICondition> getFlows();

	public TaskStatus execute(IProcessVM vm, IProcessInstance instance, ProcessInstanceContext context);
	
}
