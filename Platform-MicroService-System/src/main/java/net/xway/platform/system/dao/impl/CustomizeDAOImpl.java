package net.xway.platform.system.dao.impl;

import org.springframework.stereotype.Repository;

import net.xway.base.database.mybatis.dao.BatisDAO;
import net.xway.platform.system.dao.ICustomizeDAO;
import net.xway.platform.system.dto.Customize;

@Repository
public class CustomizeDAOImpl extends BatisDAO<Customize> implements ICustomizeDAO {

}
