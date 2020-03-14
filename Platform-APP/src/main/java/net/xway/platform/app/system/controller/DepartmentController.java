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
import net.xway.platform.app.system.service.IDepartmentService;
import net.xway.platform.system.dto.Department;
import net.xway.platform.system.dto.Org;

@RestController
public class DepartmentController {

	@Autowired
	private IDepartmentService departmentService;

	@PostMapping("/ListDepartment")
	public JsonResult<PageResult<Department>> queryDepartmentPage(@RequestBody(required = false) Query query) {
		if (query == null) {
			query = PagingUtil.create();
		}
		JsonResult<PageResult<Department>> result = new JsonResult<>();
		PageResult<Department> page = departmentService.queryDepartmentPage(query);
		result.setObject(page);
		return result;
	}

	@RequestMapping("/ViewDepartment")
	public JsonResult<Department> viewDepartment(int departmentid) {
		Department department = departmentService.findDepartmentByDepartmentID(departmentid);
		return new JsonResult<Department>(department);
	}

}
