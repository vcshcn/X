package net.xway.platform.system.service;

import java.util.Date;

import net.xway.base.service.BizException;
import net.xway.platform.system.dto.Customize;
import net.xway.platform.system.dto.Login;
import net.xway.platform.system.dto.User;

public interface IAccountService {

	public Login findAccountByLoginname(String loginname);
	
	public User findUserByUserID(int userid);
	
	public boolean matchAccountPassword(Login account, String password) throws BizException;
	
	public void saveProfile(int userid, String nickname, String tel, String mobile, Date birthday, String email, String homepage, String zipcode, String address, Integer countryid, Integer localeid, Integer timezoneid);
	
	public void saveProfileCustomize(Customize customize);
	
	public void saveProfilePassword(int userid, String password);
}
