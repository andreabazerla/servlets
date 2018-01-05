package com.andreabazerla.user;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.andreabazerla.BaseServlet;
import com.andreabazerla.util.Email;

public class Edit extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	protected void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String action = request.getParameter("action");
		HttpSession httpSession = request.getSession();
		User user = (User) httpSession.getAttribute("currentUser");
		
		if (action != null && action.equals("EDIT_ADMIN")) {
			request.setAttribute("action", "EDIT_ADMIN");
			request.setAttribute("currentUser", user);
			request.getRequestDispatcher("/signup.jsp").forward(request, response);
	        return;
		}
		
		if (action != null && action.equals("EDIT_USER")) {
			request.setAttribute("action", "EDIT_USER");
			int id = Integer.parseInt(request.getParameter("idUser"));
			
			List<User> otherUserList = new ArrayList<User>();
			otherUserList = getUserDatabase().load(id);				
			User otherUser = null;
			
			if (!otherUserList.isEmpty()) {
				otherUser = otherUserList.get(0);
			}
			
			request.setAttribute("currentUser", otherUser);
			request.getRequestDispatcher("/signup.jsp").forward(request, response);
	        return;
		}

		if (action != null && action.equals("UPDATE")) {
			
			String feedback = null;
			PrintWriter printWriter = response.getWriter(); 
			
			int id = Integer.parseInt(request.getParameter("id"));
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
			String role = request.getParameter("role");
			
			if (role == null)
				if (button.equals("Update")) {
					role = user.getRole();
				} else {
					role = "USER";
				}
			else
				role = "ADMIN";
			
			if (!ifNull(name)
					&& !ifNull(surname)
					&& !ifNull(username)
					&& !ifNull(password)
					&& !ifNull(confirmation)
					&& !ifNull(email)
					&& !ifNull(region)
					&& (button.equals("Update") || button.equals("Manage"))) {

				List tempUser = new ArrayList<User>();
				if (!tempUser.isEmpty()) {
					tempUser = (List) getUserDatabase().load(id).get(0);				
				}

				if (tempUser == null) {
						feedback = "{ \"type\": \"0\", \"field\": \"username\", \"message\": \"User not equals\" }";
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
							request.setAttribute("action", "USER_UPDATED");
							request.getRequestDispatcher("Users").forward(request, response);
						}
						return;
					}
				}
			} else {
				request.setAttribute("error", "Empty field");
				request.getRequestDispatcher("/profile.jsp").forward(request, response);
			}
			
	        response.setContentType("application/json;charset=utf-8");
	        printWriter.print(feedback);
	        printWriter.flush();
	        printWriter.close();
	        return;
		}
		
		request.setAttribute("currentUser", user);
//		request.getRequestDispatcher("/Profile").forward(request, response);
	}

	public boolean ifNull(String string) {
		if (string != null && !string.isEmpty() && string.trim().length() > 0) {
			return false;
		} else {
			return true;
		}
	}
	
}
