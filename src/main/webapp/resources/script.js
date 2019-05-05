$(document).ready(function(){
	
  $("#new-account-button").click(function(){
	  $("#form-container").removeClass('d-none');
	  $("#options-container").addClass('d-none');
  });
  
  $("#existing-account-button").click(function(){
	  $("#form-container").removeClass('d-none');
	  $("#addUserToAccountDiv").removeClass('d-none');
	  $("#options-container").addClass('d-none');
  });
});