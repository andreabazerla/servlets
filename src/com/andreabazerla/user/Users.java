package com.andreabazerla.user;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.andreabazerla.BaseServlet;

public class Users extends BaseServlet {
	private static final long serialVersionUID = 1L;

	protected void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    String action = (String) request.getAttribute("action");
		
	    if (action != null && action.equals("USER_UPDATED")) {
	    	ArrayList<User> users = new ArrayList<User>();
	    	users = (ArrayList<User>) getUserDatabase().getAll("");
	    	
	    	request.setAttribute("users", users);
	    	
	    	request.getRequestDispatcher("/list.jsp").forward(request, response);
	    } else {	    	
	    	ArrayList<User> users = new ArrayList<User>();
	    	users = (ArrayList<User>) getUserDatabase().getAll("");
	    	
	    	request.setAttribute("users", users);
	    	
	    	request.getRequestDispatcher("/list.jsp").forward(request, response);	
	    }
	}
}
