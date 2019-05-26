<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="row movement">
	<div class="card movementCard shadow-sm">
		<div class="card-body">
			<div class="row movementContainer">
				<div class="col-sm2">
					<img src="https://img.icons8.com/color/48/000000/money-transfer.png">
				</div>
				<div class="col-sm-9 movementContent">
					<p>${transaction.commentary}</p>
					<small>${transaction.getFormattedDate()}</small>
				</div>
				<div class="col-sm-2">
					<h5 class="text-center centrado">${transaction.value} €</h5>
				</div>
			</div>
		</div>
	</div>
</div>