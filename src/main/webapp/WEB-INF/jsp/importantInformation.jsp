<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Login</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link href="<c:url value="/resources/styles.css"/>" rel="stylesheet"></link>
	<!--<script src="<c:url value="/resources/js/bootstrap.js"/>"></script>
	<link href="<c:url value="/resources/css/bootstrap.css"/>" rel="stylesheet"></link>-->
</head>


<body class="background-blue">
	<div class="jumbotron jumbotronBlue vertical-center">
		<div class="container text-">
			
			<div class="card" id="form-container">
				<div class="img-container">
					<img class="card-img-top top-img" src="https://cdn.europosters.eu/image/750/wall-murals/classic-new-york-368x254-cm-premium-non-woven-wallpaper-130gsm-i55784.jpg" alt="Card image cap">
				</div>
				<div class="card-body">
					<h1>Información importante que requiere de tu atención</h1>
					<div class="row">
						<div class="col-md-6">
							<h3>Información sobre tu cuenta</h3>
							<p>IBAN: ${account.getFormattedIban()} </p>
							<p>Se han añadido 50$ a tu cuenta.</p>
							<form:form method="POST" action="/dashboard">
								<button type="submit" class="btn btn-primary">Entendido</button>
							</form:form>
						</div>
						<div class="col-md-6">
							<h3>Información sobre tu tarjeta</h3>
							<p>Número de tarjeta: ${card.cardNumber}</p>
							<p>CCV: ${card.cvv}</p>
							<p>Pin Secreto: ${card.secretPin}</p>
						</div>
					</div>
					
				</div>
			</div>
		</div>
	</div>

</body>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="<c:url value="/resources/script.js"/>"></script>
</html>