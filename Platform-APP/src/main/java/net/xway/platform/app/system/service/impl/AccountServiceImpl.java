package net.xway.platform.app.system.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.xway.platform.app.system.dao.IAccountDAO;
import net.xway.platform.app.system.dao.IEmployeeDAO;
import net.xway.platform.app.system.dao.IUserDAO;
import net.xway.platform.app.system.service.AccountLockException;
import net.xway.platform.app.system.service.IAccountService;
import net.xway.platform.app.system.service.NoSuchAccountException;
import net.xway.platform.app.system.service.PasswordNotMatchException;
import net.xway.platform.system.dto.Customize;
import net.xway.platform.system.dto.Login;
import net.xway.platform.system.dto.Privilege;
import net.xway.platform.system.dto.Resource;
import net.xway.platform.system.dto.User;

@Service
public class AccountServiceImpl implements IAccountService {

	@Autowired
	private IAccountDAO accountDAO;
	@Autowired
	private IEmployeeDAO employeeDAO;
	@Autowired
	private IUserDAO userDAO;
	
	@Override
	public User login(String loginname, String password) throws NoSuchAccountException, AccountLockException, PasswordNotMatchException {
		Login login = accountDAO.findAccountByLoginname(loginname);
		if (login == null) {
			throw new NoSuchAccountException(loginname);
		}
		if ( login.getLock() == true) {
			throw new AccountLockException(loginname);
		}
		
		if (accountDAO.matchAccountPassword(login, password) == false) {
			throw new PasswordNotMatchException(loginname, password);
		}

		User user = userDAO.findByPrimaryKey(login.getLoginid());
		if (user.getType() == User.TYPE_EMPLOYEE) {
			user =  employeeDAO.findByPrimaryKey(login.getLoginid());
		}
		else if (user.getType() == User.TYPE_CUSTOMER) {
			throw new java.lang.UnsupportedOperationException("Not yet implements customer");
		}
		return user;
	}

	@Override
	public User findUserByUserID(int userid) {
		User user = userDAO.findByPrimaryKey(userid);
		if (user.getType() == User.TYPE_EMPLOYEE) {
			return  employeeDAO.findByPrimaryKey(user.getUserid());
		}
		else if (user.getType() == User.TYPE_CUSTOMER) {
			throw new java.lang.UnsupportedOperationException("Not yet implements customer");
		}
		throw new java.lang.IllegalArgumentException("Illegal Argument User Type On " + user.getType());
	}

	@Override
	public void saveProfile(int userid, String nickname, String tel, String mobile, Date birthday, String email, String homepage, String zipcode, String address, Integer countryid, Integer localeid, Integer timezoneid) {
		userDAO.saveProfile(userid, nickname, tel, mobile, birthday, email, homepage, zipcode, address, countryid, localeid, timezoneid);
	}

	@Override
	public void saveProfileCustomize(Customize customize) {
		userDAO.saveProfileCustomize(customize);
	}

	@Override
	public void saveProfilePassword(int userid, String password) {
		accountDAO.saveProfilePassword(userid, password);
	}

	@Override
	public boolean canAccess(User user, Resource resource) {
		if (resource.getPrivilege() == null) {
			return true;
		}
		return hasPrivilege(user, resource.getPrivilege().getName());
	}
	
	@Override
	public boolean hasPrivilege(User user, String privilege) {
		for (Privilege p : user.getRole().getPrivileges()) {
			if (p.getName().equals(privilege)) {
				return true;
			}
		}
		return false;
	}

}
