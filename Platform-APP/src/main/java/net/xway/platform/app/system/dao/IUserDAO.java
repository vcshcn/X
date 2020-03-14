package net.xway.platform.app.system.dao;

import java.util.Date;

import net.xway.base.database.IBaseDAO;
import net.xway.platform.system.dto.Customize;
import net.xway.platform.system.dto.User;

public interface IUserDAO extends IBaseDAO<User>  {
	
	public void saveProfile(int userid, String nickname, String tel, String mobile, Date birthday, String email, String homepage, String zipcode, String address, Integer countryid, Integer localeid, Integer timezoneid);

	public void saveProfileCustomize(Customize customize); 
}
