package net.xway.platform.app.system.service;

import java.util.List;

import net.xway.base.database.PageResult;
import net.xway.base.database.Query;
import net.xway.platform.system.dto.Menu;

public interface IMenuService {
	
	public Menu findMenuByMenuID(int menuid);
	
	public List<Menu> findAllMenu();

	public List<Menu> findMenuByRoleID(int roleid);
	
	public List<Menu> findAllMenuTree();
}

