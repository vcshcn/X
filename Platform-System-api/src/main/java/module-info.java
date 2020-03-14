module net.xway.platform.system.api  {
	
	requires transitive net.xway.platform.base;
	requires spring.web;
	
	exports net.xway.platform.system.dto;
	exports net.xway.platform.system.micro.feign;
}
