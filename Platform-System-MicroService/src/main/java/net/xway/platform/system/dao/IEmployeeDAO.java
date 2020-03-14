package net.xway.platform.system.dao;

import net.xway.base.database.IBaseDAO;
import net.xway.platform.system.dto.Employee;

public interface IEmployeeDAO extends IBaseDAO<Employee> {
	
	public Employee findEmployeeByUserID(int loginid);

}
