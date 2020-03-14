package net.xway.platform.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import net.xway.platform.app.system.interceptor.PrivilegeInterceptor;
import net.xway.platform.app.system.interceptor.TimeInterceptor;

@EnableFeignClients
@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@Configuration
public class Application implements WebMvcConfigurer {

	@Autowired
	private PrivilegeInterceptor privilegeInterceptor;
	@Autowired
	private TimeInterceptor timeInterceptor;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(timeInterceptor);
		registry.addInterceptor(privilegeInterceptor);		
		WebMvcConfigurer.super.addInterceptors(registry);
	}

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		configurer.setUseSuffixPatternMatch(false);
		configurer.setUseRegisteredSuffixPatternMatch(true);
	}

	@Bean
	public ServletRegistrationBean<DispatcherServlet> servletRegistrationBean(DispatcherServlet dispatcherServlet) {        
		ServletRegistrationBean<DispatcherServlet> servletServletRegistrationBean = new ServletRegistrationBean<>(dispatcherServlet);        
		servletServletRegistrationBean.addUrlMappings("*.do");
		return servletServletRegistrationBean;
	}
}
