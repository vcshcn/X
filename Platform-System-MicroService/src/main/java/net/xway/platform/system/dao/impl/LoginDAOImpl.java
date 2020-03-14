package net.xway.platform.system.dao.impl;

import org.springframework.stereotype.Repository;

import net.xway.base.database.mybatis.dao.BatisDAO;
import net.xway.platform.system.dao.ILoginDAO;
import net.xway.platform.system.dto.Login;

@Repository
public class LoginDAOImpl extends BatisDAO<Login> implements ILoginDAO {

	@Override
	public Login findLoginByLoginname(String loginname) {
		return uniqueResult("findLoginByLoginname", loginname);
	}

	@Override
	public void saveProfilePassword(int userid, String password) {
		update("updateLoginPassword", Parameters().set("loginid", userid).set("password", password));
	}

}
