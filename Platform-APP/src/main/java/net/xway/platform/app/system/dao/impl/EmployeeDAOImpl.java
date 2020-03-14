package net.xway.platform.app.system.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import net.xway.base.database.PageResult;
import net.xway.base.database.Query;
import net.xway.platform.app.system.dao.IEmployeeDAO;
import net.xway.platform.app.system.dao.impl.feign.ISystemMicroFeign;
import net.xway.platform.system.dto.Employee;

@Repository
public class EmployeeDAOImpl  implements IEmployeeDAO {

	@Autowired
	private ISystemMicroFeign systemfeign;

	@Override
	public PageResult<Employee> findPage(Query query) {
		ResponseEntity<PageResult<Employee>> page = systemfeign.queryEmployeePage(query);
		return page.getBody();
	}

	@Override
	public Employee findByPrimaryKey(int employeeid) {
		ResponseEntity<Employee> page = systemfeign.findEmployeeByUserID(employeeid);
		return page.getBody();
	}

}
