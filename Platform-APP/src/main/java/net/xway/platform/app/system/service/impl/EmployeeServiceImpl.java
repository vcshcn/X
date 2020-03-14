package net.xway.platform.app.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.xway.base.database.PageResult;
import net.xway.base.database.Query;
import net.xway.platform.app.system.dao.IEmployeeDAO;
import net.xway.platform.app.system.service.IEmployeeService;
import net.xway.platform.system.dto.Employee;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private IEmployeeDAO employeeDAO;

	@Override
	public PageResult<Employee> queryEmployeePage(Query query) {
		return employeeDAO.findPage(query);
	}

	@Override
	public Employee findEmployeeByEmployeeID(int employeeid) {
		return employeeDAO.findByPrimaryKey(employeeid);
	}


}
