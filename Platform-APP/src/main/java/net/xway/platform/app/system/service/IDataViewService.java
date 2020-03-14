package net.xway.platform.app.system.service;

import java.util.List;

import net.xway.platform.system.dto.Field;
import net.xway.platform.system.dto.View;

public interface IDataViewService {

	public View findViewByName(String name);
	
	public List<Field> findUserTableField(int userid, int viewid);
	
	public List<Field> findUserDisplayField(int userid, int viewid);
	
	public List<Field> findUserNotDisplayField(int userid, int viewid);
	
	public void saveUserExcludeField(int userid, int viewid, int[] fieldid);

}
