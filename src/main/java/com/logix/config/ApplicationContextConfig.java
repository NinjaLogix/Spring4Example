package com.logix.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.ComponentScan;

import org.hibernate.ejb.HibernatePersistence;

import javax.sql.DataSource;

/**
 * @author bboyingt
 * @version ${version}
 * @since 1.0.0
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.logix.persistence.dao")
@EnableAspectJAutoProxy
@PropertySources({ @PropertySource("classpath:application.properties")})
public class ApplicationContextConfig {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private Environment env;

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
		LocalContainerEntityManagerFactoryBean lemfb = new LocalContainerEntityManagerFactoryBean();
		lemfb.setDataSource(dataSource());
		lemfb.setPersistenceProviderClass(HibernatePersistence.class);
		lemfb.setPackagesToScan("com.logix.model");
		lemfb.setJpaProperties(hibernateProperties());

		return lemfb;
	}

	/**
	 * Switched from using C3P0 for connection pooling here just to simplify things. Normally you would want
	 * a connection pool though to manage a large number of connections.
	 * @return
	 */
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setUrl(env.getProperty("dataSource.url"));
		ds.setUsername(env.getProperty("dataSource.username"));
		ds.setPassword(env.getProperty("dataSource.password"));
		ds.setDriverClassName(env.getProperty("dataSource.database-driver"));

		return ds;
	}

	@Bean
	JpaTransactionManager transactionManager(){
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
		return jpaTransactionManager;
	}

	private Properties hibernateProperties(){
		return new Properties(){
			{
				setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
				setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
				setProperty("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
				setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
			}
		};
	}
}
