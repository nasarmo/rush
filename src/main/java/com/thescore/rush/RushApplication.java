package com.thescore.rush;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Properties;

@SpringBootApplication
public class RushApplication {

	public static void main(String[] args) {
		Properties properties = new Properties();
		SpringApplication app = new SpringApplication(RushApplication.class);
		properties.setProperty("spring.resources.static-locations", "file:/app/ui/");
		app.setDefaultProperties(properties);
		app.run(args);
	}

	@Configuration
	public class Config implements WebMvcConfigurer {
		@Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**").allowedOrigins("*");
		}
	}
	
}
