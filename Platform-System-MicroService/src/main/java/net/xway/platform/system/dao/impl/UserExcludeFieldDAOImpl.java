package net.xway.platform.system.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.xway.base.database.mybatis.dao.BatisDAO;
import net.xway.platform.system.dao.IUserExcludeFieldDAO;
import net.xway.platform.system.dto.Field;

@Repository
public class UserExcludeFieldDAOImpl extends BatisDAO<Field> implements IUserExcludeFieldDAO {

	@Override
	public List<Field> findTableUserField(int userid, int viewid) {
		return selectList("findTableUserField", Parameters().set("userid",userid).set("viewid", viewid));
	}

	@Override
	public int insertNotDisplayUserFields(int userid, int viewid, int[] fieldids) {
		return insert("insertNotDisplayUserFields", Parameters().set("userid",userid).set("viewid", viewid).set("fieldids", fieldids));
	}
	
	@Override
	public int deleteNotDisplayUserFields(int userid, int viewid) {
		return delete("deleteNotDisplayUserFields", Parameters().set("userid",userid).set("viewid", viewid));
	}

}
