package net.xway.process.def;

import java.util.Date;

import net.xway.process.IProcessInstance;
import net.xway.process.ProcessInstanceContext;
import net.xway.process.ProcessInstanceStatus;
import net.xway.process.impl.vm.DefaultProcessVM;

public class ProcessInstance implements IProcessInstance {

	private DefaultProcessVM vm;
	private ProcessDefinition definition;
	private Integer processInstanceID;
	private ProcessInstanceStatus status;
	private Date createtime;

	public ProcessInstance(DefaultProcessVM vm, ProcessDefinition definition) {
		this.vm = vm;
		this.definition = definition;
		status = ProcessInstanceStatus.Active;
		createtime = new Date();
	}
		
	public Integer getProcessInstanceID() {
		return processInstanceID;
	}

	public void setProcessInstanceID(Integer processInstanceID) {
		this.processInstanceID = processInstanceID;
	}

	@Override
	public ProcessDefinition getProcessDefinition() {
		return definition;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public ProcessInstanceStatus getStatus() {
		return status;
	}

	public void setStatus(ProcessInstanceStatus status) {
		this.status = status;
	}

	@Override
	public void start() {
		vm.execute(definition.getStartEvent(), this, new ProcessInstanceContext());
	}
	
	
}
