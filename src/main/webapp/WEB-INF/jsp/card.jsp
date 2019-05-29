<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="card">
	<div class= "row">
		<div class="col-md-3">
			<img src="https://img.icons8.com/color/48/000000/mastercard-credit-card.png" width="75%">
		</div>
		<div class="col-md-9 cardDataContainer">
			<h5>${card.getFormattedCardNumber()}</h5>
			<p class="text-secondary">Titular: ${card.getUser().getFullName()}</p>
		</div>
	</div>
	<a href="/cardDetail?cardId=${card.id}"><small id="accountMore" class="form-text text-muted">Click para más información.</small></a>
	<hr>
</div>	