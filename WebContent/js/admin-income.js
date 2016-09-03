$("#saveIncomeType").click(function() {
	var errorMessage = "";
	
	if ($("#incomeName").val() == "" ) {
		errorMessage += "Please enter Income Name<br/>";
	}
	
	if (errorMessage != "") {
		$("#errorAlert").html(errorMessage);
		$("#errorAlert").show();
		return false;
	}
	
})

function showEditModal(id, name, description) {
	setValuesInEditIncomeTypeModal(id, name, description)
	$('#editIncomeTypeAlert').hide();
	$("#editIncomeTypeModal").modal();
}

function setValuesInEditIncomeTypeModal(id, name, description) {
	$('#incomeTypeId').val(id);
	$('#editIncomeName').val(name);
	$('#editIncomeDesc').val(description);
}

$("#editIncomeType").click(function() {
var errorMessage = "";
	
	if ($("#editIncomeName").val() == "" ) {
		errorMessage += "Hey, I think you forgot to enter the Income Name<br/>";
	}
	
	if (errorMessage != "") {
		$("#editIncomeTypeAlert").html(errorMessage);
		$("#editIncomeTypeAlert").show();
		return false;
	}
	
})

$("#editIncomeTypeModalClose").click(function() {
	$('#editIncomeTypeModal').modal('hide');
})



