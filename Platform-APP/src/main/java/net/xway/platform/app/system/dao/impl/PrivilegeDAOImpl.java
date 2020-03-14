package net.xway.platform.app.system.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import net.xway.platform.app.system.dao.IPrivilegeDAO;
import net.xway.platform.app.system.dao.impl.feign.ISystemMicroFeign;
import net.xway.platform.system.dto.Privilege;

@Repository
public class PrivilegeDAOImpl implements IPrivilegeDAO {
	
	@Autowired
	private ISystemMicroFeign systemfeign;

	@Override
	public List<Privilege> findAll() {
		ResponseEntity<List<Privilege>> response = systemfeign.findAllPrivilege();
		return response.getBody();
	}

	@Override
	public Privilege findByPrimaryKey(int privilegeid) {
		ResponseEntity<Privilege> response = systemfeign.findPrivilegeByPrivilegeID(privilegeid);
		return response.getBody();
	}

}
