package net.xway.platform.app.system.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import net.xway.platform.app.system.dao.IFieldDAO;
import net.xway.platform.app.system.dao.impl.feign.ISystemMicroFeign;
import net.xway.platform.system.dto.Field;

@Repository
public class FieldDAOImpl implements IFieldDAO {

	@Autowired
	private ISystemMicroFeign systemfeign;

	@Override
	public List<Field> findUserTableField(int userid, int viewid) {
		ResponseEntity<List<Field>> response = systemfeign.findUserTableField(userid, viewid);
		return response.getBody();
	}

	@Override
	public List<Field> findUserDisplayField(int userid, int viewid) {
		ResponseEntity<List<Field>> response = systemfeign.findUserDisplayField(userid, viewid);
		return response.getBody();
	}

	@Override
	public List<Field> findUserNotDisplayField(int userid, int viewid) {
		ResponseEntity<List<Field>> response = systemfeign.findUserNotDisplayField(userid, viewid);
		return response.getBody();
	}

	@Override
	public void saveUserExcludeField(int userid, int viewid, int[] fieldid) {
		systemfeign.saveUserExcludeField(userid, viewid,fieldid);
	}

}
