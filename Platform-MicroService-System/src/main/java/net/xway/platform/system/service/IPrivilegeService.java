package net.xway.platform.system.service;

import java.util.List;

import net.xway.platform.system.dto.Privilege;

public interface IPrivilegeService {

	public Privilege findPrivilegeByPrivilegeID(int privilegeid);

	public List<Privilege> findAllPrivilege();
//	
//	public List<Privilege> findWithoutPrivilegeByRole(int roleid);

}
