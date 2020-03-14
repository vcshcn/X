package net.xway.platform.system.dao.impl;

import org.springframework.stereotype.Repository;

import net.xway.base.database.mybatis.dao.BatisDAO;
import net.xway.platform.system.dao.IResourceDAO;
import net.xway.platform.system.dto.Resource;

@Repository
public class ResourceDAOImpl extends BatisDAO<Resource> implements IResourceDAO {

	@Override
	public Resource findResourceByLink(String name) {
		return uniqueResult("findResourceByLink", name);
	}

}
