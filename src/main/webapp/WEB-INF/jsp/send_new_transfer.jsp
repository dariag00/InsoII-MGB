<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>New Transfer</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link href="<c:url value="/resources/styles.css"/>" rel="stylesheet"></link>
	<!--<script src="<c:url value="/resources/js/bootstrap.js"/>"></script>
	<link href="<c:url value="/resources/css/bootstrap.css"/>" rel="stylesheet"></link>-->
</head>


<body>
	
	<%@ include file="main_navbar.jsp" %>

	<div class="jumbotron" id="transactionMenu">
		<div class="container text-">
			<div class="card shadow-sm" id="form-container">
				
				<div class="card-body">
					<form:form method="POST" action="/addUser" modelAttribute="transfer" >
						<h4>A quien le quieres enviar el dinero?</h4>
						<a href="/dashboard" >Volver</a>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
								  <form:label path="beneficiary" for="input_name">Name</form:label>
								  <form:input path="beneficiary" type="text" class="form-control" id="input_name" aria-describedby="emailHelp" placeholder="Diego Arias García"/>
								</div>
								<div class="form-group">
								  <form:label path="iban" for="input_name">IBAN</form:label>
								  <form:input path="iban" type="text" class="form-control" id="input_name" aria-describedby="emailHelp" placeholder="ES 1555 1100...."/>
								</div>
								<div class="form-group">
								  <form:label path="commentary" for="input_name">Comentario</form:label>
								  <form:input path="commentary" type="text" class="form-control" id="input_name" aria-describedby="emailHelp" placeholder="Recibo de pago"/>
								</div>
								<div class="form-group">
								  <form:label path="value" for="input_name">Valor</form:label>
								  <form:input path="value" type="text" class="form-control" id="input_name" aria-describedby="emailHelp" placeholder="135.22"/>
								</div>
								
								<button type="submit" class="btn btn-primary">Send</button>
								
							</div>
							<div class="col-md-6">
								<div class="form-group">
								   <form:label path="accountId" for="exampleFormControlSelect1">Selecciona una de tus cuentas</form:label>
								   <form:select path="accountId" class="form-control" id="exampleFormControlSelect1">
								     <option>IBAN 1 (100€)</option>
								     <option>IBAN 1 (100€)</option>
								     <option>IBAN 1 (100€)</option>
								   </form:select>
								 </div>
								<h6>¿Que tipo de Transacción quieres realizar?</h6>
								<div class="form-check">
								  <input class="form-check-input" type="radio" name="exampleRadios" id="exampleRadios1" value="option1" checked>
								  <label class="form-check-label" for="exampleRadios1">
								    Transferencia
								  </label>
								</div>
								<div class="form-check">
								  <input class="form-check-input" type="radio" name="exampleRadios" id="exampleRadios2" value="option2">
								  <label class="form-check-label" for="exampleRadios2">
								    Pago de Recibo
								  </label>
								</div>
								<div class="form-check">
								  <input class="form-check-input" type="radio" name="exampleRadios" id="exampleRadios3" value="option3">
								  <label class="form-check-label" for="exampleRadios3">
								    Transferencia entre cuentas de MGB
								  </label>
								</div>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	
	
	<%@ include file="footer.jsp" %>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/chart.js@2.7.2/dist/Chart.bundle.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>

</html>