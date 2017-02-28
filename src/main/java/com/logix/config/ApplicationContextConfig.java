package com.logix.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.logix.controller.ComponentController;

@Configuration
@ComponentScan("com.logix.*")

@EnableTransactionManagement

// Load to Environment.
@PropertySources({ @PropertySource("classpath:ds/datasource-cfg.properties") })

public class ApplicationContextConfig {
	
	private final Logger LOG = LoggerFactory.getLogger(ApplicationContextConfig.class);
	
	@Autowired
	private Environment env;

	@Bean(name = "dataSource")
	  public DataSource getDataSource() {
	      DriverManagerDataSource dataSource = new DriverManagerDataSource();

	      // See: datasouce-cfg.properties
	      dataSource.setDriverClassName(env.getProperty("ds.database-driver"));
	      dataSource.setUrl(env.getProperty("ds.url"));
	      dataSource.setUsername(env.getProperty("ds.username"));
	      dataSource.setPassword(env.getProperty("ds.password"));

	      LOG.info("## getDataSource: " + dataSource);

	      return dataSource;
	  }
	
	  @Bean(name = "transactionManager")
	  public DataSourceTransactionManager getTransactionManager() {
	      DataSourceTransactionManager txManager = new DataSourceTransactionManager();


	      DataSource dataSource = this.getDataSource();
	      txManager.setDataSource(dataSource);


	      return txManager;
	  }
}
