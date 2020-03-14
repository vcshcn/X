package net.xway.process.impl.repository.dao.impl;

import org.springframework.stereotype.Repository;

import net.xway.base.database.mybatis.dao.BatisDAO;
import net.xway.process.def.ProcessDefinition;
import net.xway.process.impl.repository.dao.IProcessDefinitionDAO;

@Repository
public class ProcessDefinitionDAOImpl extends BatisDAO<ProcessDefinition> implements IProcessDefinitionDAO {

	@Override
	public ProcessDefinition findProcessDefinitionByKey(String key) {
		return uniqueResult("findProcessDefinitionByKey", key);
	}

	@Override
	public ProcessDefinition findProcessDefinitionByKey(String key, int version) {
		return uniqueResult("findProcessDefinitionByKeyAndVersion", Parameters().set("key", key).set("version", version));
	}

	
}
