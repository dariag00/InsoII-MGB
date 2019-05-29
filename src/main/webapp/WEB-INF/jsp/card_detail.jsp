<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Card Detail</title>
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
	
</head>


<body>

	<%@ include file="main_navbar.jsp" %>
	
	<div class="container text-" id="dashboardContainer">				
		
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
					<h4>Greeting ${user.nombre}, hereby you can find the details of your card.</h4>
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-12">
					<div class="card shadow-sm" id="accountCointainer">
						<div class="card-body">
							<div class="row">
								<div class="col-md-6">
									<h5 class="card-title text-primary">Your Card</h5>
									<div class="row">
										<div class="col-md-3">
											<img src="https://img.icons8.com/dotty/80/000000/credit-card-contactless.png">
										</div>
										<div class="col-md-9">
											<p>Card Number: ${card.getFormattedCardNumber()}</p>
											<p>Owner: ${card.user.getFullName()}</p>
											<p>CVV: ${card.cvv}</p>
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<button class="btn btn-primary accountButton" data-toggle="modal" data-target="#operationModal">Realizar un pago</button>
									<button class="btn btn-primary accountButton" data-toggle="modal" data-target="#changePinModal">Change PIN</button>
									<button class="btn btn-danger accountButton" data-toggle="modal" data-target="">Dar de baja la tarjeta</button>
									<button class="btn btn-danger accountButton" data-toggle="modal" data-target="#deleteAccountModal">Eliminar Tarjeta</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="movementsContainer mx-auto">
				<h4>Tus movimientos</h4>
				<c:forEach var="transaction" items="${operations}">
					<%@ include file="movement.jsp" %>
				</c:forEach>				
			</div>
			
		</div>	
	<%@ include file="footer.jsp" %>
	

	
	<div class="modal fade" id="deleteAccountModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLongTitle">Delete Card</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	       <p>Are you sure that you want to delete this card?</p>
	       <small>You will lose all its information.</small>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	        <a class="btn btn-danger anchorBs" href="/deleteCard?cardId=${card.id}">Delete</a>
	      </div>
	    </div>
	  </div>
	</div>
	
	
	<div class="modal fade" id="changePinModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLongTitle">Change your PIN</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <form:form method="POST" id="changePinForm" action="/changePin?cardId=${card.id}" modelAttribute="cardForm" data-parsley-validate="">
	      <div class="modal-body">

    			<div class="form-group">
				  <form:label path="oldPin" for="input_old_pin">Old PIN</form:label>
				  <form:input path="oldPin" type="password" class="form-control" id="input_old_pin" aria-describedby="emailHelp" placeholder="0000" data-parsley-type="number" data-parsley-min="0" data-parsley-max="9999" data-parsley-required="true"/>
				</div>
				<div class="form-group">
				  <form:label path="secretPin" for="input_new_pin">New PIN</form:label>
				  <form:input path="secretPin" type="password" class="form-control" id="input_new_pin" aria-describedby="emailHelp" placeholder="0000" data-parsley-type="number" data-parsley-min="0" data-parsley-max="9999" data-parsley-required="true"/>
				</div>
				<div class="form-group">
				  <form:label path="confirmSecretPin" for="input_new_pin_confirm">Confirm New PIN</form:label>
				  <form:input path="confirmSecretPin" type="password" class="form-control" id="input_new_pin_confirm" aria-describedby="emailHelp" placeholder="0000" data-parsley-type="number" data-parsley-min="0" data-parsley-max="9999" data-parsley-required="true"/>
				</div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	        <button type="submit" class="btn btn-primary">Change</button>
	      </div>
	     </form:form>
	    </div>
	  </div>
	</div>
	
	
	<div class="modal fade" id="operationModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Realizar una Operacion</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
      	  <form:form method="POST" id="operationForm" action="/addOperationFromCard" modelAttribute="newOperation" data-parsley-validate="">
	      	<div class="modal-body">
				<h4>Concreta los detalles de la operación</h4>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							  <form:label path="location" for="input_name">Destino</form:label>
							  <form:input path="location" type="num" class="form-control" id="destiny" aria-describedby="emailHelp" placeholder="Cines Aeon" data-parsley-type="text"  data-parsley-required="true"/>
						</div>			
						<div class="form-group">
						 		 <form:label path="value" for="input_name">Valor</form:label>
						  		<form:input path="value" type="text" class="form-control" id="input_name" aria-describedby="emailHelp" placeholder="45" data-parsley-min="0.1" data-parsley-type="number" data-parsley-required="true"/>
						</div>	
					</div>
					<div class="col-md-6">
						<div class="form-group">
						   <form:label path="cardId" for="exampleFormControlSelect1">Selecciona una de tus tarjetas</form:label>
						   <form:select path="cardId" class="form-control" id="exampleFormControlSelect1">
						   	<option value="${card.id}">${card.getFormattedCardNumber()} (${card.getAccount().balance}€) </option>
						   </form:select>
						 </div>
						<h6>¿Que tipo de Operación quieres realizar?</h6>
						<div class="form-check">
							<form:radiobutton path="type" value="0"/> Realizar Pago
						</div>
						<div class="form-check">
						  <form:radiobutton path="type" value="1"/> Sacar dinero
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
	
	<script type="text/javascript">
		$('#operationForm').parsley();
		$('#changePinForm').parsley();
	</script>
	
</body>