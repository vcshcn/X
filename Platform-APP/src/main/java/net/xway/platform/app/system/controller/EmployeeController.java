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
import net.xway.platform.app.system.service.IEmployeeService;
import net.xway.platform.system.dto.Employee;

@RestController
public class EmployeeController {

	@Autowired
	private IEmployeeService employeeService;
	
	@PostMapping("/ListEmployee")
	public JsonResult<PageResult<Employee>> queryEmployeePage(@RequestBody(required = false) Query query) {
		if (query == null) {
			query = PagingUtil.create();
		}
		JsonResult<PageResult<Employee>> result = new JsonResult<>();
		PageResult<Employee> page = employeeService.queryEmployeePage(query);
		result.setObject(page);
		return result;
	}

	@RequestMapping("/ViewEmployee")
	public JsonResult<Employee> viewEmployee(int employeeid) {
		Employee employee = employeeService.findEmployeeByEmployeeID(employeeid);
		return new JsonResult<Employee>(employee);
	}

}
