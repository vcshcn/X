package net.xway.process.def;

import java.util.Date;

import net.xway.platform.system.dto.User;
import net.xway.process.IProcessInstance;
import net.xway.process.IProcessVM;
import net.xway.process.ITask;
import net.xway.process.ProcessInstanceContext;
import net.xway.process.TaskStatus;

public class Task extends UserTask implements ITask {

	private Integer taskid;
	private ProcessInstance instance;
	private Date createtime;
	private User claim;
	
	public Integer getTaskid() {
		return taskid;
	}
	public void setTaskid(Integer taskid) {
		this.taskid = taskid;
	}
	public ProcessInstance getInstance() {
		return instance;
	}
	public void setInstance(ProcessInstance instance) {
		this.instance = instance;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public User getClaim() {
		return claim;
	}
	public void setClaim(User claim) {
		this.claim = claim;
	}
	
	@Override
	public TaskStatus execute(IProcessVM vm, IProcessInstance instance, ProcessInstanceContext context) {
		return TaskStatus.Finish;
	}
	
}
