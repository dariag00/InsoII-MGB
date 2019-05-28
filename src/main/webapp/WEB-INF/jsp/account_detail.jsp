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
		
			<div class="row summaryContainer">
				<div class="col-md-6">
					<h4>Saludos ${user.nombre}, aquí tienes los detalles de tu cuenta.</h4>
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-12">
					<div class="card shadow-sm" id="accountCointainer">
						<div class="card-body">
							<div class="row">
								<div class="col-md-6">
									<h5 class="card-title text-primary">Your Account</h5>
									<div class="row">
										<div class="col-md-3">
											<img src="https://img.icons8.com/wired/64/000000/money-box.png">
										</div>
										<div class="col-md-9">
											<p>IBAN: ${account.getFormattedIban()}</p>
											<p>Titular: ${account.getAccountOwner().nombre}</p>
											<c:choose>
												<c:when test = "${!account.getAssociatedUsers().isEmpty()}">
													<p>Usuarios Asociados: </p>
													<c:forEach var="associatedUser" items="${account.getAssociatedUsers()}">
														<p>Nombre: ${associatedUser.getFullName()}</p>
													</c:forEach>
												</c:when>
												<c:otherwise>
													<p>Esta cuenta no tiene usuarios asociados</p>
												</c:otherwise>
												</c:choose>
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<button class="btn btn-primary accountButton" type="button" aria-haspopup="true" aria-expanded="false">Hacer una Transferencia</button>
									<button class="btn btn-primary accountButton" type="button" aria-haspopup="true" aria-expanded="false">Gestionar Usuarios</button>
									<button class="btn btn-primary accountButton" data-toggle="modal" data-target="#inviteUser">Invite an User</button>
									<button class="btn btn-danger accountButton" type="button" aria-haspopup="true" aria-expanded="false">Dar de baja</button>
								</div>
							</div>
						</div>
					</div>
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
	
	<div class="modal fade" id="inviteUser" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Invite an User to this Account</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
		      <div class="modal-body">
					<h4>Aquí estan los datos necesarios para que el usuario se una a la cuenta</h4>
					<div class="row">
						<div class="col-md-6">
						<p>Secret password: ${account.secretPassword}</p>
						<p>Account's Id: ${account.id}</p>
						<p>Owner´s Account Id: ${account.getAccountOwner().id}</p>
						</div>
						<div class="col-md-6">
						</div>
					</div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
		      </div>
	    </div>
	  </div>
	</div>
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/chart.js@2.7.2/dist/Chart.bundle.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	
</body>