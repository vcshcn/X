package net.xway.platform.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.xway.base.database.PageResult;
import net.xway.base.database.Query;
import net.xway.platform.system.dao.IMenuDAO;
import net.xway.platform.system.dto.Menu;
import net.xway.platform.system.service.IMenuService;

@Service
public class MenuServiceImpl implements IMenuService {

	@Autowired
	private IMenuDAO menuDAO;
	
	@Override
	public Menu findMenuByMenuID(int menuid) {
		return menuDAO.findByPrimaryKey(menuid);
	}

	@Override
	public List<Menu> findAllMenu() {
		return menuDAO.findAll();
	}
	
	@Override
	public List<Menu> findAllMenuTree() {
		return menuDAO.findAllMenuTree();
	}

	@Override
	public List<Menu> findMenuByRoleID(int roleid) {
		return menuDAO.findMenuByRoleID(roleid);
	}

	
//
//	@Override
//	public List<Menu> findMainMenus() {
//		return menuDAO.findMainMenus();
//	}

//	@Override
//	public void saveMenu(Menu menu) {
//		if (menu.getMenuid() == null) {
//			menuDAO.insert(menu);
//		}
//		else {
//			menuDAO.update(menu);
//		}
//	}
//
//	@Override
//	public void deleteMenu(int menuid) {
//		menuDAO.delete(menuid);
//	}


	//***********************************
	//
	// rolemenu
	//
	//***********************************
	
	
//	@Override
//	public List<Menu> findChildrenMenusByRole(int menuid, int roleid) {
//		return menuDAO.findChildrenMenusByRole(menuid, roleid);
//	}
//
//	@Override
//	public int insertRoleMenu(int roleid, int[] menuid) {
//		return menuDAO.insertRoleMenu(roleid, menuid);
//	}


}
