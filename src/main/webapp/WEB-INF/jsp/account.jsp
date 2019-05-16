<div id="account2">
	<div class= "row">
		<div class="col-md-8 cardDataContainer">
			<h5>${account.getFormattedIban()}</h5>
			<p class="text-secondary">Titular: ${account.getAccountOwner().getFullName()}</p>
		</div>
		<div class="col-md-4 cardDataContainer">
			<h3 class="text-secondary">${account.getFormattedBalance()}</h3>
		</div>
	</div>
	<small id="accountMore" class="form-text text-muted">Click for more info.</small>
	<hr>
</div>	