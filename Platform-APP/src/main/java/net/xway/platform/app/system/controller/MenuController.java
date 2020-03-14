package net.xway.platform.app.system.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.xway.base.controller.JsonResult;
import net.xway.base.database.PageResult;
import net.xway.base.database.Query;
import net.xway.base.utils.PagingUtil;
import net.xway.platform.app.system.service.IMenuService;
import net.xway.platform.system.dto.Menu;
import net.xway.platform.system.dto.Org;
import net.xway.platform.system.dto.Role;
import net.xway.platform.system.dto.User;

@RestController
public class MenuController {

	@Autowired
	private IMenuService menuService;

	@RequestMapping("/ViewMenu")
	public JsonResult<Menu> viewMenu(int menuid) {
		Menu menu = menuService.findMenuByMenuID(menuid);
		return new JsonResult<Menu>(menu);
	}
//
//	@RequestMapping("/ListMyMenus")
//	public JsonResult<List<Menu>> findMyMenus(HttpSession session) {
//		User user = (User) session.getAttribute(User.SESSION_KEY);
//		List<Menu> menus = menuService.findMenuByRoleID(user.getRole().getRoleid());
//		return new JsonResult<List<Menu>>(menus);
//	}
	
	@RequestMapping("/ListAllMenu")
	public JsonResult<List<Menu>> findAllMenu() {
		List<Menu> menus = menuService.findAllMenu();
		return new JsonResult<List<Menu>>(menus);
	}

	@RequestMapping("/ListAllMenuTree")
	public JsonResult<List<Menu>> findMenuTree() {
		List<Menu> menus = menuService.findAllMenuTree();
		return new JsonResult<List<Menu>>(menus);
	}

}
