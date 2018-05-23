package com.logix.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This isn't nessessary for a RESTFUL service, but it's purpose is to enable some browsers to work with json return.
 *
 * @author Branden Boyington
 * @since 1.0.0
 * @version ${version}
 */
public class CORSFilter extends OncePerRequestFilter{

	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
		LOG.info("Adding CORS Headers ........................ ");
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
		res.setHeader("Access-Control-Allow-Headers", "*");
		res.setHeader("Access-Control-Max-Age", "3600");
		chain.doFilter(req, res);
	}
}
