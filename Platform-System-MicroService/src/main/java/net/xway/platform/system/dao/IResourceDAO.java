package net.xway.platform.system.dao;

import net.xway.base.database.IBaseDAO;
import net.xway.base.database.PageResult;
import net.xway.base.database.Query;
import net.xway.platform.system.dto.Resource;

public interface IResourceDAO  extends IBaseDAO<Resource> {
	
	public PageResult<Resource> findPage(Query query);
	
	public Resource findResourceByLink(String name);
}
