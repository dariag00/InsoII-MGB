<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
					<div class="card">
					 	<img class="card-img-top" src="https://images.unsplash.com/photo-1513885535751-8b9238bd345a?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80" alt="Card image cap">
					 	<div class="card-body">
					    	<h5 class="card-title">Promocion 1</h5>
					    	<p class="card-text">Texto</p>
					    	<a href="#" class="btn btn-primary">See more</a>
					  	</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="card">
						<img class="card-img-top" src="https://images.unsplash.com/photo-1513885535751-8b9238bd345a?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80" alt="Card image cap">
					 	<div class="card-body">
					    	<h5 class="card-title">Promocion 2</h5>
					    	<p class="card-text">Texto</p>
					    	<a href="#" class="btn btn-primary">See more</a>
					  	</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="card">
						<img class="card-img-top" src="https://images.unsplash.com/photo-1513885535751-8b9238bd345a?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80" alt="Card image cap">
					 	<div class="card-body">
					    	<h5 class="card-title">Promocion 3</h5>
					    	<p class="card-text">Texto.</p>
					    	<a href="#" class="btn btn-primary">See more</a>
					  	</div>
					</div>
				</div>
			</div>
		
		
		
			<h4>DINERO DE LA CUENTA</h4>
			<p>Saldo de la cuenta</p>
			
			<div class="row">
				<div class="col-md-6">
					<div class="card" id="accountCointainer">
						<div class="card-body">
							<h5 class="card-title text-primary">Your Accounts</h5>
							<div id="account">
								<div class= "d-flex justify-content-between">
									<div class="p2">
										<h5>IBAN</h5>
										<p class="text-secondary">Titular: Nombre Apellidos</p>
									</div>
									<div class="p2">
										<h3 class="text-secondary">550 EUROS</h3>
									</div>
								</div>
								<hr>
							</div>
							<div id="account2">
								<div class= "d-flex justify-content-between">
									<div class="p2">
										<h5>IBAN</h5>
										<p class="text-secondary">Titular: Nombre Apellidos</p>
									</div>
									<div class="p2">
										<h3 class="text-secondary">550 EUROS</h3>
									</div>
								</div>
								<hr>
							</div>		
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="card" id="cardsContainer">
						<div class="card-body">
							<h5 class="card-title text-primary">Your Cards</h5>
						</div>
					</div>
				</div>
			</div>
		</div>
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>

</html>