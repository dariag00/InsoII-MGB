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


<body class="background-blue">
	<div class="jumbotron vertical-center">
		<div class="container text-">
			<div class="card">
				<div class="img-container">
					<img class="card-img-top top-img" src="https://cdn.europosters.eu/image/750/wall-murals/classic-new-york-368x254-cm-premium-non-woven-wallpaper-130gsm-i55784.jpg" alt="Card image cap">
				</div>
				<div class="card-body">
					<form>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
								  <label for="input_email">Email address</label>
								  <input type="email" class="form-control" id="input_email" aria-describedby="emailHelp" placeholder="Enter email">
								</div>
								<div class="form-group">
								  <label for="input_password">Password</label>
								  <input type="password" class="form-control" id="input_password" placeholder="Password">
								</div>
								<div class="form-group">
								  <label for="input_confirm_password">Confirm Password</label>
								  <input type="password" class="form-control" id="input_confirm_password" placeholder="Password">
								</div>
								<div class="form-group">
								  <label for="input_confirm_password">Birthdate</label>
								  <input type="password" class="form-control" id="input_confirm_password" placeholder="Password">
								  <small id="emailHelp" class="form-text text-muted">We'll never share your data with anyone else.</small>
								</div>
								<button type="submit" class="btn btn-primary">Submit</button>
								
							</div>
							<div class="col-md-6">
								<div class="form-group">
								  <label for="input_confirm_password">Name</label>
								  <input type="password" class="form-control" id="input_confirm_password" placeholder="Password">
								</div>
								<div class="form-group">
								  <label for="input_confirm_password">Surname</label>
								  <input type="password" class="form-control" id="input_confirm_password" placeholder="Password">
								</div>
								<div class="form-group">
								  <label for="input_confirm_password">ID</label>
								  <input type="password" class="form-control" id="input_confirm_password" placeholder="Password">
								</div>
								<div class="form-group">
								  <label for="input_confirm_password">Birthdate</label>
								  <input type="password" class="form-control" id="input_confirm_password" placeholder="Password">
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>