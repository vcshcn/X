package net.xway.process;

public interface ICondition extends INode {

	public IExecute getSource();
	
	public IExecute getTarget();
	
	public boolean pass(IProcessVM vm, IProcessInstance instance, ProcessInstanceContext context);
}
