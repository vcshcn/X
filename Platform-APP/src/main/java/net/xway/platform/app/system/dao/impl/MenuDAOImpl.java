package net.xway.platform.app.system.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import net.xway.base.database.PageResult;
import net.xway.base.database.Query;
import net.xway.platform.app.system.dao.IMenuDAO;
import net.xway.platform.app.system.dao.impl.feign.ISystemMicroFeign;
import net.xway.platform.system.dto.Menu;

@Repository
public class MenuDAOImpl implements IMenuDAO {

	@Autowired
	private ISystemMicroFeign systemfeign;

	@Override
	public List<Menu> findMenuByRoleID(int roleid) {
		ResponseEntity<List<Menu>> menus = systemfeign.findMenuByRoleID(roleid);
		return menus.getBody();
	}
	
	@Override
	public List<Menu> findAll() {
		ResponseEntity<List<Menu>> menus = systemfeign.findAllMenu();
		return menus.getBody();
	}

	@Override
	public List<Menu> findAllMenuTree() {
		ResponseEntity<List<Menu>> menus = systemfeign.findAllMenuTree();
		return menus.getBody();
	}
	
	@Override
	public Menu findByPrimaryKey(int menuid) {
		ResponseEntity<Menu> menu = systemfeign.findMenuByMenuID(menuid);
		return menu.getBody();
	}

}
