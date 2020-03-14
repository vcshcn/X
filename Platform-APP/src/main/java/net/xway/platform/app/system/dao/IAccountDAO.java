package net.xway.platform.app.system.dao;

import net.xway.base.database.IBaseDAO;
import net.xway.platform.system.dto.Login;

public interface IAccountDAO extends IBaseDAO<Login> {

	public Login findAccountByLoginname(String loginname);
	
	public boolean matchAccountPassword(Login account, String password);
	
	public void saveProfilePassword(int userid, String password);
}
