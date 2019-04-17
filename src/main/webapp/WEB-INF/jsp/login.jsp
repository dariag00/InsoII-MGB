<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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


<body>

	<%@ include file="navbar.jsp" %>
	
	<div class="container">
		<div class="row align-items-center">
			<div class="col-md-4 offset-md-4 align-items-center">

				<form class="form" action="" method="POST"
				style="width:350px">
				
				<div class="card-body">
						<img src="https://img.icons8.com/ios/150/000000/museum.png" class="rounded mx-auto d-block" alt="logo">
						<h1 class="card-title text-center">MGB</h1>
						<h5 class="text-center">Makros General Bank</h5>
				</div>
					<!--<div class="alert alert-danger invisible" role="alert" value="${ErrorMessage}" id="LoginAlert">${ErrorMessage}</div>
					<div class="alert invisible" role="alert" value="${Message}" id="LoginAlert">${Message}</div>-->
					<div class="card-body">
						<div class="input-group mb-3">
  							<div class="input-group-prepend">
						    	<span class="input-group-text" id="inputGroup-sizing-default"><img src="https://img.icons8.com/ios/24/000000/new-post.png"></span>
						  	</div>
  							<input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" placeholder="Email">
						</div>
						<div class="input-group mb-3">
  							<div class="input-group-prepend">
						    	<span class="input-group-text" id="inputGroup-sizing-default"><img src="https://img.icons8.com/ios/24/000000/password1.png"></span>
						  	</div>
  							<input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" placeholder="Password">
						</div>
						<button type="button" class="btn btn-primary btn-lg btn-block">Log In</button>
						
						<hr>
						<a class="btn btn-primary btn-lg btn-block" href="#" role="button">Sign In</a>
					</div>
				</form>
				
				</div>
			
		</div>
  </div>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>

</html>