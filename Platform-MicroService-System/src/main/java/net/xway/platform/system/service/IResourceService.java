package net.xway.platform.system.service;

import net.xway.base.database.PageResult;
import net.xway.base.database.Query;
import net.xway.platform.system.dto.Resource;

public interface IResourceService {

	public Resource findResourceByResourceID(int resourceid) ;

	public Resource findResourceByLink(String name);

	public PageResult<Resource> findResourcePage(Query query);
}
