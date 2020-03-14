package net.xway.platform.app.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.xway.base.controller.JsonResult;
import net.xway.platform.app.system.service.ILocaleService;
import net.xway.platform.system.dto.Locale;

@RestController
public class LocaleController {

	@Autowired
	private ILocaleService localService;
	
	@RequestMapping("/ListAllLocale")
	public JsonResult<List<Locale>> findAllLocale() {
		JsonResult<List<Locale>> result = new JsonResult<>();
		List<Locale> locales = localService.findAllLocale();
		result.setObject(locales);
		return result;
	}
}
