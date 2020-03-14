package net.xway.platform.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.xway.base.database.PageResult;
import net.xway.base.database.Query;
import net.xway.platform.system.dao.IDepartmentDAO;
import net.xway.platform.system.dto.Department;
import net.xway.platform.system.service.IDepartmentService;

@Service
public class DepartmentServiceImpl implements IDepartmentService {

	@Autowired
	private IDepartmentDAO departmentDAO;
	
	@Override
	public PageResult<Department> queryDepartmentPage(Query query) {
		return departmentDAO.findPage(query);
	}

	@Override
	public Department findDepartmentByDepartmentID(int departmentid) {
		return departmentDAO.findByPrimaryKey(departmentid);
	}

}
