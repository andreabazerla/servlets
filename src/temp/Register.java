package temp;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.andreabazerla.BaseServlet;
import com.andreabazerla.person.Person;

public class Register extends BaseServlet  {
	private static final long serialVersionUID = 1L;
	
	//Person person;
	
	
	
	@Override
	protected void execute(HttpServletRequest request, HttpServletResponse response) throws Exception
	{

		String cf = request.getParameter("cf");

		Person person = getPersonDatabase().getPerson(cf);
		
		request.setAttribute("person", person);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ID.jsp");
        dispatcher.forward(request, response);
        		
	}


		
}
