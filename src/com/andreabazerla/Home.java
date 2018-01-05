package com.andreabazerla;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.andreabazerla.database.IGenericDao;
import com.andreabazerla.database.sql.PersonDao;
import com.andreabazerla.person.Person;


public class Home extends BaseServlet {

	private static final long serialVersionUID = 1L;
		
	public void init() {
		IGenericDao<Person> database = new PersonDao();
		setPersonDatabase(database);
	}

	protected void execute(HttpServletRequest request, HttpServletResponse response) throws  Exception {
        request.getRequestDispatcher("List").forward(request, response);        
	}
	
}
