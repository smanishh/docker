package com.example.ms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:global.properties")
public class GlobalProperties {
	
	@Value("${thread-pool}")
    private int threadPool;

    @Value("${email}")
    private String email;

	public int getThreadPool() {
		return threadPool;
	}

	public String getEmail() {
		return email;
	}   

}
