package net.xway.platform.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.xway.platform.system.dao.ITimeZoneDAO;
import net.xway.platform.system.dto.TimeZone;
import net.xway.platform.system.service.ITimeZoneService;

@Service
public class TimeZoneServiceImpl implements ITimeZoneService {

	@Autowired
	private ITimeZoneDAO timezoneDAO;
	
	@Override
	public TimeZone findTimeZoneByTimeZoneID(int timezoneid) {
		return timezoneDAO.findByPrimaryKey(timezoneid);
	}

	@Override
	public List<TimeZone> findAllTimeZone() {
		return timezoneDAO.findAll();
	}

}
