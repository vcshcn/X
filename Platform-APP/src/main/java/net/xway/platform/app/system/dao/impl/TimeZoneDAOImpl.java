package net.xway.platform.app.system.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import net.xway.platform.app.system.dao.ITimeZoneDAO;
import net.xway.platform.app.system.dao.impl.feign.ISystemMicroFeign;
import net.xway.platform.system.dto.TimeZone;

@Repository
public class TimeZoneDAOImpl  implements ITimeZoneDAO {

	@Autowired
	private ISystemMicroFeign systemfeign;

	@Override
	public List<TimeZone> findAll() {
		ResponseEntity<List<TimeZone>> entity = systemfeign.findAllTimeZone();
		return entity.getBody();
	}

	@Override
	public TimeZone findByPrimaryKey(int timezoneid) {
		ResponseEntity<TimeZone> entity = systemfeign.findTimeZoneByTimeZoneID(timezoneid);
		return entity.getBody();
	}

}
