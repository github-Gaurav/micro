package studentservice.StudentService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class AppConfig {
	
	@Bean
	RestTemplate rest() {
		
		RestTemplate rest = new RestTemplate();
		return rest;
		
	}
	
	

}
