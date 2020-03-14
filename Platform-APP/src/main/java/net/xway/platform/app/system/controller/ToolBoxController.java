package net.xway.platform.app.system.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.xway.base.controller.JsonResult;
import net.xway.base.database.PageResult;
import net.xway.platform.app.system.service.ITimeZoneService;
import net.xway.platform.app.system.service.IToolBoxService;
import net.xway.platform.system.dto.Employee;
import net.xway.platform.system.dto.TimeZone;
import net.xway.platform.system.dto.ToolBox;
import net.xway.platform.system.dto.User;

@RestController
public class ToolBoxController {
	
	@Autowired
	private IToolBoxService toolboxService;
	
	@RequestMapping("/ListToolBox")
	public JsonResult<List<ToolBox>> ListToolBox() {
		List<ToolBox> tools = toolboxService.findAllToolBox();
		return new JsonResult<List<ToolBox>>(tools);
	}
	
	@RequestMapping("/findMySelectedToolBox")
	public JsonResult<List<ToolBox>> findMySelectedToolBox(HttpSession session) {
		User user = (User) session.getAttribute(User.SESSION_KEY);
		List<ToolBox> tools = toolboxService.findSelectedToolBoxByUserID(user.getUserid());
		return new JsonResult<List<ToolBox>>(tools);
	}


}
