package net.xway.process.def;

import net.xway.process.IProcessInstance;
import net.xway.process.IProcessVM;
import net.xway.process.ProcessInstanceContext;
import net.xway.process.TaskStatus;

public class UserTask extends AbstractTask {
	
	@Override
	public TaskStatus execute(IProcessVM vm, IProcessInstance instance, ProcessInstanceContext context) {
		
		vm.createToDoTask(this, instance);
		
		return TaskStatus.Await;
	}

}
