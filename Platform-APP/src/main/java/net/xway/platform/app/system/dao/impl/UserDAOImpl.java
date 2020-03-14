package net.xway.platform.app.system.dao.impl;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import net.xway.platform.app.system.dao.IUserDAO;
import net.xway.platform.app.system.dao.impl.feign.ISystemMicroFeign;
import net.xway.platform.system.dto.Customize;
import net.xway.platform.system.dto.User;

@Repository
public class UserDAOImpl implements IUserDAO {

	@Autowired
	private ISystemMicroFeign systemfeign;

	@Override
	public void saveProfile(int userid, String nickname, String tel, String mobile, Date birthday, String email, String homepage, String zipcode, String address, Integer countryid, Integer localeid, Integer timezoneid) {
		HashMap<String, Object> json = new HashMap<>();
		json.put("userid", userid);
		json.put("nickname", nickname);
		json.put("tel", tel);
		json.put("mobile", mobile);
		json.put("birthday",birthday != null ? birthday.getTime() : null );
		json.put("email", email);
		json.put("homepage", homepage);
		json.put("zipcode",zipcode);
		json.put("address", address);
		json.put("countryid", countryid);
		json.put("localeid", localeid);
		json.put("timezoneid", timezoneid);
		systemfeign.saveProfile(json);
	}

	@Override
	public void saveProfileCustomize(Customize customize) {
		systemfeign.saveProfileCustomize(customize);
	}

	@Override
	public User findByPrimaryKey(int userid) {
		ResponseEntity<User> response = systemfeign.findUserByUserID(userid);
		return response.getBody();
	}



}
