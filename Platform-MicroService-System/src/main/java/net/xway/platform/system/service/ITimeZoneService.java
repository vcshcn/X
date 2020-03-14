package net.xway.platform.system.service;

import java.util.List;

import net.xway.platform.system.dto.TimeZone;

public interface ITimeZoneService {

	public TimeZone findTimeZoneByTimeZoneID(int timezoneid);
	
	public List<TimeZone> findAllTimeZone();
}
