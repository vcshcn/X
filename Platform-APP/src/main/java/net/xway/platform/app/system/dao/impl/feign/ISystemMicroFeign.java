package net.xway.platform.app.system.dao.impl.feign;

import org.springframework.cloud.openfeign.FeignClient;

import net.xway.platform.system.micro.feign.ISystemMicroService;

@FeignClient(value = "Platform-MicroService-System")
public interface ISystemMicroFeign extends ISystemMicroService {

}
