package com.logix.config;

import com.logix.filter.CORSFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * This class is responsible for configuring the Web Servlet for requests. I've included an optional CORS Filter here
 * as well. This is for some browsers that have trouble with RESTFUL requests.
 * @author Branden Boyington
 * @version
 * @since 1.0.0
 */
public class ServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { WebConfig.class };
	}
	
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}
	
	/*@Override // This filter isn't needed in most cases, but you can un-comment it if you need to use it.
	//protected Filter[] getServletFilters() {
		//return new Filter[] { new CORSFilter() };
	}*/
}
