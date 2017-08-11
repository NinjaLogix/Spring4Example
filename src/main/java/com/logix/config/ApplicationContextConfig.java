package com.logix.config;

import com.logix.aop.CustomerAspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * This class is the main point of reference for initiating the IOC container with beans, setting datasources,
 * transaction management, and setting environment. Since this is Java config
 * @Author Branden Boyington
 * @version
 * @since 1.0.0
 *
 * 1.0.1-AOP additions:
 * - AspectJAutoProxy
 * - custAspect
 * @since 1.0.1-AOP
 */
@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
@ComponentScan(basePackages = "com.logix")
@PropertySources({ @PropertySource("classpath:ds/datasource-cfg.properties") }) //Load to environment
public class ApplicationContextConfig {
	
	private final Logger LOG = LoggerFactory.getLogger(ApplicationContextConfig.class);

	/**
	 * Autowires a bean named env for later use
	 * @since 1.0.0
	 */
	@Autowired
	  private Environment env;

	/**
	 * Creates a bean called dataSource that will be used for setting the data connection
	 * @since 1.0.0
	 * @return
	 */
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

	/**
	 * Creates a bean named transactionManager that will be used to manage database transactions
	 * @since 1.0.0
	 * @return
	 */
	@Bean(name = "transactionManager")
	  public DataSourceTransactionManager getTransactionManager() {
	      DataSourceTransactionManager txManager = new DataSourceTransactionManager();

	      DataSource dataSource = this.getDataSource();
	      txManager.setDataSource(dataSource);

	      return txManager;
	  }

	/**
	 * Creates a bean named custAspect. Without this there is no proxy connection to the CustomerAspect class
	 * and none of the concerns will execute.
	 * @since 1.0.1-AOP
 	 * @return
	 */
	@Bean(name = "custAspect")
      public CustomerAspect getCustomerAspect(){
	    CustomerAspect cAspect = new CustomerAspect();

	    return cAspect;
      }
}
