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

function showEditModal(id, rego, make) {
	$('#carId').val(id);
	$('#editCarMake').val(make);
	$('#editRego').val(rego);
	$('#editCarAlert').hide();
	$("#editCarModal").modal();
}

$("#editCar").click(function() {
var errorMessage = "";
	
	if ($("#editCarMake").val() == "" ) {
		errorMessage += "Hey, I think you forgot to enter the Car Make<br/>";
	}
	
	if ($("#editRego").val() == "" ) {
		errorMessage += "Sorry Please enter rego<br/>";
	}
	
	if (errorMessage != "") {
		$("#editCarAlert").html(errorMessage);
		$("#editCarAlert").show();
		return false;
	}
	
})

$("#editCarModalClose").click(function() {
	$('#editCarModal').modal('hide');
})



