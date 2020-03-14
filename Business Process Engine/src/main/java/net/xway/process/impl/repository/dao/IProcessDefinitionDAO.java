package net.xway.process.impl.repository.dao;

import net.xway.base.database.IBaseDAO;
import net.xway.process.def.ProcessDefinition;

public interface IProcessDefinitionDAO extends IBaseDAO<ProcessDefinition> {

	public ProcessDefinition findProcessDefinitionByKey(String key);
	
	public ProcessDefinition findProcessDefinitionByKey(String key, int version);
}
