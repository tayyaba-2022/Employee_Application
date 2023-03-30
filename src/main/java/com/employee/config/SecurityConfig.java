package com.employee.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Value("${application.default.page}")
	private String defaultPage;
	private String defaultURL;
	
	@PostConstruct
	public void postConstruct() {
		defaultURL="/"+defaultPage;
	}

	@Bean
	public UserDetailsService userDetails(PasswordEncoder encoder) {
		UserDetails user1=User.withUsername("Admin").password(encoder.encode("admin@01")).roles("ADMIN").build();
		UserDetails user2=User.withUsername("Appuser").password(encoder.encode("user@01")).roles("USER").build();
		return new InMemoryUserDetailsManager(user1,user2);
	}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		return http.csrf().disable().authorizeHttpRequests()
				.antMatchers("/welcome").permitAll()
				.antMatchers("/**").authenticated()
				.antMatchers("/add").hasRole("ADMIN")
				.and().formLogin()
				.loginProcessingUrl("/perform_login").defaultSuccessUrl(defaultURL,true)
				.and()
				.logout((logout) -> logout.permitAll()).build();
	}
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}
