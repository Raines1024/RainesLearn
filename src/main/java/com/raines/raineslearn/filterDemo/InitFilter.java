package com.raines.raineslearn.filterDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
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
		//若当前session中存在账号，则将账号对应的操作机构也保存至session,方便以后查询
		HttpServletRequest req = (HttpServletRequest)request;
		if("user" == null) {
			log.debug("用户未登录!!!!!!");
			//response.sendRedirect("login");//未登录，可以重定向到登录页面
			chain.doFilter(req, response);
			return;
		}
		String uri = req.getRequestURI();
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
