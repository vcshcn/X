package net.xway.platform.app.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.xway.base.controller.JsonResult;
import net.xway.base.database.PageResult;
import net.xway.base.database.Query;
import net.xway.base.utils.PagingUtil;
import net.xway.platform.app.system.service.IRoleService;
import net.xway.platform.system.dto.Role;

@RestController
public class RoleController {

	@Autowired
	private IRoleService roleService;

	@PostMapping("/ListRole")
	public JsonResult<PageResult<Role>> queryRolePage(@RequestBody(required = false) Query query) {
		if (query == null) {
			query = PagingUtil.create();
		}
		JsonResult<PageResult<Role>> result = new JsonResult<>();
		PageResult<Role> page = roleService.queryRolePage(query);
		result.setObject(page);
		return result;
	}

	@RequestMapping("/ViewRole")
	public JsonResult<Role> viewRole(int roleid) {
		Role role = roleService.findRoleByRoleID(roleid);
		return new JsonResult<Role>(role);
	}

}
