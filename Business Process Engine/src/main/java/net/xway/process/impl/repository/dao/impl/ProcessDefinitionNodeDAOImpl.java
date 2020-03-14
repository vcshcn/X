package net.xway.process.impl.repository.dao.impl;

import org.springframework.stereotype.Repository;

import net.xway.base.database.mybatis.dao.BatisDAO;
import net.xway.process.def.AbstractExecutor;
import net.xway.process.def.AbstractStartEvent;
import net.xway.process.impl.repository.dao.IProcessDefinitionNodeDAO;

@Repository
public class ProcessDefinitionNodeDAOImpl extends BatisDAO<AbstractExecutor> implements IProcessDefinitionNodeDAO {

	@Override
	public AbstractStartEvent findStartEvent(int processDefinitionId) {
		return (AbstractStartEvent)uniqueResult("findStartEvent", processDefinitionId);
	}

	@Override
	public AbstractExecutor findNode(int processDefinitionId, String key) {
		return (AbstractExecutor)uniqueResult("findNode", Parameters().set("processDefinitionId", processDefinitionId).set("key", key));
	}

	
}
