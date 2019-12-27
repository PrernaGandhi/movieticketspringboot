package com.epam.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;

	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	private AccessDeniedHandler accessDeniedHandler;

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/", "/login", "/registerUser", "/css/**").permitAll()
				.antMatchers("/homePage", "/displayTimings", "/displayTheaters", "/bookSeats", "/displayDate",
						"/displayMovies", "/displaySeats")
				.hasAuthority("USER").antMatchers("/admin", "/addLocation", "/displayLocation", "/adminPage")
				.hasAuthority("ADMIN").and().formLogin().successHandler(authenticationSuccessHandler)
				.failureHandler(authenticationFailureHandler).permitAll().and().exceptionHandling()
				.accessDeniedHandler(accessDeniedHandler).and().logout().invalidateHttpSession(true)
				.clearAuthentication(true).permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN").authorities("ADMIN");
	}

}
