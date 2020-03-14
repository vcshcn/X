package net.xway.platform.system.dao.impl;

import org.springframework.stereotype.Repository;

import net.xway.base.database.mybatis.dao.BatisDAO;
import net.xway.platform.system.dao.IViewDAO;
import net.xway.platform.system.dto.View;

@Repository
public class ViewDAOImpl extends BatisDAO<View> implements IViewDAO {

	@Override
	public View findViewByName(String name) {
		return uniqueResult("findViewByName", name);
	}
	
	
}
