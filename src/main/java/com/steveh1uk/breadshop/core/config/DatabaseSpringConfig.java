package com.steveh1uk.breadshop.core.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class DatabaseSpringConfig {

	@Bean
	public DataSource dataSource() {

		// @formatter:off
		// no need shutdown, EmbeddedDatabaseFactoryBean will take care of this
		EmbeddedDatabase db = new EmbeddedDatabaseBuilder()
 	        .setType(EmbeddedDatabaseType.H2)
	        .generateUniqueName(false)
			.setScriptEncoding("UTF-8")
			.addScript("db/sql/create-db.sql")
			.addScript("db/sql/insert-data.sql")
			.build();
		return db;
		// @formatter:on
	}

	
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
	return new JdbcTemplate(dataSource);
	}


}
