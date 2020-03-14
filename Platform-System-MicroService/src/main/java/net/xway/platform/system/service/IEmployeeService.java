package net.xway.platform.system.service;

import net.xway.base.database.PageResult;
import net.xway.base.database.Query;
import net.xway.platform.system.dto.Employee;

public interface IEmployeeService {

	public Employee findEmployeeByUserID(int loginid);

	public PageResult<Employee> queryEmployeePage(Query query);
}
