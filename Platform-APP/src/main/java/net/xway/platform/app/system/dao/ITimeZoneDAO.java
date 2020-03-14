package net.xway.platform.app.system.dao;

import java.util.List;

import net.xway.base.database.IBaseDAO;
import net.xway.platform.system.dto.TimeZone;

public interface ITimeZoneDAO extends IBaseDAO<TimeZone> {

	public List<TimeZone> findAll();

}
