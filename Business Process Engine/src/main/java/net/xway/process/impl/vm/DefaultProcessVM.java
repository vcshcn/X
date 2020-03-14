package net.xway.process.impl.vm;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import net.xway.platform.system.dto.User;
import net.xway.process.ICondition;
import net.xway.process.IExecute;
import net.xway.process.IProcessInstance;
import net.xway.process.IProcessVM;
import net.xway.process.ITask;
import net.xway.process.ProcessInstanceContext;
import net.xway.process.TaskStatus;
import net.xway.process.def.ProcessDefinition;
import net.xway.process.def.ProcessInstance;
import net.xway.process.def.Task;
import net.xway.process.def.UserTask;
import net.xway.process.impl.repository.dao.IProcessDefinitionDAO;
import net.xway.process.impl.repository.dao.IProcessDefinitionNodeDAO;
import net.xway.process.impl.repository.dao.IProcessInstanceDAO;
import net.xway.process.impl.repository.dao.ITaskDAO;
import net.xway.process.utils.LogUtils;

public class DefaultProcessVM implements IProcessVM {

	private Log log = LogFactory.getLog(DefaultProcessVM.class);
	
	@Autowired
	private IProcessDefinitionDAO processDefinitionDAO;
	@Autowired
	private IProcessDefinitionNodeDAO processDefinitionNodeDAO;
	@Autowired
	private IProcessInstanceDAO processInstanceDAO;
	@Autowired
	private ITaskDAO taskDAO;

	@Override
	public IProcessInstance create(String processDefinitionKey) {
		ProcessDefinition definition = processDefinitionDAO.findProcessDefinitionByKey(processDefinitionKey);
		return create(definition);
	}

	@Override
	public IProcessInstance create(String processDefinitionKey, int version) {
		ProcessDefinition definition = processDefinitionDAO.findProcessDefinitionByKey(processDefinitionKey, version);
		return create(definition);
	}
	
	public IProcessInstance create(ProcessDefinition definition) {
		ProcessInstance instance = new ProcessInstance(this, definition);
		instance.setCreatetime(new Date());
		processInstanceDAO.insert(instance);
		return instance;
	}

	public void execute(IExecute activity, IProcessInstance instance, ProcessInstanceContext context) {
		if (log.isInfoEnabled()) {
			log.info(LogUtils.formatLog(instance, context, activity));
		}
		TaskStatus status = activity.execute(this, instance, context);
		if (TaskStatus.Finish.equals(status)) {
			for (ICondition condition : activity.getFlows()) {
				if (condition.pass(this, instance, context) ) {
					IExecute e = condition.getTarget();
					execute(e, instance, context);
				}
			}
		}
	}

	@Override
	public ITask createToDoTask(UserTask task, int instanceid) {
		ProcessInstance instance = processInstanceDAO.findByPrimaryKey(instanceid);
		
		Task t = new Task();
		t.setInstance(instance);
		t.setName(task.getName());
		t.setDescription(task.getDescription());
		t.setCreatetime(new Date());
		taskDAO.insert(t);
		return t;
	}

	@Override
	public void claim(int taskid, User user) {
		Task task = taskDAO.findByPrimaryKey(taskid);
		task.setClaim(user);
		
		
	}

	@Override
	public void complete(int taskid) {
		Task task = taskDAO.findByPrimaryKey(taskid);
		execute(task, task.getInstance(), null);
	}

}
