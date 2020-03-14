package net.xway.platform.app.system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.xway.base.controller.JsonResult;
import net.xway.platform.system.dto.Gender;

@RestController
public class EnumController {


	@RequestMapping("/ListAllGender")
	public JsonResult<Gender[]> findGender() {
		return new JsonResult<Gender[]>(Gender.values());
	}

}
