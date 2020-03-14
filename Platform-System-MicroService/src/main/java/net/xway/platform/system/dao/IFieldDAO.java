package net.xway.platform.system.dao;

import java.util.List;

import net.xway.base.database.IBaseDAO;
import net.xway.platform.system.dto.Field;

public interface IFieldDAO extends IBaseDAO<Field> {
	
	public List<Field> findUserTableField(int userid, int viewid);
	
	public List<Field> findUserDisplayField(int userid, int viewid);
	
	public List<Field> findUserNotDisplayField(int userid, int viewid);
}
