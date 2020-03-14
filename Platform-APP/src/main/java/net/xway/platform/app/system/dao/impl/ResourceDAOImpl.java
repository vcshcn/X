package net.xway.platform.app.system.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import net.xway.platform.app.system.dao.IResourceDAO;
import net.xway.platform.app.system.dao.impl.feign.ISystemMicroFeign;
import net.xway.platform.system.dto.Resource;

@Repository
public class ResourceDAOImpl  implements IResourceDAO {

	@Autowired
	private ISystemMicroFeign systemfeign;

	@Override
	public Resource findResourceByLink(String name) {
		ResponseEntity<Resource> resource = systemfeign.findResourceByLink(name);
		return resource.getBody();
	}

}
