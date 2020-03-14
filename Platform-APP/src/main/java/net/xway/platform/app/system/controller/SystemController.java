package net.xway.platform.app.system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemController {

	@RequestMapping("/ping")
	public long ping() {
		return System.currentTimeMillis();
	}
	
	@RequestMapping("/Calendar")
	public long now() {
		return System.currentTimeMillis();
	}
	
}
