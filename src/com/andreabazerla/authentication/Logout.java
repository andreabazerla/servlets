package com.andreabazerla.authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.andreabazerla.BaseServlet;

public class Logout extends BaseServlet {
	private static final long serialVersionUID = 1L;

	protected void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		session.invalidate();
		response.sendRedirect("Login");		
	}		
}
