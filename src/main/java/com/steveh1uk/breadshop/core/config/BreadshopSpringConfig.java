package com.steveh1uk.breadshop.core.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@ComponentScan(basePackages = {"com.steveh1uk.breadshop.core","com.steveh1uk.breadshop.web"})
@Configuration
public class BreadshopSpringConfig {

	private static final Logger logger = LoggerFactory.getLogger(BreadshopSpringConfig.class);

	@Autowired
	DataSource dataSource;

	@Bean
	public JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(dataSource);
	}
	
	// Start WebServer, access http://localhost:8082
		@Bean(initMethod = "start", destroyMethod = "stop")
		public Server startDBManager() throws SQLException {
			logger.info("Starting H2 Web Console ....");
			return Server.createWebServer();

		}

	
}
