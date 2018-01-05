package com.andreabazerla;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.andreabazerla.database.IGenericDao;
import com.andreabazerla.person.Person;
import com.andreabazerla.user.User;

public abstract class BaseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final String DATABASE_ATTRIBUTE_NAME = "database";
	private static final String DATABASE_USER_ATTRIBUTE_NAME = "databaseUser";

	protected abstract void execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	@Override
	protected final void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			execute(request, response);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected final void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			execute(request, response);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		catch(ServletException e)
		{
			throw e;
		}
		catch(IOException e)
		{
			throw e;
		}
		catch(Exception e)
		{
			throw new ServletException(e);
		}
	}
	
//	protected void setPersonDatabase(AbstractDatabase<Person> database) {
//		getServletContext().setAttribute(DATABASE_ATTRIBUTE_NAME, database);
//	}
	protected void setPersonDatabase(IGenericDao<Person> database) {
		getServletContext().setAttribute(DATABASE_ATTRIBUTE_NAME, database);
	}
	
//	protected AbstractDatabase<Person> getPersonDatabase() {
//		return (PersonDatabase)getServletContext().getAttribute(DATABASE_ATTRIBUTE_NAME);
//	}
	protected IGenericDao<Person> getPersonDatabase() {
		return (IGenericDao<Person>)getServletContext().getAttribute(DATABASE_ATTRIBUTE_NAME);
	}
	
//	protected void setUserDatabase(AbstractDatabase<User> database) {
//		getServletContext().setAttribute(DATABASE_USER_ATTRIBUTE_NAME, database);
//	}
	protected void setUserDatabase(IGenericDao<User> database) {
		getServletContext().setAttribute(DATABASE_USER_ATTRIBUTE_NAME, database);
	}
	
//	protected AbstractDatabase<User> getUserDatabase() {
//		return (UserDatabase)getServletContext().getAttribute(DATABASE_USER_ATTRIBUTE_NAME);
//	}
	protected IGenericDao<User> getUserDatabase() {
		return (IGenericDao<User>)getServletContext().getAttribute(DATABASE_USER_ATTRIBUTE_NAME);
	}
	
}
