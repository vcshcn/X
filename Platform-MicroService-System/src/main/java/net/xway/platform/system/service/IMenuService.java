package net.xway.platform.system.service;

import java.util.List;

import net.xway.base.database.PageResult;
import net.xway.base.database.Query;
import net.xway.platform.system.dto.Menu;

public interface IMenuService {
	
	public Menu findMenuByMenuID(int menuid);
	
	public List<Menu> findAllMenu();
	
	public List<Menu> findAllMenuTree();
	
	public List<Menu> findMenuByRoleID(int roleid);

//	public void saveMenu(Menu menu);
//	
//	public void deleteMenu(int menuid);
//	
//	public List<Menu> findMainMenus();
	
	// rolemenu
	
	
//	public List<Menu> findChildrenMenusByRole(int menuid, int roleid);
//	
//	public int insertRoleMenu(int roleid, int[] menuid);
}

