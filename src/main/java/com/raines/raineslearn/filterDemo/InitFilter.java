package com.raines.raineslearn.filterDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 过滤器demo
 *
 */
public class InitFilter implements Filter {
	
	private static Logger log = LoggerFactory.getLogger(InitFilter.class);
	
	private String[] excludes;
	
	public void init(FilterConfig filterConfig) throws ServletException {
		excludes = new String[]{".jpg,.png,.gif,.bmp,.doc,.xls,.docx,.xlsx,.ppt,.pptx,.js,.css,.avi,.mp4,.apk"};
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		if("user" == null) {
			log.debug("用户未登录!!!!!!");
			chain.doFilter(req, response);
			return;
		}

		String uri = req.getRequestURI();
		System.out.println(uri);
		for(String subfix : excludes) {
			if(uri.equalsIgnoreCase(subfix)) {
				chain.doFilter(req, response);
				return;
			}
		}
		chain.doFilter(req, response);
	}

	public void destroy() {
	}

}
