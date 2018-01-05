package com.andreabazerla.authentication;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.andreabazerla.BaseServlet;
import com.andreabazerla.database.IGenericDao;
import com.andreabazerla.database.sql.UserDao;
import com.andreabazerla.person.Person;
import com.andreabazerla.user.User;
import com.andreabazerla.util.Email;

public class Signup extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	public void init() {
		IGenericDao<User> userDatabase = new UserDao();
		setUserDatabase(userDatabase);
	}
    
	@Override
	protected void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String action = request.getParameter("action");
		
		if (action != null && action.equals("CHECK")) {
			HttpSession httpSession = request.getSession();
			User user = (User) httpSession.getAttribute("currentUser");
			
			String username = request.getParameter("username");
			
			List<User> userList = new ArrayList<User>();
			userList = getUserDatabase().log(username);
			User tempUser = null;
			
			if (!userList.isEmpty()) {
				tempUser = userList.get(0);
			}
			
			String feedback = null;
			if (tempUser != null) {
				feedback = "{ \"type\": \"1\", \"header\": \"Error! \", \"message\": \"Error: username already exists\" }";
			} else {
				feedback = "{ \"type\": \"0\", \"header\": \"Success! \", \"message\": \"Ok\" }";
			}
	        response.setContentType("application/json;charset=utf-8");
			PrintWriter printWriter = response.getWriter();
			printWriter.print(feedback);
	        printWriter.flush();
	        printWriter.close();
	        return;
		}	

		if (action != null && action.equals("SIGNUP")) {
			HttpSession httpSession = request.getSession();
			User user = (User) httpSession.getAttribute("currentUser");
			
			String feedback = null;
			PrintWriter printWriter = response.getWriter(); 
			
			int id = 0;
			String name = request.getParameter("name");
			String surname = request.getParameter("surname");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String confirmation = request.getParameter("confirmation");
			String email = request.getParameter("email");
			String birthday = request.getParameter("birthday");
			String sex = request.getParameter("sex");
			String region = request.getParameter("region");
			String button = request.getParameter("button");
			String terms = null;
			String role = request.getParameter("role");
			
			if (role == null)
				if (button.equals("Update")) {
					role = user.getRole();
				} else {
					role = "USER";
				}
			else
				role = "ADMIN";
			
			if (button.equals("Signup")) {
				terms = request.getParameter("terms");
			}
			
			if (!ifNull(name)
					&& !ifNull(surname)
					&& !ifNull(username)
					&& !ifNull(password)
					&& !ifNull(confirmation)
					&& !ifNull(email)
					&& !ifNull(region)
					&& (button.equals("Update") || button.equals("Manage"))
						|| terms.equals("on")) {
								
				List<User> userList = new ArrayList<User>();
				userList = getUserDatabase().load(id);				
				User tempUser = null;
				
				if (!userList.isEmpty()) {
					tempUser = userList.get(0);
				}

				if (tempUser == null) {
					if (button.equals("Signup")) {
						feedback = "{ \"type\": \"1\", \"message\": \"Ok user signup\" }";
						tempUser = new User(id, name, surname, username, password, email, birthday, sex, region, role);
						getUserDatabase().save(tempUser);
						request.setAttribute("currentUser", tempUser);
						response.sendRedirect("Login");
						return;
					} else {
						feedback = "{ \"type\": \"0\", \"field\": \"username\", \"message\": \"User not equals\" }";
					}
				} else {
					if (button.equals("Signup")) {
						feedback = "{ \"type\": \"0\", \"field\": \"username\", \"message\": \"User already exist\" }";
					} else {
						
						if (!password.equals(confirmation)) {
							feedback = "{ \"type\": \"0\", \"field\": \"confirmation\", \"message\": \"Passwords not equals\" }";
						} else if (!Email.validate(email)) {
							feedback = "{ \"type\": \"0\", \"field\": \"email\", \"message\": \"Email not valid\" }";
						} else {
							feedback = "{ \"type\": \"1\", \"message\": \"Ok user updated\" }";
							
							getUserDatabase().delete(id);
							
							user = new User(id, name, surname, username, password, email, birthday, sex, region, role);
							
							getUserDatabase().save(user);

							if (button.equals("Update")) {								
								request.getSession().setAttribute("currentUser", user);
								request.getRequestDispatcher("Profile").forward(request, response);
							} else if (button.equals("Manage")) {
								request.getRequestDispatcher("List").forward(request, response);
							}
							return;
						}
					}
				}
			} else {
				request.setAttribute("error", "Empty field");
				request.getRequestDispatcher("/signup.jsp").forward(request, response);
			}
			
	        response.setContentType("application/json;charset=utf-8");
	        printWriter.print(feedback);
	        printWriter.flush();
	        printWriter.close();
	        return;
		} else {
			request.getRequestDispatcher("/signup.jsp").forward(request, response);
		}

	}

	public boolean ifNull(String string) {
		if (string != null && !string.isEmpty() && string.trim().length() > 0) {
			return false;
		} else {
			return true;
		}
	}
	
}
