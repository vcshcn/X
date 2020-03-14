package net.xway.platform.app.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.xway.base.controller.JsonResult;
import net.xway.platform.app.system.service.IPrivilegeService;
import net.xway.platform.system.dto.Privilege;

@RestController
public class PrivilegeController {

	@Autowired
	private IPrivilegeService privilegeService;

	@RequestMapping("/ListAllPrivilege")
	public JsonResult<List<Privilege>> queryAllPrivilege() {
		List<Privilege> ps = privilegeService.findAllPrivilege();
		return new JsonResult<List<Privilege>>(ps);
	}

}
