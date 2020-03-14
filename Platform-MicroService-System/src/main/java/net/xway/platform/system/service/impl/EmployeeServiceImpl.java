package net.xway.platform.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.xway.base.database.PageResult;
import net.xway.base.database.Query;
import net.xway.platform.system.dao.IEmployeeDAO;
import net.xway.platform.system.dao.IToolBoxDAO;
import net.xway.platform.system.dto.Employee;
import net.xway.platform.system.dto.Menu;
import net.xway.platform.system.dto.ToolBox;
import net.xway.platform.system.service.IEmployeeService;
import net.xway.platform.system.service.IMenuService;
import net.xway.platform.system.service.IRoleService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private IEmployeeDAO employeeDAO;
	@Autowired
	private IToolBoxDAO toolboxDAO;
	@Autowired
	private IMenuService menuboxDAO;
	
	@Override
	public Employee findEmployeeByUserID(int userid) {
		Employee e = employeeDAO.findEmployeeByUserID(userid);
		List<ToolBox> toolboxs = toolboxDAO.findSelectedToolBoxByUserID(userid);
		e.setToolboxs(toolboxs);
		return e;
	}

	@Override
	public PageResult<Employee> queryEmployeePage(Query query) {
		return employeeDAO.findPage(query);
	}

}
