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
</head>


<body>

	<%@ include file="main_navbar.jsp" %>
	
		<div class="container text-" id="dashboardContainer">		
			<div class="row promotionContainer">
				<div class="col-md-4">
					<div class="card shadow-sm">
					 	<img class="card-img-top" src="https://images.unsplash.com/photo-1513885535751-8b9238bd345a?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80" alt="Card image cap">
					 	<div class="card-body">
					    	<h5 class="card-title">Promocion 1</h5>
					    	<p class="card-text">Texto</p>
					    	<a href="#" class="btn btn-primary">See more</a>
					  	</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="card shadow-sm">
						<img class="card-img-top" src="https://images.unsplash.com/photo-1513885535751-8b9238bd345a?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80" alt="Card image cap">
					 	<div class="card-body">
					    	<h5 class="card-title">Promocion 2</h5>
					    	<p class="card-text">Texto</p>
					    	<a href="#" class="btn btn-primary">See more</a>
					  	</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="card shadow-sm">
						<img class="card-img-top" src="https://images.unsplash.com/photo-1513885535751-8b9238bd345a?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80" alt="Card image cap">
					 	<div class="card-body">
					    	<h5 class="card-title">Promocion 3</h5>
					    	<p class="card-text">Texto.</p>
					    	<a href="#" class="btn btn-primary">See more</a>
					  	</div>
					</div>
				</div>
			</div>
		
		
		
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
					    <a class="dropdown-item" href="/newCard">Contratar una tarjeta de crédito</a>
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
							  <form:input path="beneficiary" type="text" class="form-control" id="input_name" aria-describedby="emailHelp" placeholder="" data-parsley-required="true"/>
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
	<script type="text/javascript">
		$('#transferForm').parsley();
	</script>
	<script src="<c:url value="/resources/accountChart.js"/>"></script>
	<script src="<c:url value="/resources/moneyChart.js"/>"></script>
</body>

</html>