package com.andreabazerla.person;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.andreabazerla.BaseServlet;
import com.andreabazerla.user.User;

public class Store extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void execute(HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		String action = request.getParameter("action");
		String feedback = null;
		PrintWriter printWriter = response.getWriter();

		if (action.equals("STORE")) {
			int id = 0;
			String cf = request.getParameter("cf");
			String name = request.getParameter("name");
			String surname = request.getParameter("surname");

			if ((cf != null && !cf.isEmpty() && cf.trim().length() > 0)
					&& (name != null && !name.isEmpty() && name.trim().length() > 0)
					&& (surname != null && !surname.isEmpty() && surname.trim()
							.length() > 0)) {
				
				List<Person> personList = new ArrayList<Person>();
				personList = getPersonDatabase().log(cf);
				
				if (personList.isEmpty()) {
					getPersonDatabase().save(new Person(id, cf, name, surname));
					feedback = "{ \"type\": \"0\", \"header\": \"Success! \", \"message\": \"Ok, user created\" }";
				} else {
					feedback = "{ \"type\": \"1\", \"header\": \"Error! \", \"message\": \"User already exist\" }";
				}
				
			} else {
				feedback = "{ \"type\": \"1\", \"header\": \"Error! \", \"message\": \"Empty fields\" }";
			}
			
		} else if (action.equals("UPDATE")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String cf = request.getParameter("cf");
			String name = request.getParameter("name");
			String surname = request.getParameter("surname");

			if ((name != null && !name.isEmpty())
					&& (surname != null && !surname.isEmpty())) {
				getPersonDatabase().delete(id);
				getPersonDatabase().save(new Person(id, cf, name, surname));
				feedback = "{ \"type\": \"0\", \"header\": \"Success! \", \"message\": \"Ok, user updated\" }";
			} else {
				feedback = "{ \"type\": \"1\", \"header\": \"Error! \", \"message\": \"Empty fields\" }";
			}
			
		} else if (action.equals("DELETE_PERSON")) {
			int id = Integer.parseInt(request.getParameter("id"));
			getPersonDatabase().delete(id);
			response.sendRedirect("List");
			
		} else if (action.equals("DELETE_USER")) {
			int id = Integer.parseInt(request.getParameter("id"));
			getUserDatabase().delete(id);
			response.sendRedirect("List");
			
		} else {
			String cf = request.getParameter("cf");
			Person person = null;
			person = (Person) getPersonDatabase().log(cf);
			request.setAttribute("person", person);
			request.getRequestDispatcher("personForm.jsp").forward(request,
					response);
		}
		
		response.setContentType("application/json;charset=utf-8");
		printWriter.print(feedback);
		printWriter.flush();
		printWriter.close();
	}
}
