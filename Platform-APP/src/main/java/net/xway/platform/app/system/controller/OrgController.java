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
import net.xway.platform.app.system.service.IOrgService;
import net.xway.platform.system.dto.Org;

@RestController
public class OrgController {

	@Autowired
	private IOrgService orgService;
	
	@PostMapping("/ListOrg")
	public JsonResult<PageResult<Org>> queryOrgPage(@RequestBody(required = false) Query query) {
		if (query == null) {
			query = PagingUtil.create();
		}
		JsonResult<PageResult<Org>> result = new JsonResult<>();
		PageResult<Org> page = orgService.queryOrgPage(query);
		result.setObject(page);
		return result;
	}

	@RequestMapping("/ViewOrg")
	public JsonResult<Org> viewOrg(int orgid) {
		Org org = orgService.findOrgByOrgID(orgid);
		return new JsonResult<Org>(org);
	}

}
