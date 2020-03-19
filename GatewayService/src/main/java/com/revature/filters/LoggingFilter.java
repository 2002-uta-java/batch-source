package com.revature.filters;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Service
public class LoggingFilter extends ZuulFilter {

	private Logger log = Logger.getLogger(LoggingFilter.class);

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		final RequestContext rc = RequestContext.getCurrentContext();
		final HttpServletRequest request = rc.getRequest();

		log.info(request.getMethod() + " amde to: " + request.getRequestURI());
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

}
