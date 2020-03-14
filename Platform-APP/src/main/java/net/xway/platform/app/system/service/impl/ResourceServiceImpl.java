package net.xway.platform.app.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.xway.base.database.PageResult;
import net.xway.base.database.Query;
import net.xway.platform.app.system.dao.IResourceDAO;
import net.xway.platform.app.system.service.IResourceService;
import net.xway.platform.system.dto.Resource;

@Service
public class ResourceServiceImpl implements IResourceService {

	@Autowired
	private IResourceDAO resourceDAO;
	
	@Override
	public Resource findResourceByResourceID(int resourceid) {
		return resourceDAO.findByPrimaryKey(resourceid);
	}

	@Override
	public Resource findResourceByLink(String name) {
		return resourceDAO.findResourceByLink(name);
	}

	@Override
	public PageResult<Resource> findResourcePage(Query query) {
		return resourceDAO.findPage(query);
	}

}
