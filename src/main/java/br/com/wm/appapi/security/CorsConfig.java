package br.com.wm.appapi.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
	
	@Value("#{'${appapi.cors.allowed.origins}'.split(',')}")
	private List<String> rawOrigins;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins(getOrigins()).allowedMethods("*").allowedHeaders("*");
	}

	private String[] getOrigins() {
	    int size = rawOrigins.size();
	    String[] originArray = new String[size];
	    return rawOrigins.toArray(originArray);
	}
}
