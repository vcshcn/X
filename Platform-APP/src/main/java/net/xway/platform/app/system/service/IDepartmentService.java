package net.xway.platform.app.system.service;

import net.xway.base.database.PageResult;
import net.xway.base.database.Query;
import net.xway.platform.system.dto.Department;
import net.xway.platform.system.dto.Role;

public interface IDepartmentService {

	public PageResult<Department> queryDepartmentPage(Query query);

	public Department findDepartmentByDepartmentID(int departmentid);

}
