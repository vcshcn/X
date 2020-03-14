package net.xway.process.impl.repository;

import org.springframework.beans.factory.annotation.Autowired;

import net.xway.process.IProcessDefinition;
import net.xway.process.IRepository;
import net.xway.process.impl.repository.dao.IProcessDefinitionDAO;

public class Repository implements IRepository {

	@Autowired
	private IProcessDefinitionDAO processDefinitionDAO;
	
	@Override
	public IProcessDefinition loadProcessDefinition(String key) {
		return processDefinitionDAO.findProcessDefinitionByKey(key);
	}

}
