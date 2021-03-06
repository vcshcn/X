package net.xway.platform.app.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.xway.platform.app.system.dao.IPrivilegeDAO;
import net.xway.platform.app.system.service.IPrivilegeService;
import net.xway.platform.system.dto.Privilege;

@Service
public class PrivilegeServiceImpl implements IPrivilegeService {

	@Autowired
	private IPrivilegeDAO privilegeDAO;

	@Override
	public List<Privilege> findAllPrivilege() {
		return privilegeDAO.findAll();
	}

	@Override
	public Privilege findPrivilegeByPrivilegeID(int privilegeid) {
		return privilegeDAO.findByPrimaryKey(privilegeid);
	}

//	@Override
//	public Privilege findPrivilegeByPrivilegeID(int privilegeid) {
//		return privilegeDAO.findByPrimaryKey(privilegeid);
//	}
//
//	@Override
//	public List<Privilege> findWithoutPrivilegeByRole(int roleid) {
//		return privilegeDAO.findWithoutPrivilegeByRole(roleid);
//	}
	
}
