<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Login</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link href="<c:url value="/resources/styles.css"/>" rel="stylesheet"></link>
	<link href="<c:url value="/resources/parsley.css"/>" rel="stylesheet"></link>
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="<c:url value="/resources/script.js"/>"></script>
	<script src="<c:url value="/resources/parsley.js"/>"></script>
	<script src="<c:url value="/resources/es.js"/>"></script>
	<!--<script src="<c:url value="/resources/js/bootstrap.js"/>"></script>
	<link href="<c:url value="/resources/css/bootstrap.css"/>" rel="stylesheet"></link>-->
</head>


<body class="background-blue">
	<div class="jumbotron jumbotronBlue vertical-center">
		<div class="container text-">
			<div class="card" id="options-container">
				<div class="card-body">
					<c:if test="${not empty errorMessage}">
						<div class="alert alert-danger" role="alert">
	  						${errorMessage}
						</div>
					</c:if>
					<h5>¿Qué quieres hacer?</h5>
					<div class="row">
						<div class="col-md-6">
							<button type="submit" id="new-account-button"class="btn btn-primary totalButton">Crear una cuenta nueva</button>
						</div>
						<div class="col-md-6">
							<button type="submit" id="existing-account-button" class="btn btn-primary totalButton">Unirme a una cuenta ya creada</button>
						</div>
					</div>
				</div>
			</div>
			
			<div class="card d-none" id="form-container">
				<div class="img-container">
					<img class="card-img-top top-img" src="https://cdn.europosters.eu/image/750/wall-murals/classic-new-york-368x254-cm-premium-non-woven-wallpaper-130gsm-i55784.jpg" alt="Card image cap">
				</div>
				<div class="card-body">
					<form:form method="POST" id="createAccountForm" action="/addUser" modelAttribute="user" data-parsley-validate="">
						
						<h5>Informacion Personal </h5>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
								  <form:label path="name" for="input_name">Nombre</form:label>
								  <form:input path="name" type="text" class="form-control" data-parsley-required="true" id="input_name" aria-describedby="emailHelp" placeholder="Introduce tu nombre"/>
								</div>
								<div class="form-group">
								  <form:label path="surname" for="input_surname">Apellidos</form:label>
								  <form:input path="surname" type="text" data-parsley-required="true" class="form-control" id="input_surname" placeholder="Introduce tus apellidos"/>
								</div>
								<div class="form-group">
								  <form:label path="id" for="input_id">DNI</form:label>
								  <form:input path="id" type="text" data-parsley-required="true" class="form-control" id="input_id" placeholder="Introduce tu DNI"/>
								</div>
								<div class="form-group">
								  <form:label path="birthdate" for="input_birthdate">Fecha de nacimiento</form:label>
								  <form:input path="birthdate" type="text" data-parsley-required="true" class="form-control" id="input_birthdate" placeholder="Formato: 1990-12-31"/>
								</div>
								
							</div>
							<div class="col-md-6">
								<div class="form-group">
								  <form:label path="address" for="input_address">Dirección</form:label>
								  <form:input path="address" type="text" class="form-control" id="input_address" placeholder="Introduce tu dirección"/>
								</div>
								<div class="form-group">
								  <form:label path="postalCode" for="input_postal_code">Código postal</form:label>
								  <form:input path="postalCode" type="text" class="form-control" id="input_postal_code" placeholder="Introduce tu código postal"/>
								</div>
								<div class="form-group">
								  <form:label path="city" for="input_city">Ciudad</form:label>
								  <form:input path="city" type="text" class="form-control" id="input_city" placeholder="Introduce tu ciudad"/>
								</div>
								<div class="form-group">
								  <form:label path="country" for="input_country">País</form:label>
								  <form:input path="country" type="text" class="form-control" id="input_country" placeholder="Introduce tu país"/>
								</div>
							</div>
						</div>
						<h5>Información de la cuenta</h5>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
								  <form:label path="email" for="input_email">Email</form:label>
								  <form:input path="email" data-parsley-required="true" type="email" class="form-control" id="input_email" aria-describedby="emailHelp" placeholder="Introduce tu email"/>
								</div>
								<div class="form-group">
								  <form:label path="password" for="input_password">Contraseña</form:label>
								  <form:input path="password" data-parsley-required="true" type="password" class="form-control" id="input_password" data-parsley-minlength="8" placeholder="Password"/>
								</div>
								<div class="form-group">
								  <form:label path="confirmPassword" for="input_confirm_password">Confirma tu contraseña</form:label>
								  <form:input path="confirmPassword" data-parsley-required="true" type="password" class="form-control" id="input_confirm_password" data-parsley-minlength="8" placeholder="Password"/>
								</div>
								<button type="submit" class="btn btn-primary">Aceptar</button>
							</div>
							<div class="col-md-6">
								<div id="addUserToAccountDiv" class="d-none">
									<div class="form-group">
									  <form:label path="accountId" for="input_account_id">Tu DNI</form:label>
									  <form:input path="accountId" type="text" class="form-control" id="input_account_id" aria-describedby="" placeholder=""/>
									</div>
									<div class="form-group">
									  <form:label path="accountOwnerId" for="input_account_owner_id">Identificador del dueño</form:label>
									  <form:input path="accountOwnerId" type="text" class="form-control" id="input_account_owner_id" aria-describedby="" placeholder=""/>
									</div>
									<div class="form-group">
									  <form:label path="secretPassword" for="input_secret_password">Contraseña secreta</form:label>
									  <form:input path="secretPassword" type="text" class="form-control" id="input_secret_password" aria-describedby="" placeholder=""/>
									</div>
								</div>
							</div>
							
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$('#createAccountForm').parsley();
	</script>

</body>
</html>