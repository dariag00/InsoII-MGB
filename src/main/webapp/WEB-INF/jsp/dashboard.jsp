<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Dashboard</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link href="<c:url value="/resources/styles.css"/>" rel="stylesheet"></link>
	<!--<script src="<c:url value="/resources/js/bootstrap.js"/>"></script>
	<link href="<c:url value="/resources/css/bootstrap.css"/>" rel="stylesheet"></link>-->
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
					<h4>Este es el resumen de su cuenta:</h4>
				</div>
				<div class="col-md-6">
					<div class="dropdown">
					  <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					  	QUIERO
					  </button>
					  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
					    <a class="dropdown-item" href="/newTransfer">Realizar una transferencia</a>
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
							<div id="account2">
								<div class= "row">
									<div class="col-md-8 cardDataContainer">
										<h5>IBAN</h5>
										<p class="text-secondary">Titular: Nombre Apellidos</p>
									</div>
									<div class="col-md-4 cardDataContainer">
										<h3 class="text-secondary">550 EUROS</h3>
									</div>
								</div>
								<small id="accountMore" class="form-text text-muted">Click for more info.</small>
								<hr>
							</div>	
							<div id="account1">
								<div class= "row">
									<div class="col-md-8 cardDataContainer">
										<h5>IBAN</h5>
										<p class="text-secondary">Titular: Nombre Apellidos</p>
									</div>
									<div class="col-md-4 cardDataContainer">
										<h3 class="text-secondary">550 EUROS</h3>
									</div>
								</div>
								<small id="accountMore" class="form-text text-muted">Click for more info.</small>
								<hr>
							</div>		
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="card shadow-sm" id="cardsContainer">
						<div class="card-body">
							<h5 class="card-title text-primary">Your Cards</h5>
							<div id="card1">
								<div class= "row">
									<div class="col-md-3">
										<img src="https://img.icons8.com/color/48/000000/mastercard-credit-card.png" width="75%">
									</div>
									<div class="col-md-9 cardDataContainer">
										<h5>NUMERO DE LA TARJETA TO LARGO</h5>
										<p class="text-secondary">Titular: Nombre Apellidos</p>
									</div>
								</div>
								<small id="accountMore" class="form-text text-muted">Click for more info.</small>
								<hr>
							</div>	
							<div id="card1">
								<div class= "row">
									<div class="col-md-3">
										<img src="https://img.icons8.com/color/48/000000/mastercard-credit-card.png" width="75%">
									</div>
									<div class="col-md-9 cardDataContainer">
										<h5>NUMERO DE LA TARJETA TO LARGO</h5>
										<p class="text-secondary">Titular: Nombre Apellidos</p>
									</div>
								</div>
								<small id="accountMore" class="form-text text-muted">Click for more info.</small>
								<hr>
							</div>
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
				<div class="row movement">
					<div class="card movementCard shadow-sm">
						<div class="card-body">
							<div class="row movementContainer">
								<div class="col-sm2">
									<img src="https://img.icons8.com/color/48/000000/money-transfer.png">
								</div>
								<div class="col-sm-9 movementContent">
									<p>CINES QUE DISES LOCO</p>
									<small>15/05/2019</small>
								</div>
								<div class="col-sm-2">
									<h5 class="text-center centrado">-15.00€</h5>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row movement">
					<div class="card movementCard shadow-sm">
						<div class="card-body">
							<div class="row movementContainer">
								<div class="col-sm2">
									<img src="https://img.icons8.com/color/48/000000/money-transfer.png">
								</div>
								<div class="col-sm-9 movementContent">
									<p>CINES QUE DISES LOCO</p>
									<small>15/05/2019</small>
								</div>
								<div class="col-sm-2">
									<h5 class="text-center centrado">-15.00€</h5>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row movement">
					<div class="card movementCard shadow-sm">
						<div class="card-body">
							<div class="row movementContainer">
								<div class="col-sm2">
									<img src="https://img.icons8.com/color/48/000000/money-transfer.png">
								</div>
								<div class="col-sm-9 movementContent">
									<p>CINES QUE DISES LOCO</p>
									<small>15/05/2019</small>
								</div>
								<div class="col-sm-2">
									<h5 class="text-center centrado">-15.00€</h5>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
		</div>		
	<%@ include file="footer.jsp" %>

	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/chart.js@2.7.2/dist/Chart.bundle.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	<script src="<c:url value="/resources/accountChart.js"/>"></script>
	<script src="<c:url value="/resources/moneyChart.js"/>"></script>
</body>

</html>