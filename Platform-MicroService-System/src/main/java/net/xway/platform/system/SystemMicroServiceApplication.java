package net.xway.platform.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SystemMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SystemMicroServiceApplication.class, args);
	}

}
