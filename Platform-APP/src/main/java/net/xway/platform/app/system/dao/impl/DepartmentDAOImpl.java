package net.xway.platform.app.system.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import net.xway.base.database.PageResult;
import net.xway.base.database.Query;
import net.xway.platform.app.system.dao.IDepartmentDAO;
import net.xway.platform.app.system.dao.impl.feign.ISystemMicroFeign;
import net.xway.platform.system.dto.Department;
import net.xway.platform.system.dto.Role;

@Repository
public class DepartmentDAOImpl implements IDepartmentDAO {

	@Autowired
	private ISystemMicroFeign systemfeign;

	@Override
	public PageResult<Department> findPage(Query query) {
		ResponseEntity<PageResult<Department>> page = systemfeign.queryDepartmentPage(query);
		return page.getBody();
	}

	@Override
	public Department findByPrimaryKey(int departmentid) {
		ResponseEntity<Department> department = systemfeign.findDepartmentByDepartmentID(departmentid);
		return department.getBody();
	}

}
