package temp;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.andreabazerla.BaseServlet;

public class Store extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	protected void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String cf = request.getParameter("cf").trim();
		String name = request.getParameter("name").trim();
	    String surname = request.getParameter("surname").trim();
	    
		if ((cf != null && !cf.isEmpty()) && (name != null && !name.isEmpty()) && (surname != null && !surname.isEmpty())) {
		    
		    ServletContext servletContext = getServletContext(); 
			Database database = (Database) servletContext.getAttribute("database");
			
			if (database.getPerson(cf) == null) {
			
				database.setPerson(cf, name, surname);
				
				response.sendRedirect("List");
				
			} else {
				
				request.setAttribute("error", "CF already exist");
				
		        request.getRequestDispatcher("List").forward(request, response);
				
			}
		
		} else {
			
			request.setAttribute("error", "Empty field");
			
	        request.getRequestDispatcher("List").forward(request, response);
			
		}
	}
}
