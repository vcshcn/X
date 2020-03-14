package net.xway.platform.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.xway.base.database.PageResult;
import net.xway.base.database.Query;
import net.xway.platform.system.dao.IOrgDAO;
import net.xway.platform.system.dto.Org;
import net.xway.platform.system.service.IOrgService;

@Service
public class OrgServiceImpl implements IOrgService {

	@Autowired
	private IOrgDAO orgDAO;
	
	@Override
	public PageResult<Org> queryOrgPage(Query query) {
		return orgDAO.findPage(query);
	}

	@Override
	public Org findOrgByOrgID(int orgid) {
		return orgDAO.findByPrimaryKey(orgid);
	}

}
