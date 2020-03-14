package net.xway.platform.system.dao;

import java.util.List;

import net.xway.base.database.IBaseDAO;
import net.xway.platform.system.dto.Field;

public interface IUserExcludeFieldDAO extends IBaseDAO<Field> {

	public List<Field> findTableUserField(int userid, int viewid);
	
	public int insertNotDisplayUserFields(int userid, int viewid, int[] fieldids);
	
	public int deleteNotDisplayUserFields(int userid, int viewid);
}
