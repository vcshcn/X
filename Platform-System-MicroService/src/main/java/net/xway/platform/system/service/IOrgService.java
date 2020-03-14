package net.xway.platform.system.service;

import net.xway.base.database.PageResult;
import net.xway.base.database.Query;
import net.xway.platform.system.dto.Org;

public interface IOrgService {

	public PageResult<Org> queryOrgPage(Query query);

	public Org findOrgByOrgID(int orgid);

}
