package com.steveh1uk.breadshop.core.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages="com.steveh1uk.breadshop.core.reserve")
public class JpaConfiguration {
	
	@Autowired
	DataSource dataSource;
	
	
	@Bean
	 public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	 
	 HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

	 
	 LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
	 em.setDataSource(dataSource);
	 em.setPackagesToScan(new String[] { "com.steveh1uk.breadshop.core.reserve" });
	 em.setJpaVendorAdapter(vendorAdapter);
	 em.setJpaProperties(additionalProperties());
	 
	 return em;
	 }
	
	
	   @Bean
	   public PlatformTransactionManager transactionManager(
	     EntityManagerFactory emf){
	       JpaTransactionManager transactionManager = new JpaTransactionManager();
	       transactionManager.setEntityManagerFactory(emf);
	 
	       return transactionManager;
	   }
	   
	   Properties additionalProperties() {
	       Properties properties = new Properties();
	       properties.setProperty(
	         "hibernate.dialect", "org.hibernate.dialect.H2Dialect");
	  
	       return properties;
	   }
	
}

