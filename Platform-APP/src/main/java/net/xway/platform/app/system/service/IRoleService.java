package net.xway.platform.app.system.service;

import net.xway.base.database.PageResult;
import net.xway.base.database.Query;
import net.xway.platform.system.dto.Employee;
import net.xway.platform.system.dto.Role;

public interface IRoleService {

	public PageResult<Role> queryRolePage(Query query);

	public Role findRoleByRoleID(int roleid);

}
