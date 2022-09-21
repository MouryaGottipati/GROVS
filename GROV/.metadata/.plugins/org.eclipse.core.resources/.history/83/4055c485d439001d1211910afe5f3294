package com.grovs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class GrovsAppConfig extends WebMvcConfigurationSupport {
	
	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
	        "classpath:/META-INF/resources/", "classpath:/resources/",
	        "classpath:/static/", "classpath:/public/" };
	
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
		//registry.addResourceHandler("/app/static/**").addResourceLocations("classpath:/static/");
		
	}

}
