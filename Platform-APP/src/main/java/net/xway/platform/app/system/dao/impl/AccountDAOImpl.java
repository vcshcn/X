package net.xway.platform.app.system.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import net.xway.platform.app.system.dao.IAccountDAO;
import net.xway.platform.app.system.dao.impl.feign.ISystemMicroFeign;
import net.xway.platform.system.dto.Login;

@Repository
public class AccountDAOImpl implements IAccountDAO {
	
	@Autowired
	private ISystemMicroFeign systemfeign;
	
	@Override
	public Login findAccountByLoginname(String loginname) {
		ResponseEntity<Login> entity = systemfeign.findAccountByLoginname(loginname);
		return entity.getBody();
	}

	@Override
	public boolean matchAccountPassword(Login account, String password) {
		ResponseEntity<Boolean> entity = systemfeign.matchAccountPassword(account,password);
		return entity.getBody();
	}

	@Override
	public void saveProfilePassword(int userid, String password) {
		systemfeign.saveProfilePassword(userid,password);
	}

}
