package org.java.app.mvc.auth.conf;

import org.java.app.mvc.auth.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuthConf {

	
	@Bean
		SecurityFilterChain filterChain(HttpSecurity http)
			throws Exception {
				http.authorizeHttpRequests()
					.requestMatchers("/pizze/create").hasAuthority("ADMIN")
					.requestMatchers("/pizze/update/**").hasAuthority("ADMIN")
					.requestMatchers("/pizze/delete/**").hasAuthority("ADMIN")
					.requestMatchers("/pizze/test\\d").hasAuthority("ADMIN")
					.requestMatchers("/**").permitAll()
					.and().formLogin().defaultSuccessUrl("/pizze")
					.and().logout();
		return http.build();
	}
	
	@Bean
	UserService userDetailService() {
		return new UserService();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		
		authProvider.setUserDetailsService(userDetailService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}
}
