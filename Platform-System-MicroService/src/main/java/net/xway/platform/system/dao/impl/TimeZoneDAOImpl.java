package net.xway.platform.system.dao.impl;

import org.springframework.stereotype.Repository;

import net.xway.base.database.mybatis.dao.BatisDAO;
import net.xway.platform.system.dao.ITimeZoneDAO;
import net.xway.platform.system.dto.TimeZone;

@Repository
public class TimeZoneDAOImpl extends BatisDAO<TimeZone> implements ITimeZoneDAO {


}
