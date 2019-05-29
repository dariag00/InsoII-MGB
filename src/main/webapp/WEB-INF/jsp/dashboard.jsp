<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Dashboard</title>
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
	
		<div class="container text-" id="dashboardContainer">
			<a id="promotionsShow" class="form-text text-muted" href="#"><small>Hide/Show promotions</small></a>	
			<div class="row promotionContainer" id="promotionContainerId">
				<div class="col-md-4">
					<div class="card shadow-sm">
					 	<img class="card-img-top" src="https://images-na.ssl-images-amazon.com/images/I/71wTQpB99hL._SL1500_.jpg" alt="Card image cap">
					 	<div class="card-body">
					    	<h5 class="card-title">Consigue tu juego de cazuelas</h5>
					    	<p class="card-text">Entra en el sorteo de este increible juego de cazuelas marca Velaze</p>
					    	<a href="https://www.facebook.com/notes/farmacia-carol/gana-un-juego-de-ollas-royal-prestige/514748305208392/" class="btn btn-primary">Más info</a>
					  	</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="card shadow-sm">
						<img class="card-img-top" src="https://static.fnac-static.com/multimedia/Images/ES/NR/fe/27/46/4597758/1505-1.jpg" alt="Card image cap">
					 	<div class="card-body">
					    	<h5 class="card-title">Regalamos una Smart TV</h5>
					    	<p class="card-text">Invita a un amigo para conseguir tu propia SmartTV! (Promoción válida hasta fin de existencias)</p>
					    	<a href="https://www.fnac.es/TV-QLED-85-Samsung-QE85Q900R-8K-IA-HDR-4000-TV-TV-LED/a5984667" class="btn btn-primary">Más info</a>
					  	</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="card shadow-sm">
						<img class="card-img-top" src="https://upload.wikimedia.org/wikipedia/commons/thumb/e/e5/NASA_logo.svg/1200px-NASA_logo.svg.png" alt="Card image cap">
					 	<div class="card-body">
					    	<h5 class="card-title">Viaja a Marte</h5>
					    	<p class="card-text">MGB te ofrece de forma gratuita un viaje a Marte en colaboración con la Nasa. ¡Rellena este formulario y podrás viajar al planeta rojo en 2020! </p>
					    	<a href="https://mars.nasa.gov/participate/send-your-name/mars2020" class="btn btn-primary">Más info</a>
					  	</div>
					</div>
				</div>
			</div>
		
		
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
			<div class="row summaryContainer">
				<div class="col-md-6">
					<h4>Saludos ${user.nombre}, aquí tienes los detalles de tu cuenta.</h4>
				</div>
				<div class="col-md-6">
					<div class="dropdown">
					  <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					  	QUIERO
					  </button>
					  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
					    <a class="dropdown-item" data-toggle="modal" data-target="#transferModal">Realizar una transferencia</a>
					    <a class="dropdown-item" data-toggle="modal" data-target="#operationModal">Realizar una operación</a>
					    <a class="dropdown-item" data-toggle="modal" data-target="#addCardModal">Contratar una tarjeta de crédito</a>
					  	</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-6">
					<div class="card shadow-sm" id="accountCointainer">
						<div class="card-body">
							<h5 class="card-title text-primary">Your Accounts</h5>
							<c:forEach var="account" items="${accounts}">
								<%@ include file="account.jsp" %>
							</c:forEach>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="card shadow-sm" id="cardsContainer">
						<div class="card-body">
							<h5 class="card-title text-primary">Your Cards</h5>
							<c:forEach var="card" items="${cards}">
								<%@ include file="card.jsp" %>
							</c:forEach>
						</div>	
					</div>
				</div>
			</div>
			<div class="row graphicsContainer">
				<div class="col-md-6">
					<canvas id="moneyChart">
					</canvas>
				</div>
				<div class="col-md-6">
					<canvas id="accountsChart">
					</canvas>
				</div>
			</div>
			
			<div class="movementsContainer mx-auto">
				<h4>Tus movimientos</h4>
				<c:forEach var="transaction" items="${transactions}">
					<%@ include file="movement.jsp" %>
				</c:forEach>
			</div>
			
		</div>		
	<%@ include file="footer.jsp" %>
	
	<div class="modal fade" id="transferModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Realizar una Transferencia</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      	 <form:form method="POST" id="transferForm" action="/addTransfer" modelAttribute="transfer" data-parsley-validate="">
		      <div class="modal-body">
					<h4>A quien le quieres enviar el dinero?</h4>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
							  <form:label path="beneficiary" for="input_name">Name</form:label>
							  <form:input path="beneficiary" type="text" class="form-control" id="input_name" aria-describedby="emailHelp" placeholder="Diego Arias" data-parsley-required="true"/>
							</div>
							<div class="form-group">
							  <form:label path="iban" for="input_name">IBAN</form:label>
							  <form:input path="iban" type="text" class="form-control" id="input_name" aria-describedby="emailHelp" placeholder="ES 1555 1100...." data-parsley-required="true"/>
							</div>
							<div class="form-group">
							  <form:label path="commentary" for="input_name">Comentario</form:label>
							  <form:input path="commentary" type="text" class="form-control" id="input_name" aria-describedby="emailHelp" placeholder="Recibo de pago" data-parsley-required="true"/>
							</div>
							<div class="form-group">
							  <form:label path="value" for="input_name">Valor</form:label>
							  <form:input path="value" type="text" class="form-control" id="input_name" aria-describedby="emailHelp" placeholder="135.22" data-parsley-min="0.1" data-parsley-type="number" data-parsley-required="true"/>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
							   <form:label path="accountId" for="exampleFormControlSelect1">Selecciona una de tus cuentas</form:label>
							   <form:select path="accountId" class="form-control" id="exampleFormControlSelect1">
							   	<c:forEach var="account" items="${accounts}">
							     <option value="${account.id}">${account.getFormattedIban()} (${account.balance} €)</option>
							    </c:forEach>
							   </form:select>
							 </div>
							<h6>¿Que tipo de Transacción quieres realizar?</h6>
							<div class="form-check">
								<form:radiobutton path="type" value="0"/> Transferencia
							</div>
							<div class="form-check">
							  <form:radiobutton path="type" value="1"/> Transferencia entre cuentas de MGB
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
	
	<div class="modal fade" id="operationModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Realizar una Operacion</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
      	  <form:form method="POST" id="operationForm" action="/addOperation" modelAttribute="newOperation" data-parsley-validate="">
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
						   	<c:forEach var="card" items="${cards}">
						   	 <c:if test="${card.status == 0}">
						    	<option value="${card.id}">${card.getFormattedCardNumber()} (${card.getAccount().balance}€) </option>
						     </c:if>
						     <c:if test="${card.status == 1}">
						    	<option value="${card.id}" disabled>${card.getFormattedCardNumber()} (${card.getAccount().balance}€) INACTIVE</option>
						     </c:if>
						    </c:forEach>
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
	
	<div class="modal fade" id="addCardModal" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">Contratar una tarjeta nueva</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      	 <form:form method="POST" id="addCardForm" action="/addCard" modelAttribute="newCard" data-parsley-validate="">
			      <div class="modal-body">
						<h4>Elige el pin de tu nueva tarjeta</h4>
						<h5>Debe tener 4 dígitos</h5>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
								  <form:label path="secretPin" for="input_name">PIN</form:label>
								  <form:input path="secretPin" type="num" class="form-control" id="input_pin" aria-describedby="emailHelp" placeholder="0000" data-parsley-type="number" data-parsley-min="0" data-parsley-max="9999" data-parsley-required="true"/>
								</div>				
								<div class="form-group">
								   <form:label path="idAccount" for="exampleFormControlSelect1">Selecciona una de tus cuentas</form:label>
								   <form:select path="idAccount" class="form-control" id="exampleFormControlSelect1">
								   	<c:forEach var="account" items="${accounts}">
								     <option value="${account.id}">${account.getFormattedIban()} (${account.balance} €)</option>
								    </c:forEach>
								   </form:select>
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
		$('#transferForm').parsley();
		$('#addCardForm').parsley();
		$('#operationForm').parsley();
	</script>
	<script src="<c:url value="/resources/accountChart.js"/>"></script>
	<script src="<c:url value="/resources/moneyChart.js"/>"></script>
</body>

</html>