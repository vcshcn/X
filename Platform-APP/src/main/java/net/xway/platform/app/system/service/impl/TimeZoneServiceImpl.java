package net.xway.platform.app.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.xway.platform.app.system.dao.ITimeZoneDAO;
import net.xway.platform.app.system.service.ITimeZoneService;
import net.xway.platform.system.dto.TimeZone;

@Service
public class TimeZoneServiceImpl implements ITimeZoneService {

	@Autowired
	private ITimeZoneDAO timezoneDAO;

	@Override
	public List<TimeZone> findAllTimeZone() {
		return timezoneDAO.findAll();
	}

	@Override
	public TimeZone findTimeZoneByTimeZoneID(int timezoneid) {
		return timezoneDAO.findByPrimaryKey(timezoneid);
	}

}
