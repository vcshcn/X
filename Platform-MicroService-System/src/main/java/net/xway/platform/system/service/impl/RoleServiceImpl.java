package net.xway.platform.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.xway.base.database.PageResult;
import net.xway.base.database.Query;
import net.xway.platform.system.dao.IRoleDAO;
import net.xway.platform.system.dto.Role;
import net.xway.platform.system.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private IRoleDAO roleDAO;

	@Override
	public PageResult<Role> queryRolePage(Query query) {
		return roleDAO.findPage(query);
	}

	@Override
	public Role findRoleByRoleID(int roleid) {
		return roleDAO.findByPrimaryKey(roleid);
	}

}
