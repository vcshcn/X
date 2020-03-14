package net.xway.platform.app.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.xway.base.database.PageResult;
import net.xway.base.database.Query;
import net.xway.platform.app.system.dao.IMenuDAO;
import net.xway.platform.app.system.service.IMenuService;
import net.xway.platform.system.dto.Menu;

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
	public List<Menu> findMenuByRoleID(int roleid) {
		return menuDAO.findMenuByRoleID(roleid);
	}

	@Override
	public List<Menu> findAllMenuTree() {
		return menuDAO.findAllMenuTree();		
	}

}
