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
			<div class="card" id="options-container">
				<div class="card-body">
					<h5>What do you want to do?</h5>
					<div class="row">
						<div class="col-md-6">
							<button type="submit" id="new-account-button"class="btn btn-primary totalButton">Create an Account</button>
						</div>
						<div class="col-md-6">
							<button type="submit" id="existing-account-button" class="btn btn-primary totalButton">Join an existing Account</button>
						</div>
					</div>
				</div>
			</div>
			
			<div class="card d-none" id="form-container">
				<div class="img-container">
					<img class="card-img-top top-img" src="https://cdn.europosters.eu/image/750/wall-murals/classic-new-york-368x254-cm-premium-non-woven-wallpaper-130gsm-i55784.jpg" alt="Card image cap">
				</div>
				<div class="card-body">
					<form:form method="POST" action="/addUser" modelAttribute="user" >
						<h5>Personal Information</h5>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
								  <form:label path="name" for="input_name">Name</form:label>
								  <form:input path="name" type="text" class="form-control" id="input_name" aria-describedby="emailHelp" placeholder="Enter Name"/>
								</div>
								<div class="form-group">
								  <form:label path="surname" for="input_surname">Surname</form:label>
								  <form:input path="surname" type="text" class="form-control" id="input_surname" placeholder="Surname"/>
								</div>
								<div class="form-group">
								  <form:label path="id" for="input_id">ID</form:label>
								  <form:input path="id" type="text" class="form-control" id="input_id" placeholder="ID"/>
								</div>
								<div class="form-group">
								  <form:label path="birthdate" for="input_birthdate">Birthdate</form:label>
								  <form:input path="birthdate" type="text" class="form-control" id="input_birthdate" placeholder="Birthdate"/>
								  <small id="emailHelp" class="form-text text-muted">We'll never share your data with anyone else.</small>
								</div>
								
							</div>
							<div class="col-md-6">
								<div class="form-group">
								  <form:label path="address" for="input_address">Address</form:label>
								  <form:input path="address" type="text" class="form-control" id="input_address" placeholder="Address"/>
								</div>
								<div class="form-group">
								  <form:label path="postalCode" for="input_postal_code">Postal Code</form:label>
								  <form:input path="postalCode" type="text" class="form-control" id="input_postal_code" placeholder="Postal Code"/>
								</div>
								<div class="form-group">
								  <form:label path="city" for="input_city">City</form:label>
								  <form:input path="city" type="text" class="form-control" id="input_city" placeholder="City"/>
								</div>
								<div class="form-group">
								  <form:label path="country" for="input_country">Country</form:label>
								  <form:input path="country" type="text" class="form-control" id="input_country" placeholder="Country"/>
								</div>
							</div>
						</div>
						<h5>Account Information</h5>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
								  <form:label path="email" for="input_email">Email</form:label>
								  <form:input path="email" type="email" class="form-control" id="input_email" aria-describedby="emailHelp" placeholder="Enter email"/>
								</div>
								<div class="form-group">
								  <form:label path="password" for="input_password">Password</form:label>
								  <form:input path="password" type="password" class="form-control" id="input_password" placeholder="Password"/>
								</div>
								<div class="form-group">
								  <form:label path="confirmPassword" for="input_confirm_password">Confirm Password</form:label>
								  <form:input path="confirmPassword" type="password" class="form-control" id="input_confirm_password" placeholder="Password"/>
								</div>
								<button type="submit" class="btn btn-primary">Submit</button>
							</div>
							<div class="col-md-6">
								<div id="addUserToAccountDiv" class="d-none">
									<div class="form-group">
									  <form:label path="accountId" for="input_account_id">Introduce Account ID</form:label>
									  <form:input path="accountId" type="text" class="form-control" id="input_account_id" aria-describedby="" placeholder="Enter Account´s ID"/>
									</div>
									<div class="form-group">
									  <form:label path="accountOwnerId" for="input_account_owner_id">Introduce ID of Account´s Owner</form:label>
									  <form:input path="accountOwnerId" type="text" class="form-control" id="input_account_owner_id" aria-describedby="" placeholder="Enter Account´s Owner ID"/>
									</div>
									<div class="form-group">
									  <form:label path="secretPassword" for="input_secret_password">Introduce Secret Password</form:label>
									  <form:input path="secretPassword" type="text" class="form-control" id="input_secret_password" aria-describedby="" placeholder="Enter the secret password"/>
									</div>
								</div>
							</div>
							
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="<c:url value="/resources/script.js"/>"></script>