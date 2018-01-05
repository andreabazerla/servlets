package temp;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private final String message = "Hello World";

	public void init() throws ServletException {}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
		PrintWriter out = response.getWriter();
		
		Enumeration<?> parameters = request.getParameterNames();
		
		out.println("<table border='1'>");
	    while (parameters.hasMoreElements()) {
	    	String parameterName = (String) parameters.nextElement();
	    	String parameter = (String) request.getParameter(parameterName);
	    	
	    	out.println("<tr>");
				out.println("<td>");
					out.println("<h1>" + parameterName + "</h1>");
				out.println("</td>");
				out.println("<td>");
					out.println("<h2>" + parameter + "</h2>");
				out.println("</td>");
			out.println("</tr>");
	    }
	    
		out.print("</table>");
				
//		Map<String,Object> parameters = request.getParameterMap();		
//		Iterator<String> iterator = parameters.keySet().iterator();
//		
//		out.println("<table border='1'>");		
//	    while (iterator.hasNext()) {
//	        String key = (String) iterator.next();
//	        String value = ((String[]) parameters.get(key))[0];
//	        
//	        out.println("<tr>");
//				out.println("<td>");
//					out.println("<h1>" + key + "</h1>");
//				out.println("</td>");
//				out.println("<td>");
//					out.println("<h2>" + value + "</h2>");
//				out.println("</td>");
//			out.println("</tr>");
//	    }		
//		out.print("</table>");
		
	    // Session's attributes
		
		HttpSession session = request.getSession();		
		Enumeration<?> sessionAttributes = session.getAttributeNames();
		
	    session.setAttribute("x", 0);
	    session.setAttribute("y", 1);
	    session.setAttribute("z", 2);
//	    session.setAttribute("k", 3);
	    
		out.println("<table border='1'>");
	    while(sessionAttributes.hasMoreElements()) {
			out.println("<tr>");
				out.println("<td>");
					out.println(session.getAttribute((String) sessionAttributes.nextElement()));
				out.println("</td>");
			out.println("</tr>");
		}
		out.println("</table>");
	    
		Enumeration<?> requestAttributes = request.getAttributeNames();
		
		request.setAttribute("x", 0);
		request.setAttribute("y", 1);
		request.setAttribute("z", 2);
		
		out.println("<table border='1'>");
	    while(requestAttributes.hasMoreElements()) {
			out.println("<tr>");
				out.println("<td>c");
					out.println(request.getAttribute((String) requestAttributes.nextElement()));
				out.println("</td>");
			out.println("</tr>");
		}
		out.println("</table>");
		
	    // Hello World
						
		String sender = message;
		String nome = request.getParameter("nome");

		if (nome != null && nome.length() > 0)
			sender += " da " + nome ;
		
		response.setContentType("text/html");
		out.println("<h1>" + sender + "</h1>");
	}

	public void destroy() {
	}
}