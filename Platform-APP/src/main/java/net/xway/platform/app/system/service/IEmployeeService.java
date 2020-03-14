package net.xway.platform.app.system.service;

import net.xway.base.database.PageResult;
import net.xway.base.database.Query;
import net.xway.platform.system.dto.Employee;

public interface IEmployeeService {

	public PageResult<Employee> queryEmployeePage(Query query);
	
	public Employee findEmployeeByEmployeeID(int employeeid);
	
}
