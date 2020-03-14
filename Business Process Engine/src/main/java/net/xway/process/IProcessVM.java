package net.xway.process;

import net.xway.platform.system.dto.User;
import net.xway.process.def.UserTask;

public interface IProcessVM {
	
	public IProcessInstance create(String processDefinitionKey);
	
	public IProcessInstance create(String processDefinitionKey, int version);
	
	public ITask createToDoTask(UserTask task, int instanceid);
	
	public void claim(int taskid, User user);
	
	public void complete(int taskid);
}
