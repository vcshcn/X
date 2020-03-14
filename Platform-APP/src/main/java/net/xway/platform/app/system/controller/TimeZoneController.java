package net.xway.platform.app.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.xway.base.controller.JsonResult;
import net.xway.platform.app.system.service.ITimeZoneService;
import net.xway.platform.system.dto.TimeZone;

@RestController
public class TimeZoneController {
	
	@Autowired
	private ITimeZoneService timeZoneService;
	
	@RequestMapping("/ListAllTimeZone")
	public JsonResult<List<TimeZone>> findAllTimeZone() {
		JsonResult<List<TimeZone>> result = new JsonResult<>();
		List<TimeZone> locales = timeZoneService.findAllTimeZone();
		result.setObject(locales);
		return result;
	}

}
