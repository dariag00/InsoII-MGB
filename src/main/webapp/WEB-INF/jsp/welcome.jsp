<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Welcome</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link href="<c:url value="/resources/styles.css"/>" rel="stylesheet"></link>
	<!--<script src="<c:url value="/resources/js/bootstrap.js"/>"></script>
	<link href="<c:url value="/resources/css/bootstrap.css"/>" rel="stylesheet"></link>-->
</head>


<body >
	<%@ include file="navbar.jsp" %>
	
	<div class= "main" align="center" id="welcomeContainer" style="background-attachment:fixed" >
		<div class="row welcomeText" align="center">
			<div>
					<img src="https://img.icons8.com/ios/150/000000/museum.png" class="rounded mx-auto d-block" alt="logo">
			</div>
			<div align="center">
				<h1 style="font-size:5vw">Makros General Bank</h1>
			 	<h2 > Su banco de confianza, y cada d�a el de m�s gente</h2> 	
			</div>
			<div>
				<img src="https://img.icons8.com/ios/150/000000/museum.png" class="rounded mx-auto d-block" alt="logo">
	
			</div>
		
		</div>
		<div class="container" align="center" display="inline-block">
			<img src="https://platterivermed.com/wp-content/uploads/2014/12/Happy-Family.jpg"/>
		</div>
		<div class="row promotionContainer">
				<div class="col-md-4">
					<div class="card shadow-sm">
					 	<div class="card-body">
					    	<h5 class="card-title">�Quieres unirte a nosotros?</h5>
					    	<a href="/create_account" class="btn btn-primary">�Log�ate ahora!</a>
					  	</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="card shadow-sm">
						<div class="card-body">
					    	<h5 class="card-title">Si ya eres socio...</h5>
					    	<a href="/login" class="btn btn-primary">Sin problema.</a>
					  	</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="card shadow-sm">
						<div class="card-body">
					    	<h5 class="card-title">�Solo est�s aqu� por los memes?</h5>
					    	<a href="https://www.reddit.com/r/memes/" target="_blank" class="btn btn-primary">Sin problema.</a>
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