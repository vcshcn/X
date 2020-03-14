package net.xway.platform.system.dao;

import net.xway.base.database.IBaseDAO;
import net.xway.platform.system.dto.Login;

public interface ILoginDAO  extends IBaseDAO<Login> {

	public Login findLoginByLoginname(String loginname);

	public void saveProfilePassword(int userid, String password);
}
