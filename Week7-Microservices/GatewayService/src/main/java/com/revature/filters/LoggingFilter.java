package com.revature.filters;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class LoggingFilter extends ZuulFilter{
	
	private Logger log = Logger.getLogger(LoggingFilter.class);

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext rc = RequestContext.getCurrentContext();
		HttpServletRequest request = rc.getRequest();
		log.info(request.getMethod()+ " made to: "+request.getRequestURI());
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
