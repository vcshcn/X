package net.xway.platform.system.dao.impl;

import org.springframework.stereotype.Repository;

import net.xway.base.database.mybatis.dao.BatisDAO;
import net.xway.platform.system.dao.IOrgDAO;
import net.xway.platform.system.dto.Org;

@Repository
public class OrgDAOImpl extends BatisDAO<Org> implements IOrgDAO {

}
