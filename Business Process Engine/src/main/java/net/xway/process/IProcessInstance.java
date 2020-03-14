package net.xway.process;

public interface IProcessInstance {

	public int getProcessInstanceId();
	
	public IProcessDefinition getProcessDefinition();
	
	public void start();
	
	public void suspend();
	
	public void resume();
}
