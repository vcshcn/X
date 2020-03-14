package net.xway.platform.system.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.xway.base.database.mybatis.dao.BatisDAO;
import net.xway.platform.system.dao.IFieldDAO;
import net.xway.platform.system.dto.Field;

@Repository
public class FieldDAOImpl extends BatisDAO<Field> implements IFieldDAO {

	@Override
	public List<Field> findUserTableField(int userid, int viewid) {
		return selectList("findUserTableField", Parameters().set("userid",userid).set("viewid", viewid));
	}

	@Override
	public List<Field> findUserDisplayField(int userid, int viewid) {
		return selectList("findUserDisplayField", Parameters().set("userid",userid).set("viewid", viewid));
	}

	@Override
	public List<Field> findUserNotDisplayField(int userid, int viewid) {
		return selectList("findUserNotDisplayField", Parameters().set("userid",userid).set("viewid", viewid));
	}

}
