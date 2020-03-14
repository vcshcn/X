package net.xway.platform.system.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.xway.base.service.BizException;
import net.xway.base.utils.CipherUtils;
import net.xway.platform.system.dao.ICustomizeDAO;
import net.xway.platform.system.dao.ILoginDAO;
import net.xway.platform.system.dao.IUserDAO;
import net.xway.platform.system.dto.Customize;
import net.xway.platform.system.dto.Login;
import net.xway.platform.system.dto.User;
import net.xway.platform.system.service.IAccountService;

@Service
public class AccountServiceImpl implements IAccountService {

	@Autowired
	private ILoginDAO loginDAO;
	@Autowired
	private IUserDAO userDAO;
	@Autowired
	private ICustomizeDAO customizeDAO;
	
	@Override
	public Login findAccountByLoginname(String loginname) {
		return loginDAO.findLoginByLoginname(loginname);
	}
	
	@Override
	public User findUserByUserID(int userid) {
		return userDAO.findByPrimaryKey(userid);
	}

	@Override
	public boolean matchAccountPassword(Login account, String password) throws BizException {
		return password(password).equals(account.getPassword());
	}

	@Override
	public void saveProfile(int userid, String nickname, String tel, String mobile, Date birthday, String email, String homepage, String zipcode, String address, Integer countryid, Integer localeid, Integer timezoneid) {
		userDAO.saveProfile(userid, nickname, tel, mobile, birthday, email, homepage, zipcode, address, countryid, localeid, timezoneid);
	}

	@Override
	public void saveProfilePassword(int userid, String password) {
		loginDAO.saveProfilePassword(userid, password(password));
	}

	@Override
	public void saveProfileCustomize(Customize customize) {
		customizeDAO.update(customize);
	}

	private String password(String pass) throws BizException {
		try {
			return CipherUtils.HashWithMD5(pass);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			throw new BizException(e);
		}
	}

}
