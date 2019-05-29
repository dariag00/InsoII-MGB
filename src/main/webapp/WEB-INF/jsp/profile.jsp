<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Profile</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link href="<c:url value="/resources/styles.css"/>" rel="stylesheet"></link>
	<link href="<c:url value="/resources/parsley.css"/>" rel="stylesheet"></link>
	<!--<script src="<c:url value="/resources/js/bootstrap.js"/>"></script>
	<link href="<c:url value="/resources/css/bootstrap.css"/>" rel="stylesheet"></link>-->
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/chart.js@2.7.2/dist/Chart.bundle.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	<script src="<c:url value="/resources/parsley.js"/>"></script>
	<script src="<c:url value="/resources/script.js"/>"></script>
</head>


<body>

	<%@ include file="main_navbar.jsp" %>
		<div class="container text-" id="profileContainer">
			<div class="row summaryContainer">
				<div class="col-md-8">
					<c:if test="${not empty errorMessage}">
						<div class="alert alert-danger" role="alert">
		 						${errorMessage}
						</div>
						</c:if>
						<c:if test="${not empty successMessage}">
						<div class="alert alert-primary" role="alert">
		 						${successMessage}
						</div>
					</c:if>
					<h4>Saludos ${user.nombre}, este es tu perfil.</h4>
				</div>
				
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="card shadow-sm" id="profileCointainer">
						<div class="card-body">
							<div class="row">
								<div class="col-md-6">
									<h5 class="card-title text-primary">Perfil</h5>
									<div class="row">
										<div class="col-md-3">
											<img src="https://img.icons8.com/color/48/000000/user.png">
										</div>
										<div class="col-md-9" >
											<p>Nombre: ${user.getNombre()}</p>
											<p>Apellidos: ${user.getApellidos()}</p>
											<p>DNI: ${user.getDni()}</p>
											<p>Email: ${user.getEmail()}</p>
											<p>Fecha de Nacimiento: ${user.getBirthdate()}</p>
											<p>Ciudad: ${user.getCity()}</p>
											<p>Dirección: ${user.getAddress()}</p>
											
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<button class="btn btn-primary accountButton" data-toggle="modal" data-target="#editProfile">Gestionar Perfil</button>
									<!-- TODO --><button class="btn btn-danger accountButton" data-toggle="modal" data-target="#deleteUserModal">Darme de baja</button> 
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>	
		<div class="modal fade" id="editProfile" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			  <div class="modal-dialog modal-lg" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title" id="exampleModalLabel">Editar perfil</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
		      	  <form:form method="POST" id="editUserForm" action="/editUser" modelAttribute="editUser" data-parsley-validate="">
			      	<div class="modal-body">
						<h4>Concreta los detalles de la operación</h4>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
								  <form:label path="name" for="input_name">Name</form:label>
								  <form:input path="name" type="text" class="form-control" data-parsley-required="true" id="input_name" aria-describedby="emailHelp" value="${user.nombre }"/>
								</div>
								<div class="form-group">
								  <form:label path="surname" for="input_surname">Surname</form:label>
								  <form:input path="surname" type="text" data-parsley-required="true" class="form-control" id="input_surname" value="${user.apellidos }"/>
								</div>	
								<div class="form-group">
								  <form:label path="country" for="input_country">Country</form:label>
								  <form:input path="country" type="text" class="form-control" id="input_country" value="${user.country }"/>
								</div>				
							</div>
							<div class="col-md-6">
								<div class="form-group">
								  <form:label path="address" for="input_address">Address</form:label>
								  <form:input path="address" type="text" class="form-control" id="input_address" value="${user.address }"/>
								</div>
								<div class="form-group">
								  <form:label path="city" for="input_city">City</form:label>
								  <form:input path="city" type="text" class="form-control" id="input_city" value="${user.city }"/>
								</div>
								
							</div>
							
						</div>
						<h5>Account Information</h5>
						<div class="row">
							<div class="col-md-6">
								
								<div class="form-group">
								  <form:label path="oldPassword" for="input_oldPassword">Password antigua</form:label>
								  <form:input path="oldPassword" data-parsley-required="true" type="password" class="form-control" id="input_oldPassword" placeholder= "" data-parsley-minlength="8"/>
								</div>
								<div class="form-group">
								  <form:label path="password" for="input_password">Password nueva</form:label>
								  <form:input path="password" data-parsley-required="true" type="password" class="form-control" id="input_password" placeholder="" data-parsley-minlength="8"/>
								</div>
								<div class="form-group">
								  <form:label path="confirmPassword" for="input_confirm_password">Confirma Password nueva</form:label>
								  <form:input path="confirmPassword" data-parsley-required="true" type="password" class="form-control" id="input_confirm_password" placeholder="" data-parsley-minlength="8"/>
								</div>
							</div>
							
							
						</div>
					
			      </div>
			      	<div class="modal-footer">
				        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				        <button type="submit" class="btn btn-primary">Send</button>
			      	</div>
			      </form:form>
			    </div>
			  </div>
			</div>
			
			
			<div class="modal fade" id="deleteUserModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
			  <div class="modal-dialog modal-dialog-centered" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title" id="exampleModalLongTitle">Delete Account</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body">
			       <p>¿Estás seguro de que quieres borrar este usuario? </p>
			       <small>Perderas tus cuentas, tarjetas y todo tu dinero.</small>
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			        <a class="btn btn-danger anchorBs" href="/deleteUser?userId=${user.id}">Delete</a>
			      </div>
			    </div>
			  </div>
			</div>	
		<script type="text/javascript">
		$('#editProfile').parsley();
	<%@ include file="footer.jsp" %>
</body>

</html>