package net.xway.platform.system.dao.impl;

import java.util.Date;

import org.springframework.stereotype.Repository;

import net.xway.base.database.mybatis.dao.BatisDAO;
import net.xway.platform.system.dao.IUserDAO;
import net.xway.platform.system.dto.User;

@Repository
public class UserDAOImpl extends BatisDAO<User> implements IUserDAO {

	@Override
	public int saveProfile(int userid, String nickname, String tel, String mobile, Date birthday, String email, String homepage, String zipcode, String address, Integer countryid, Integer localeid, Integer timezoneid) {
		return update("saveProfile", Parameters().set("userid", userid).set("nickname", nickname).set("tel", tel).set("mobile", mobile).set("birthday", birthday).set("email", email).set("homepage", homepage).set("zipcode", zipcode).set("address", address).set("countryid", countryid).set("localeid", localeid).set("timezoneid", timezoneid));
	}
}
