package com.steveh1uk.breadshop.core.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/**
 * A simple security login using the default Spring security form to allow me to
 * get the username of the person who will order the bread
 */
@Configuration
@EnableWebSecurity
public class WebSecuritySpringConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().authenticated().and().formLogin()
				// disable Spring csrf support since I am using a generated login page and doing
				// JSF anyway
				.and().csrf().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("select username, password, true from users where username = ?")
		.authoritiesByUsernameQuery("select username, 'ROLE_USER' from users where username=?")
		.passwordEncoder(NoOpPasswordEncoder.getInstance());
	}



}