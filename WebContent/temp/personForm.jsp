<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Store</title>
	
		<link rel="stylesheet"
			href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script
			src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</head>

	<body>
	
		<div class="container">
			<div class="col-md-4 col-md-offset-4">
				<h1>
					<c:choose>
						<c:when test="${empty person.cf}">
						    	Store
						    </c:when>
						<c:otherwise>
						    	Update
						    </c:otherwise>
					</c:choose>
				</h1>
				<form action="Store" method="POST">
	
					<table class="table">
						<tr>
							<td>CF:</td>
							<td>
								<input class="form-control" name="cf"
									value="<c:out value='${person.cf}'></c:out>"
									<c:choose>
									    <c:when test="${!empty person.cf}">
									    	readonly="readonly"
									    </c:when>
									</c:choose>
								required />
							</td>
						</tr>
						<tr>
							<td>Name:</td>
							<td>
								<input class="form-control" name="name"
								value="<c:out value='${person.name}'></c:out>" required />
							</td>
						</tr>
						<tr>
							<td>Surname:</td>
							<td>
								<input class="form-control" name="surname"
									value="<c:out value='${person.surname}'></c:out>" required />
							</td>
						</tr>
						<input type="hidden" name="action" value="
							<c:choose>
								<c:when test="${empty person.cf}">
							    	STORE
						    	</c:when>
								<c:otherwise>
							    	UPDATE
							    </c:otherwise>
							</c:choose>"
						/>
					</table>
					<input class="btn btn-primary" type="submit"
						value="Store">
				</form>
			</div>
		</div>
	</body>
</html>