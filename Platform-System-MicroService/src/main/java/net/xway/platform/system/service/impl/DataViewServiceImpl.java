package net.xway.platform.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.xway.platform.system.dao.IFieldDAO;
import net.xway.platform.system.dao.IUserExcludeFieldDAO;
import net.xway.platform.system.dao.IViewDAO;
import net.xway.platform.system.dto.Field;
import net.xway.platform.system.dto.View;
import net.xway.platform.system.service.IDataViewService;

@Service
public class DataViewServiceImpl implements IDataViewService {

	@Autowired
	private IViewDAO viewDAO;
	@Autowired
	private IFieldDAO fieldDAO;
	@Autowired
	private IUserExcludeFieldDAO excludeFieldDAO;
		
	@Override
	public View findViewByName(String name) {
		return viewDAO.findViewByName(name);
	}

	@Override
	public List<Field> findUserTableField(int userid, int viewid) {
		return fieldDAO.findUserTableField(userid, viewid);
	}

	@Override
	public List<Field> findUserDisplayField(int userid, int viewid) {
		return fieldDAO.findUserDisplayField(userid, viewid);
	}

	@Override
	public List<Field> findUserNotDisplayField(int userid, int viewid) {
		return fieldDAO.findUserNotDisplayField(userid, viewid);
	}

	@Override
	public void saveUserExcludeField(int userid, int viewid, int[] fieldid) {
		excludeFieldDAO.deleteNotDisplayUserFields(userid, viewid);
		if (fieldid.length > 0) {
			excludeFieldDAO.insertNotDisplayUserFields(userid, viewid, fieldid);
		}
	}


}
