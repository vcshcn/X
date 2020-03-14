package net.xway.platform.app.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.xway.base.controller.JsonResult;
import net.xway.platform.app.system.service.ICountryService;
import net.xway.platform.system.dto.Country;

@RestController
public class CountryController {

	@Autowired
	private ICountryService countryService;
	
	@RequestMapping("/ListAllCountry")
	public JsonResult<List<Country>> findAllCountry() {
		JsonResult<List<Country>> result = new JsonResult<>();
		List<Country> countrys = countryService.findAllCountry();
		result.setObject(countrys);
		return result;
	}
}
