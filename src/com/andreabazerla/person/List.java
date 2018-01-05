package com.andreabazerla.person;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.andreabazerla.BaseServlet;

public class List extends BaseServlet {
	private static final long serialVersionUID = 1L;

	protected void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    		
		String action = (String) request.getParameter("action");
		String search = null;
		
		ArrayList<Person> people = new ArrayList<Person>();			

			if (action != null && action.equals("SEARCH")) {
				search = request.getParameter("search");
				people = (ArrayList<Person>) getPersonDatabase().getAll(search);
				request.setAttribute("pattern", search);
			} else {
				people = (ArrayList<Person>) getPersonDatabase().getAll("");
			}
		
		request.setAttribute("people", people);
		
        request.getRequestDispatcher("/list.jsp").forward(request, response);
	}
}
