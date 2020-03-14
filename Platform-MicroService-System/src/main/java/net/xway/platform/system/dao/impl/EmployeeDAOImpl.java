package net.xway.platform.system.dao.impl;

import org.springframework.stereotype.Repository;

import net.xway.base.database.mybatis.dao.BatisDAO;
import net.xway.platform.system.dao.IEmployeeDAO;
import net.xway.platform.system.dto.Employee;

@Repository
public class EmployeeDAOImpl extends BatisDAO<Employee> implements IEmployeeDAO {

	@Override
	public Employee findEmployeeByUserID(int userid) {
		return uniqueResult("findEmployeeByUserID", userid);
	}
}
