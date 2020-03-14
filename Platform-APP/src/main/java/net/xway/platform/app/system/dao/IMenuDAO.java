package net.xway.platform.app.system.dao;

import java.util.List;

import net.xway.base.database.IBaseDAO;
import net.xway.platform.system.dto.Menu;

public interface IMenuDAO  extends IBaseDAO<Menu> {

	public List<Menu> findMenuByRoleID(int roleid);
	
	public List<Menu> findAllMenuTree();
}
