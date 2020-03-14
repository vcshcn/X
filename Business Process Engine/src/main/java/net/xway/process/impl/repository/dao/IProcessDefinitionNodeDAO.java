package net.xway.process.impl.repository.dao;

import net.xway.base.database.IBaseDAO;
import net.xway.process.def.AbstractExecutor;
import net.xway.process.def.AbstractStartEvent;

public interface IProcessDefinitionNodeDAO extends IBaseDAO<AbstractExecutor> {

	public AbstractStartEvent findStartEvent(int processDefinitionId);
	
	public AbstractExecutor findNode(int processDefinitionId, String key);
}
