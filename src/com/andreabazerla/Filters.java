//package com.andreabazerla;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//  
//public class Filters implements Filter {  
//	  
//	public void init(FilterConfig arg0) throws ServletException {}
//	
//	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//		
//		HttpServletRequest request = (HttpServletRequest) req;
//	    HttpServletResponse response = (HttpServletResponse) res;
//	    
//	    String appName = request.getContextPath();
//		String servletPath = request.getServletPath();
//		HttpSession session = request.getSession(false);
//		
//		if (servletPath.endsWith(".js")
//				|| servletPath.endsWith(".css")
//				|| servletPath.endsWith(".html")) {
//			chain.doFilter(request, response);
//		}
//
//		if (session.getAttribute("currentUser") != null) {
//			chain.doFilter(request, response);
//		} else {
//			if (servletPath.equals("/Login")
//				|| servletPath.equals("/Home")
//				|| servletPath.equals("/Profile")
//				|| servletPath.equals("/Logout")
//				|| servletPath.equals("/List")
//				|| servletPath.equals("/Store")) {
//				
//				response.sendRedirect(appName + "/Login");
//			}
//		}
//	}
//
//	@Override
//	public void destroy() {}
//}

package com.andreabazerla;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
  
public class Filters implements Filter {  
	  
	public void init(FilterConfig arg0) throws ServletException {}
	
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
	    HttpServletResponse response = (HttpServletResponse) res;
	    
	    String appName = request.getContextPath();
		String servletPath = request.getServletPath();
		String requestURI = request.getRequestURI();
		
		if (servletPath.endsWith(".js")
				|| servletPath.endsWith(".css")
				|| servletPath.endsWith(".html")) {
			chain.doFilter(request, response);
		}
		
		HttpSession session = request.getSession(false);

		if (session != null && session.getAttribute("currentUser") != null) {
			chain.doFilter(request, response);
		} else {
			if ((servletPath.equals("/Signup")) || (servletPath.equals("/Login"))) {
				chain.doFilter(request, response);
			} else {
				if (servletPath.equals("/Signup")) {
					response.sendRedirect(appName + "/Signup");
				} else if (servletPath.equals("/Login")
					|| servletPath.equals("/Home")
					|| servletPath.equals("/Users")
					|| servletPath.equals("/Profile")
					|| servletPath.equals("/Logout")
					|| servletPath.equals("/List")
					|| servletPath.equals("/Store")) {
					response.sendRedirect(appName + "/Login");
				}
			}
		}
	}

	@Override
	public void destroy() {}
}