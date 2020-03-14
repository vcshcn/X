package net.xway.process.impl.repository.dao.impl;

import org.springframework.stereotype.Repository;

import net.xway.base.database.mybatis.dao.BatisDAO;
import net.xway.process.def.AbstractFlow;
import net.xway.process.impl.repository.dao.IProcessDefinitionFlowDAO;

@Repository
public class ProcessDefinitionFlowDAOImpl extends BatisDAO<AbstractFlow> implements IProcessDefinitionFlowDAO {

}
