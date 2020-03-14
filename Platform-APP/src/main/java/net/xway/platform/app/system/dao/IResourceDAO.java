package net.xway.platform.app.system.dao;

import net.xway.base.database.IBaseDAO;
import net.xway.platform.system.dto.Resource;

public interface IResourceDAO  extends IBaseDAO<Resource> {
	
	public Resource findResourceByLink(String name);
}
