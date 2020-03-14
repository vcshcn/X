package net.xway.process.def;

import net.xway.process.IProcessInstance;
import net.xway.process.IProcessVM;
import net.xway.process.ProcessInstanceContext;
import net.xway.process.TaskStatus;

public class NoneStartEvent extends AbstractStartEvent {
	
	@Override
	public TaskStatus execute(IProcessVM vm, IProcessInstance instance, ProcessInstanceContext context) {
		return TaskStatus.Finish;
	}

}
