package com.andreabazerla;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Profile extends BaseServlet {
	private static final long serialVersionUID = 1L;

	protected void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getRequestDispatcher("/profile.jsp").forward(request, response);		
	}
}
