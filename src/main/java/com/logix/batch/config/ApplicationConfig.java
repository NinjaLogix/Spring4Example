package com.logix.batch.config;

import org.apache.log4j.Logger;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.PropertySources;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import org.springframework.core.env.Environment;

@Configuration
@ComponentScan("com.logix.batch.*")
@PropertySources({ @PropertySource("classpath:application.properties")})
public class ApplicationConfig {
    private final Logger log = Logger.getLogger(getClass());

    @Autowired
    public Environment env;

    /* The DriverManagerDataSource object works well with MySQL. There are multiple ways to
     * setup a datasource object including allowing spring boot to automatically setup
     * one for you. */
    @Bean
    public DriverManagerDataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("datasource.driver-class-name"));
        dataSource.setUsername(env.getProperty("datasource.username"));
        dataSource.setPassword(env.getProperty("datasource.password"));
        dataSource.setUrl(env.getProperty("datasource.url"));

        return dataSource;
    }
}
