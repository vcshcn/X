package net.xway.platform.system.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.xway.base.database.mybatis.dao.BatisDAO;
import net.xway.platform.system.dao.IMenuDAO;
import net.xway.platform.system.dto.Menu;

@Repository
public class MenuDAOImpl extends BatisDAO<Menu> implements IMenuDAO {	

	@Override
	public List<Menu> findMenuByRoleID(int roleid) {
		return selectList("findMenuByRoleID", roleid);
	}

	@Override
	public List<Menu> findAllMenuTree() {
		return selectList("findAllMenuTree");
	}

//
//	@Override
//	public int insertRoleMenu(int roleid, int[] menuid) {
//		return insert("insertRoleMenu",Parameters().set("menuid",menuid).set("roleid", roleid));
//	}
//
//	@Override
//	public int deleteMenuByRoleID(int roleid) {
//		return delete("deleteMenuByRoleID", roleid);
//	}
//

}
