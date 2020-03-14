package net.xway.platform.app.system.service;

import java.util.Date;

import net.xway.platform.system.dto.Customize;
import net.xway.platform.system.dto.Resource;
import net.xway.platform.system.dto.User;

public interface IAccountService {

	public User login(String loginname, String password) throws NoSuchAccountException, AccountLockException, PasswordNotMatchException;
	
	public User findUserByUserID(int userid);
	
	public void saveProfile(int userid, String nickname, String tel, String mobile, Date birthday, String email, String homepage, String zipcode, String address, Integer countryid, Integer localeid, Integer timezoneid);
	
	public void saveProfilePassword(int userid, String password);
	
	public void saveProfileCustomize(Customize customize);
	
	public boolean canAccess(User user, Resource resource);
	
	public boolean hasPrivilege(User user, String privilege);
}
