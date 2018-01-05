<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Home</title>
		
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
		<link rel="stylesheet" type="text/css" href="style.css">
	
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
		
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
	
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		
		<link rel="stylesheet" type="text/css" href="style.css">
		
		<script type="text/javascript">
			$(document).ready(function() {
				
				$("#navbar").load("navbar.jsp");
				
				$("#storePerson").click(function() {
				    $.post("Store",
				    {
				        cf: $('#cf').val(),
				        name: $('#name').val(),
				        surname: $('#surname').val(),
				        action: "STORE"
				    },
				    function(data, success) {
						if (data.type == "0") {
							$("#feedbackStore").addClass("alert-success");
							$("#feedbackStore").removeClass("alert-danger");
						} else if (data.type == "1") {
							$("#feedbackStore").addClass("alert-danger");
							$("#feedbackStore").removeClass("alert-success");
						}
				    	$("#feedbackStore").html("<strong>" + data.header + "</strong>" + data.message);
				    	$("#feedbackStore").show();
				    });
				});
				
				$("#updatePerson").click(function() {
				    $.post("Store",
				    {
				    	id: $('#idPerson').val(),
				        cf: $('#cf2').val(),
				        name: $('#name2').val(),
				        surname: $('#surname2').val(),
				        action: "UPDATE"
				    },
				    function(data, success) {
				    	if (data.type == "0") {
							$("#feedbackUpdate").addClass("alert-success");
							$("#feedbackUpdate").removeClass("alert-danger");
						} else if (data.type == "1") {
							$("#feedbackUpdate").addClass("alert-danger");
							$("#feedbackUpdate").removeClass("alert-success");
						}
				    	$("#feedbackUpdate").html("<strong>" + data.header + "</strong>" + data.message);
				    	$("#feedbackUpdate").show();
				    });
				});
				
				$(".closeRefresh").click(function() {
					location.reload();
				});
				
				$(".buttonUpdatePerson").click(function() {
					$("#idPerson").val($(".buttonUpdatePerson").parent().children(":first-child").val());
				    $("#cf2").val($(this).parents().siblings("td:nth-child(1)").html());
				    $("#name2").val($(this).parents().siblings("td:nth-child(2)").html());
				    $("#surname2").val($(this).parents().siblings("td:nth-child(3)").html());
				});
				
				$(".deletePerson").click(function() {
					var id = $(this).parent().children().find("input:hidden[name=idPerson]").val();
					 $.post("Store",
				    {
				        id: id,
				        action: "DELETE_PERSON"
				    },
				    function(data, success) {
						location.reload();
				    });
				});
				
				$(".deleteUser").click(function() {
					var id = $(this).parent().children().find("input:hidden[name=idUser]").val();
					console.log(id);
					
					 $.post("Store",
				    {
						id: id,
				        action: "DELETE_USER"
				    },
				    function(data, success) {
						location.reload();
				    });
				});
				
			});			
		</script>
	
	</head>
	
	<body id="body">
		
		<div id="navbar"></div>
		
		<div class="container">			
			<div class="col-md-8 col-md-offset-2 p-0">
				<c:choose>
					<c:when test="${pattern != null}">
						<h5>Search: "<b><c:out value="${pattern}"></c:out></b>"</h5>
					</c:when>				
					<c:when test="${empty users}">
						<button id="buttonNewPerson" type="button" data-toggle="modal"
							data-target="#newPerson" class="btn btn-primary">New</button><br>
					</c:when>					
				</c:choose>
				<div class="modal fade" id="newPerson" tabindex="-1" role="dialog"
					aria-labelledby="exampleModalLabel" aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">Store</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<div id="feedbackStore" class="alert" role="alert" style="display: none">
								</div>
								<table class="table">
									<tr>
										<td>CF:</td>
										<td><input id="cf" class="form-control" name="cf" required />
										</td>
									</tr>
									<tr>
										<td>Name:</td>
										<td><input id="name" class="form-control" name="name"
											required /></td>
									</tr>
									<tr>
										<td>Surname:</td>
										<td><input id="surname" class="form-control" name="surname"
											required /></td>
									</tr>
								</table>
							</div>
							<div class="modal-footer">
								<button id="storePerson" type="button" class="btn btn-primary">Store</button>
								<button type="button" class="closeRefresh btn btn-secondary"
									data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
		
				<div class="modal fade" id="updatePersonModal" tabindex="-1"
					role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">Update</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<div id="feedbackUpdate" class="alert" role="alert" style="display: none">
								</div>
								<table class="table">
									<tr>
										<td>CF:</td>
										<td><input id="cf2" class="form-control" name="cf" required
											readonly /></td>
									</tr>
									<tr>
										<td>Name:</td>
										<td><input id="name2" class="form-control" name="name"
											required /></td>
									</tr>
									<tr>
										<td>Surname:</td>
										<td><input id="surname2" class="form-control"
											name="surname" required /></td>
									</tr>
								</table>
							</div>
							<div class="modal-footer">
								<input type="hidden" name="idPerson" id="idPerson">
								<button id="updatePerson" type="button" class="btn btn-primary">Update</button>
								<button type="button" class="closeRefresh btn btn-secondary"
									data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
		
				<br>
				<table class="table">
						<c:choose>
							<c:when test="${people != null}">
								<thead>
									<tr>
										<th>CF</th>
										<th>Name</th>
										<th>Surname</th>
									</tr>
								</thead>
					
								<tbody id="personList">
								<c:forEach var="person" items="${people}">
									<tr>
				
										<td><c:out value="${person.cf}"/></td>
										<td><c:out value="${person.name}"/></td>
										<td><c:out value="${person.surname}"/></td>
				
										<td class="updateForm">
											<div class="dropdown">
												<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
											    	Actions
											  	</button>
											  	<div class="dropdown-menu" aria-labelledby="dropdownMenu2">
											    	<form action="Store" method="POST" style="margin: 0">
														<input
															type="hidden" name="idPerson"
															value='<c:out value="${person.id}"></c:out>'>
														<input
															type="hidden" name="action"
															value='<c:out value="EDIT"></c:out>'>
														<button type="button" data-toggle="modal"
															data-target="#updatePersonModal"
															class="buttonUpdatePerson dropdown-item">Edit</button>
													</form>
													<button type="button" class="deletePerson dropdown-item">Delete</button>
											  	</div>
											</div>
										<td>
									</tr>
								</c:forEach>
							</c:when>
							<c:when test="${users != null}">
								<thead>
									<tr>
										<th>Username</th>
										<th>Password</th>
										<th>E-mail</th>
									</tr>
								</thead>
					
								<tbody id="personList">
								<c:forEach var="userList" items="${users}" >
									<c:if test="${userList.username != currentUser.username}">
										<tr>
					
											<td><c:out value="${userList.username}"/></td>
											<td><c:out value="${userList.password}"/></td>
											<td><c:out value="${userList.email}"/></td>
					
											<td class="updateForm">
												<div class="dropdown">
													<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
												    	Actions
												  	</button>
												  	<div class="dropdown-menu" aria-labelledby="dropdownMenu2">
												    	<form action="Edit" method="POST" style="margin: 0">
												    		<input
																type="hidden" name="idUser"
																value='<c:out value="${userList.id}"></c:out>'>
															<input type="hidden" name="otherUsername"
																value='<c:out value="${userList.id}"></c:out>'> <input
																type="hidden" name="action"
																value='<c:out value="EDIT_USER"></c:out>'>
															<button type="submit"
																class="dropdown-item">Edit</button>
														</form>
														<button type="button" class="deleteUser dropdown-item">Delete</button>
												  	</div>
												</div>
											<td>
										</tr>
									</c:if>
								</c:forEach>
							</c:when>
						</c:choose>
					</tbody>
				</table>
			</div>
		</div>
	
	</body>

</html>