<%-- <%@page import="com.andreabazerla.Person"%> --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Register</title>
	</head>
	<body>
	
		<table>
		
<%-- 			<%
					com.andreabazerla.Person person = (com.andreabazerla.Person) request.getAttribute("person");
				%> --%>
			
			<thead>
				<tr><td>CF</td><td>Name</td><td>Surname</td></tr>
			</thead>
		
			<tbody>
				
				<tr>
				
					<td><c:out value="${person.cf}"></c:out></td>
					<td><c:out value="${person.name}"></c:out></td>
					<td><c:out value="${person.surname}"></c:out></td>
					
					<%-- <td><%=person.getCf()%></td><td><%=person.getName()%></td><td><%=person.getSurname()%></td> --%>
					<%-- <td>${person.cf}</td><td>${person.name}</td><td>${person.surname}</td> --%>
					
				</tr>
				
			</tbody>
		
		</table>
		
		<a href="newPerson.jsp">New</a>
	
	</body>
</html>