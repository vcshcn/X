package net.xway.platform.system.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.xway.base.database.mybatis.dao.BatisDAO;
import net.xway.platform.system.dao.IToolBoxDAO;
import net.xway.platform.system.dto.ToolBox;

@Repository
public class ToolBoxDAOImpl extends BatisDAO<ToolBox> implements IToolBoxDAO {

	@Override
	public List<ToolBox> findSelectedToolBoxByUserID(int userid) {
		return selectList("findSelectedToolBoxByUserID", userid);
	}

	@Override
	public int deleteToolBoxByUserID(int userid) {
		return delete("deleteToolBoxByUserID", userid);
	}

	@Override
	public int insertUserToolBox(int userid, int[] toolboxids) {
		return insert("insertUserToolBox", Parameters().set("userid", userid).set("toolboxids", toolboxids));
	}
}
