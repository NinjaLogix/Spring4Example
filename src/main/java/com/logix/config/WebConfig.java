package com.logix.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * This is just a simple setup class that enables the web mvc. It does a simple component scan to get any missing beans
 * and enables the web mvc for this app to use.
 * @Author Branden Boyington
 * @version
 * @since 1.0.0
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "com.logix")
public class WebConfig extends WebMvcConfigurerAdapter{

}
