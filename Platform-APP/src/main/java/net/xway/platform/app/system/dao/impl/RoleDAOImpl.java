package net.xway.platform.app.system.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import net.xway.base.database.PageResult;
import net.xway.base.database.Query;
import net.xway.platform.app.system.dao.IRoleDAO;
import net.xway.platform.app.system.dao.impl.feign.ISystemMicroFeign;
import net.xway.platform.system.dto.Role;

@Repository
public class RoleDAOImpl implements IRoleDAO {

	@Autowired
	private ISystemMicroFeign systemfeign;

	@Override
	public PageResult<Role> findPage(Query query) {
		ResponseEntity<PageResult<Role>> page = systemfeign.queryRolePage(query);
		return page.getBody();
	}

	@Override
	public Role findByPrimaryKey(int roleid) {
		ResponseEntity<Role> role = systemfeign.findRoleByRoleID(roleid);
		return role.getBody();
	}

}
