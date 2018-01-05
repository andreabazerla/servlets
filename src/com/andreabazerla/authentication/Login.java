package com.andreabazerla.authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.andreabazerla.BaseServlet;
import com.andreabazerla.database.IGenericDao;
import com.andreabazerla.database.sql.UserDao;
import com.andreabazerla.user.User;


public class Login extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	public void init() {
		IGenericDao<User> userDatabase = new UserDao();
		setUserDatabase(userDatabase);
	}
	
	protected void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getParameter("login") != null) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			if ((username != null && !username.isEmpty()) && (password != null && !password.isEmpty())) {
				User user = null;
				try {
					IGenericDao<User> userDao = new UserDao();
					user = userDao.log(username).get(0);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				if (user != null) {
					if (user.getPassword().equals(request.getParameter("password"))) {
						HttpSession session = request.getSession();
					    session.setAttribute("currentUser", user);
					    
						response.sendRedirect("Home");
						return;
					} else {
						request.setAttribute("error", "Wrong password");
						request.getRequestDispatcher("/login.jsp").forward(request, response);
					}
				} else {
					request.setAttribute("error", "User doesn't exist");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("error", "Empty field");
				request.getRequestDispatcher("/login.jsp").forward(request, response);			
			}
		} else {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}	
}
