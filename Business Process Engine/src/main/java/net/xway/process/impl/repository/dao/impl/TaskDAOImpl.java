package net.xway.process.impl.repository.dao.impl;

import org.springframework.stereotype.Repository;

import net.xway.base.database.mybatis.dao.BatisDAO;
import net.xway.process.def.Task;
import net.xway.process.impl.repository.dao.ITaskDAO;

@Repository
public class TaskDAOImpl extends BatisDAO<Task> implements ITaskDAO {

}
