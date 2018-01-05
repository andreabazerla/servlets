<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Test.jsp</title>
	</head>
	
	<body>
		
		<h1>Parametri</h1>
		
		<%
			Enumeration<?> parameterNames = request.getParameterNames();
			
			if (parameterNames.hasMoreElements()) {
		%>
				<table>
					<tbody>
					<thead>
						<tr><th>Key</th><th>Value</th></tr>
					</thead>
				<%
					while(parameterNames.hasMoreElements()) {
						String parameterName = (String) parameterNames.nextElement();
						String parameterValue = request.getParameter(parameterName);
				%>
						<tr><td><%=parameterName%></td><td><%=parameterValue%></td></tr>
				<%
					}
				%>
					</tbody>
				</table>
		<%
			}
		%>
		
			<h1>Attributi della Request</h1>
		
		<%			
			request.setAttribute("x", 0);
			request.setAttribute("y", 1);
			request.setAttribute("z", 2);
			
			Enumeration<?> attributeNames = request.getAttributeNames();
			
			int x = (Integer) request.getAttribute("x");
			int y = (Integer) request.getAttribute("y");
			int z = (Integer) request.getAttribute("z");
			
			if (attributeNames.hasMoreElements()) {
		%>
			
				<table>
					<tbody>
					<thead>
						<tr><th>Key</th><th>Value</th></tr>
					</thead>
		<%
					while(attributeNames.hasMoreElements()) {
						String attributeName = (String) attributeNames.nextElement();
						int attributeValue = (Integer) request.getAttribute(attributeName);
				%>
						<tr><td><%=attributeName%></td><td><%=attributeValue%></td></tr>
				<%
					}
				%>
					</tbody>
				</table>
		<%
			}
		%>
	
		<h1>Attributi della Session</h1>
		
		<%			
			session.setAttribute("xx", 0);
			session.setAttribute("yy", 1);
			session.setAttribute("zz", 2);
			
			Enumeration<?> attributeNamesSession = session.getAttributeNames();
			
			int xx = (Integer) session.getAttribute("xx");
			int yy = (Integer) session.getAttribute("yy");
			int zz = (Integer) session.getAttribute("zz");
			
			if (attributeNamesSession.hasMoreElements()) {
		%>
			
				<table>
					<tbody>
					<thead>
						<tr><th>Key</th><th>Value</th></tr>
					</thead>
		<%
					while(attributeNamesSession.hasMoreElements()) {
						String attributeName = (String) attributeNamesSession.nextElement();
						int attributeValue = (Integer) session.getAttribute(attributeName);
				%>
						<tr><td><%=attributeName%></td><td><%=attributeValue%></td></tr>
				<%
					}
				%>
					</tbody>
				</table>
		<%
			}
		%>
		
		<div>
			
			<%=xx%>/<%=yy%>/<%=zz%>
			
		</div>
	
	</body>
</html>