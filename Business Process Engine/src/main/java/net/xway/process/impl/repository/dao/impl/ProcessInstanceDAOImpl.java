package net.xway.process.impl.repository.dao.impl;

import org.springframework.stereotype.Repository;

import net.xway.base.database.mybatis.dao.BatisDAO;
import net.xway.process.def.ProcessInstance;
import net.xway.process.impl.repository.dao.IProcessInstanceDAO;

@Repository
public class ProcessInstanceDAOImpl extends BatisDAO<ProcessInstance> implements IProcessInstanceDAO {

	
}
