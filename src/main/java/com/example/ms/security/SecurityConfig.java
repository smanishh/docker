package com.example.ms.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	 	@Bean
	    public UserDetailsService userDetailsService() {

	        User.UserBuilder users = User.withDefaultPasswordEncoder();
	        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
	        manager.createUser(users.username("user").password("password").roles("USER").build());
	        manager.createUser(users.username("admin").password("password").roles("ADMIN").build());
	        return manager;

	    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeRequests()
			.antMatchers("/rest").hasRole("USER")
			.antMatchers("/").hasRole("ADMIN")
			.and()
			.csrf().disable();
	}
	
}
