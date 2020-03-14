package net.xway.platform.app.system.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import net.xway.base.database.PageResult;
import net.xway.base.database.Query;
import net.xway.platform.app.system.dao.IOrgDAO;
import net.xway.platform.app.system.dao.impl.feign.ISystemMicroFeign;
import net.xway.platform.system.dto.Org;
import net.xway.platform.system.dto.Role;

@Repository
public class OrgDAOImpl implements IOrgDAO {

	@Autowired
	private ISystemMicroFeign systemfeign;

	@Override
	public PageResult<Org> findPage(Query query) {
		ResponseEntity<PageResult<Org>> page = systemfeign.queryOrgPage(query);
		return page.getBody();
	}

	@Override
	public Org findByPrimaryKey(int orgid) {
		ResponseEntity<Org> org = systemfeign.findOrgByOrgID(orgid);
		return org.getBody();
	}

}
