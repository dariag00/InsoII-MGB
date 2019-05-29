<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="row movement">
	<div class="card movementCard shadow-sm">
		<div class="card-body">
			<div class="row movementContainer">
				<div class="col-sm2">
					<img src="https://img.icons8.com/cotton/64/000000/card-in-use.png">
				</div>
				<div class="col-sm-9 movementContent">
					<p>${operation.location}</p>
					<small>Fecha: ${operation.getFormattedDate()}  </small>
					<small>Tipo: ${operation.getOperationTypeString()}</small>
				</div>
				<div class="col-sm-2">
					<h5 class="text-center centrado">${operation.value} €</h5>
				</div>
			</div>
		</div>
	</div>
</div>