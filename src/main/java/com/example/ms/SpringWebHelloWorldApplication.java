package com.example.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringWebHelloWorldApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebHelloWorldApplication.class, args);
	}
	
	@Bean
	public RestTemplate getRestTemplate() {
      return new RestTemplate();
    }

	@Bean
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource dataSource() {
	    return new DataSource();
	}
}
