package net.xway.platform.app.system.service;

import java.util.List;

import net.xway.platform.system.dto.TimeZone;

public interface ITimeZoneService {

	public List<TimeZone> findAllTimeZone();
	
	public TimeZone findTimeZoneByTimeZoneID(int timezoneid);

	
}
