$("#saveCar").click(function() {
	var errorMessage = "";
	
	if ($("#carMake").val() == "" ) {
		errorMessage += "Please enter Car Make<br/>";
	}
	
	if ($("#rego").val() == "" ) {
		errorMessage += "Please enter rego<br/>";
	}
	
	if (errorMessage != "") {
		$("#errorAlert").html(errorMessage);
		$("#errorAlert").show();
		return false;
	}
	
})