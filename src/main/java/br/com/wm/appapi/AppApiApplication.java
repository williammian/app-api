package br.com.wm.appapi;

import java.util.Date;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class AppApiApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(AppApiApplication.class);

	public static void main(String[] args) {
		logger.info("Starting server App-Api ...");
		
		System.setProperty("spring.profiles.active", "prod");
		
		// spring.profiles.active = prod
		//System.setProperty("DATABASE_URL", "jdbc:mysql://localhost:3306/app?createDatabaseIfNotExist=true&useSSL=false&useTimezone=true&serverTimezone=America/Sao_Paulo&allowPublicKeyRetrieval=true");
		//System.setProperty("DATABASE_USERNAME", "user");
		//System.setProperty("DATABASE_PASSWORD", "user123");
		//System.setProperty("SERVER_PORT", "8080");
		
		SpringApplication.run(AppApiApplication.class, args);
	}
	
	@PostConstruct
	public void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
		logger.info("Server running in UTC timezone :"+new Date());
	}

}
