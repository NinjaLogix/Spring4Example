package com.logix.config;

import javax.sql.DataSource;

import java.beans.PropertyVetoException;
import java.util.Properties;

import com.logix.aop.CustomerAspect;
import org.hibernate.SessionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * Hibernate java style config setup. Followed this article
 * @see <a href="http://www.baeldung.com/hibernate-4-spring">baeldung.com</a>
 * Main Hibernate article followed
 * @see <a href="https://myjourneyonjava.blogspot.com/2015/09/spring-4-mvc-hibernate-4-mysql-5-maven.html">myjourneyonjava.blogspot.com</a>
 *
 * @author bboyingt
 * @version ${version}
 * @since 1.0.0
 */
@Configuration
@EnableTransactionManagement
@EnableAspectJAutoProxy
@PropertySources({ @PropertySource("classpath:dataSource/datasource-cfg.properties") })
@ComponentScan({ "com.logix.*" })
public class ApplicationContextConfig {
	private final Logger log = LoggerFactory.getLogger(ApplicationContextConfig.class);

	@Autowired
	private Environment env;

	@Bean
	public LocalSessionFactoryBean sessionFactory(){
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(restDataSource());
		sessionFactory.setPackagesToScan(new String[]{ "com.logix.model"});
		sessionFactory.setHibernateProperties(hibernateProperties());
		sessionFactory.setPackagesToScan("com.logix.model");
		//sessionFactory.setMappingResources("com.logix.model.Customer");

		return sessionFactory;
	}

	@Bean
	public DataSource restDataSource() {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		try{
			dataSource.setDriverClass(env.getProperty("dataSource.database-driver"));
			dataSource.setJdbcUrl(env.getProperty("dataSource.url"));
			dataSource.setUser(env.getProperty("dataSource.username"));
			dataSource.setPassword(env.getProperty("dataSource.password"));
			dataSource.setAcquireIncrement(Integer.valueOf(env.getProperty("connection.acquireIncrement")));
			dataSource.setMinPoolSize(Integer.valueOf(env.getProperty("connection.minPoolSize")));
			dataSource.setMaxPoolSize(Integer.valueOf(env.getProperty("connection.maxPoolSize")));
			dataSource.setMaxIdleTime(Integer.valueOf(env.getProperty("connection.maxIdleTime")));
		}catch (PropertyVetoException e) {
			log.info("PropertyVetoException Caught: ", e.toString()); //TODO - need to change this to something along the lines of e.printstack
		}

		return dataSource;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory){
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);

		return txManager;
	}

	Properties hibernateProperties(){
		return new Properties(){
			{
				setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
				setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
				setProperty("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
				setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
			}
		};
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
